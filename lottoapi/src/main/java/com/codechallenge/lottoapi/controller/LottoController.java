package com.codechallenge.lottoapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codechallenge.lottoapi.model.LottoVO;
import com.codechallenge.lottoapi.service.LottoService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/v1")
public class LottoController {
	
	@Autowired private LottoService lottoService;

	@GetMapping("/lottoResults")
	public ResponseEntity<LottoVO> getLottoWins(
			@RequestParam(value = "n1") @Max(49) @Min(1) int n1, 
			@RequestParam(value = "n2") @Max(49) @Min(1) int n2, 
			@RequestParam(value = "n3") @Max(49) @Min(1) int n3, 
			@RequestParam(value = "n4") @Max(49) @Min(1) int n4, 
			@RequestParam(value = "n5") @Max(49) @Min(1) int n5, 
			@RequestParam(value = "n6") @Max(49) @Min(1) int n6 
			)  {
		
		List<Integer> userNumbers = new ArrayList<Integer>();
		userNumbers.add(n1);
		userNumbers.add(n2);
		userNumbers.add(n3);
		userNumbers.add(n4);
		userNumbers.add(n5);
		userNumbers.add(n6);
		System.out.println("Numbers are >>"+n1+ " "+n2+ " "+n3+ " "+n4+ " "+n5+ " "+n6+ " ");
		LottoVO lottoVO = lottoService.getLottoWinningDetails(userNumbers);
		
		return ResponseEntity.ok().body(lottoVO);
	}
}
