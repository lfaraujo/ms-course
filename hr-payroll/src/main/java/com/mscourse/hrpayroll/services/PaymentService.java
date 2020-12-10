package com.mscourse.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mscourse.hrpayroll.entities.Payment;
import com.mscourse.hrpayroll.entities.Worker;

@Service
public class PaymentService {

	@Value("${hr-worker.host}")
	private String workerHost;

	@Autowired
	private RestTemplate restTemplate;

	public Payment getPayment(Long workerId, Integer days) {

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", workerId.toString());

		Worker worker = Optional
				.ofNullable(restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables))
				.orElse(new Worker());

		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}

}
