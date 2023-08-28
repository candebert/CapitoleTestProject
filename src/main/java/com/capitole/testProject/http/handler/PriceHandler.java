package com.capitole.testProject.http.handler;

import com.capitole.testProject.core.domain.PriceResult;
import com.capitole.testProject.core.infrastructure.exception.PriceNotFoundException;
import com.capitole.testProject.http.exception.InvalidDateFormat;
import com.capitole.testProject.http.provider.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class PriceHandler {

	private final Actions actions;

	@Autowired
	public PriceHandler(Actions actions) {
		this.actions = actions;
	}

	@GetMapping("/price-finder/price")
	public ResponseEntity<?> getPrice(
			@RequestParam String application_date,
			@RequestParam int product_id,
			@RequestParam int brand_id
	) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date parsedDate = validateDate(application_date, dateFormat);

			PriceResult price = actions.getPrice().invoke(parsedDate, brand_id, product_id);
			return ResponseEntity.ok(price.toContract());
		} catch (InvalidDateFormat invalidDateFormat) {
			return new ResponseEntity<>(invalidDateFormat.getMessage(), invalidDateFormat.getStatus());
		} catch (PriceNotFoundException priceNotFoundException) {
			return new ResponseEntity<>(priceNotFoundException.getMessage(), priceNotFoundException.getStatus());
		}
	}

	private static Date validateDate(String application_date, SimpleDateFormat dateFormat) {
		Date parsedDate;
		try {
			parsedDate = dateFormat.parse(application_date);
		} catch (ParseException e) {
			throw new InvalidDateFormat(application_date);
		}
		return parsedDate;
	}

}
