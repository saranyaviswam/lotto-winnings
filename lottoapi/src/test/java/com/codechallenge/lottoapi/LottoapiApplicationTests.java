package com.codechallenge.lottoapi;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = LottoapiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LottoapiApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port + "/api/v1";
	}
	
	@Test
	void contextLoads() {
		Assertions.assertNotNull(restTemplate);
	}

	@Test
	void testGetLottoWins_case1() {
		int num1=8,num2=17,num3=45,num4=32,num5=20,num6=3;
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/lottoResults?n1="+num1+"&n2="+num2+"&n3="+num3+"&n4="+num4+"&n5="+num5+"&n6="+num6,
				HttpMethod.GET, entity, String.class);
		
		String responseStr =response.getBody();

		Assertions.assertNotNull(responseStr);
		assertValueFromResponse(responseStr);
		Assertions.assertEquals(200,response.getStatusCodeValue());
	}
	
	//case with bonus number
	@Test
	void testGetLottoWins_case2() {
		int num1=3,num2=9,num3=10,num4=20,num5=34,num6=42;
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/lottoResults?n1="+num1+"&n2="+num2+"&n3="+num3+"&n4="+num4+"&n5="+num5+"&n6="+num6,
				HttpMethod.GET, entity, String.class);
		
		String responseStr =response.getBody();

		Assertions.assertNotNull(responseStr);
		assertValueFromResponse(responseStr);
		Assertions.assertEquals(200,response.getStatusCodeValue());
	}
	
	private void assertValueFromResponse(String responseString) {
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(responseString);
			Assertions.assertNotNull(jsonObject.getInt("totalWon"));
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
	}
}
