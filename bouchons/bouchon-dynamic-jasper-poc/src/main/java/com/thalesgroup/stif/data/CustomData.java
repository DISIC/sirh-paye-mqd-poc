package com.thalesgroup.stif.data;

import java.math.BigDecimal;
import java.util.Date;

public class CustomData {

	private String item;

	private int quantity;

	private BigDecimal price;

	private Date orderDate;

	public String getItem() {
		return item;
	}

	public void setItem(final String item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(final int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(final Date orderDate) {
		this.orderDate = orderDate;
	}

}
