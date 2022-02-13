package com.codechallenge.lottoapi.model;

import java.math.BigDecimal;
import java.util.Date;

public class LottoWinningDeatilsVO {

	private String winningDate;
	private Integer amount;
	
	public LottoWinningDeatilsVO(String winningDate, Integer amount) {
		super();
		this.winningDate = winningDate;
		this.amount = amount;
	}
	public String getWinningDate() {
		return winningDate;
	}
	public void setWinningDate(String winningDate) {
		this.winningDate = winningDate;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
