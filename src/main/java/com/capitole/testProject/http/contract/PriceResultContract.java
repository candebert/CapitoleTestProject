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

	public Date getApplication_date() {
		return application_date;
	}

	public void setApplication_date(Date application_date) {
		this.application_date = application_date;
	}

	public int getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getPrice_list() {
		return price_list;
	}

	public void setPrice_list(int price_list) {
		this.price_list = price_list;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
}
