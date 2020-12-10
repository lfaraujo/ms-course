package com.mscourse.hrpayroll.entities;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private Double dailyIncome;

	private Integer days;

	public Double getTotal() {
		return days * dailyIncome;
	}

}
