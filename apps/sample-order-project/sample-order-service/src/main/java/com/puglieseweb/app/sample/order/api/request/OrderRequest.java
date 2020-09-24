package com.puglieseweb.app.sample.order.api.request;

import java.util.List;

public class OrderRequest {

	private String creditCardNumber;
	private List<OrderItemRequest> items;
	private String orderLocation;

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public List<OrderItemRequest> getItems() {
		return items;
	}

	public String getOrderLocation() {
		return orderLocation;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public void setItems(List<OrderItemRequest> items) {
		this.items = items;
	}

	public void setOrderLocation(String orderLocation) {
		this.orderLocation = orderLocation;
	}

	@Override
	public String toString() {
		return "OrderRequest [orderLocation=" + orderLocation + ", creditCardNumber=" + creditCardNumber + ", items="
				+ items + "]";
	}

}
