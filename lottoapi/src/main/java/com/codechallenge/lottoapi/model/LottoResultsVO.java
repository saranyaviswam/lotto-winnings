package com.codechallenge.lottoapi.model;

import java.util.ArrayList;
import java.util.List;

public class LottoResultsVO {
	
	private int drawNumber;
	
	private int seqNumber;
	
	private String drawDate;
	
	private List<Integer> numbers;
	
	private int bonusNumber;

	public int getDrawNumber() {
		return drawNumber;
	}

	public void setDrawNumber(int drawNumber) {
		this.drawNumber = drawNumber;
	}

	public int getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}

	public String getDrawDate() {
		return drawDate;
	}

	public void setDrawDate(String drawDate) {
		this.drawDate = drawDate;
	}

	

	public List<Integer> getNumbers() {
		return numbers;
	}

	public void addNumber(Integer number) {
		if (this.numbers == null) {
			this.numbers = new ArrayList<>();
		}
		this.numbers.add(number);
	}

	public int getBonusNumber() {
		return bonusNumber;
	}

	public void setBonusNumber(int bonusNumber) {
		this.bonusNumber = bonusNumber;
	}


	@Override
	public String toString() {
		return "LottoResultsVO [drawNumber=" + drawNumber + ", seqNumber=" + seqNumber + ", drawDate=" + drawDate
				+ ", numbers=" + numbers + ", bonusNumber=" + bonusNumber + "]";
	}

}
