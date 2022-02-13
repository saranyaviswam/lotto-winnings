/**
 * 
 */
package com.codechallenge.lottoapi.service;

import java.util.List;

import com.codechallenge.lottoapi.model.LottoVO;

/**
 * @author SARVISWA
 *
 */
public interface LottoService {

	LottoVO getLottoWinningDetails(List<Integer> userNumbers);
}
