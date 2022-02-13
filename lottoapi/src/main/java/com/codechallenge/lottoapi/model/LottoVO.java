package com.codechallenge.lottoapi.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LottoVO {

	private List<LottoWinningDeatilsVO> winningDetails = new ArrayList<>();
	private BigDecimal totalWon = new BigDecimal(0);
	private BigDecimal totalSpent = new BigDecimal(0);
	private BigDecimal netWonOrLoss = new BigDecimal(0);
	
	public List<LottoWinningDeatilsVO> getWinningDetails() {
		return winningDetails;
	}
	public void addWinningDetails(LottoWinningDeatilsVO winningDetails) {
		this.winningDetails.add(winningDetails);
	}
	public BigDecimal getTotalWon() {
		return totalWon;
	}
	public void addTotalWon(BigDecimal totalWon) {
		this.totalWon = this.totalWon.add(totalWon);
	}
	public BigDecimal getTotalSpent() {
		return totalSpent;
	}
	public void setTotalSpent(BigDecimal totalSpent) {
		this.totalSpent = totalSpent;
	}
	public BigDecimal getNetWonOrLoss() {
		return netWonOrLoss;
	}
	public void setNetWonOrLoss(BigDecimal netWonOrLoss) {
		this.netWonOrLoss = netWonOrLoss;
	}
	
	
}
