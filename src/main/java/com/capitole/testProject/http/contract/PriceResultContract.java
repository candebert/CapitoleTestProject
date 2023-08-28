package com.capitole.testProject.http.contract;

import com.capitole.testProject.core.domain.PriceResult;

import java.util.Date;

public class PriceResultContract {
	private Date application_date;
	private int brand_id;
	private Double price;
	private int price_list;
	private int product_id;

	public PriceResultContract(Date application_date, int brand_id, Double price, int price_list, int product_id) {
		this.application_date = application_date;
		this.brand_id = brand_id;
		this.price = price;
		this.price_list = price_list;
		this.product_id = product_id;
	}

}
