package com.mscourse.hrpayroll.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mscourse.hrpayroll.entities.Payment;
import com.mscourse.hrpayroll.entities.Worker;
import com.mscourse.hrpayroll.feignclients.WorkerFeignClient;

import feign.FeignException;

@Service
public class PaymentService {

	@Autowired
	private WorkerFeignClient workerFeignClient;

	public Payment getPayment(Long workerId, Integer days) {

		try {

			ResponseEntity<Worker> workerResponse = workerFeignClient.findById(workerId);

			Worker worker = Optional.ofNullable(workerResponse.getBody()).orElse(new Worker());

			return new Payment(worker.getName(), worker.getDailyIncome(), days);

		} catch (FeignException e) {
			return new Payment();
		}
	}

}
