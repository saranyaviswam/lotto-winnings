package com.codechallenge.lottoapi.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.codechallenge.lottoapi.model.LottoResultsVO;
import com.codechallenge.lottoapi.model.LottoVO;
import com.codechallenge.lottoapi.model.LottoWinningDeatilsVO;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class LottoServiceImpl implements LottoService {

	private static final Map<String, Integer> WINNING_FORMULA = new HashMap<String, Integer>() {

		private static final long serialVersionUID = 1L;

		{
			put("6", 5000000);
			put("5+", 250000);
			put("5", 30000);
			put("4+", 85);
			put("4", 85);
			put("3+", 10);
			put("3", 10);
			put("2+", 5);
			put("2", 3);
		}
	};

	@SuppressWarnings("unchecked")
	@Override
	public LottoVO getLottoWinningDetails(List<Integer> userNumbers) {

		List<LottoResultsVO> results = getLottoResults();
		LottoVO lottoVO = new LottoVO();
		BigDecimal totalSpent = getTotalSpent(results.size());

		System.out.println("No of rows >>" + results.size());
		for (LottoResultsVO result : results) {
			String representation = String
					.valueOf(CollectionUtils.intersection(userNumbers, result.getNumbers()).size());
			if (userNumbers.contains(result.getBonusNumber())) {
				representation += "+";
			}

			Integer winningAmount = WINNING_FORMULA.get(representation);
			if (winningAmount == null) {
				continue;
			}
			lottoVO.addTotalWon(new BigDecimal(winningAmount));
			if (winningAmount >= 85) {
				LottoWinningDeatilsVO winningDetails = new LottoWinningDeatilsVO(result.getDrawDate(), winningAmount);
				lottoVO.addWinningDetails(winningDetails);
			}

		}

		lottoVO.setTotalSpent(totalSpent);
		lottoVO.setNetWonOrLoss(getNetWinOrLoss(lottoVO.getTotalWon(), totalSpent));
		return lottoVO;
	}

	private List<LottoResultsVO> getLottoResults() {
		List<LottoResultsVO> results = new ArrayList<>();

		try (InputStream is = getClass().getClassLoader().getResourceAsStream("data/649.csv");
				CSVReader reader = new CSVReader(new InputStreamReader(is))) {
			String[] lineInArray;
			try {
				while ((lineInArray = reader.readNext()) != null) {
					System.out.println("---" + lineInArray[2] + "---");
					if (lineInArray[2].trim().equals("0")) {
						LottoResultsVO vo = new LottoResultsVO();
						results.add(vo);

						vo.setDrawNumber(Integer.parseInt(lineInArray[1]));
						vo.setSeqNumber(Integer.parseInt(lineInArray[2]));
						vo.setDrawDate(lineInArray[3]);
						vo.addNumber(Integer.parseInt(lineInArray[4]));
						vo.addNumber(Integer.parseInt(lineInArray[5]));
						vo.addNumber(Integer.parseInt(lineInArray[6]));
						vo.addNumber(Integer.parseInt(lineInArray[7]));
						vo.addNumber(Integer.parseInt(lineInArray[8]));
						vo.addNumber(Integer.parseInt(lineInArray[9]));
						vo.setBonusNumber(Integer.parseInt(lineInArray[10]));
					}
				}
			} catch (CsvValidationException | NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(results);
		return results;
	}

	private BigDecimal getTotalSpent(int totalDrawNumber) {

		// 2124*1 + (2989 - 2124)*2 + (totalDrawNumber - 2989)*3
		BigDecimal oneDollarDraws = new BigDecimal(2124);
		BigDecimal twoDollarDraws = new BigDecimal((2989 - 2124) * 2);
		BigDecimal threeDollarDraws = new BigDecimal((totalDrawNumber - 2989) * 3);

		return oneDollarDraws.add(twoDollarDraws).add(threeDollarDraws);
	}

	private BigDecimal getNetWinOrLoss(BigDecimal totalWon, BigDecimal totalSpent) {
		return totalWon.subtract(totalSpent);
	}

}
