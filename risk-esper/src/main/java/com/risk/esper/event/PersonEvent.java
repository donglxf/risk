package com.risk.esper.event;

public class PersonEvent {

	private String itemName;
	private double price;

	public PersonEvent(String itemName, double price) {
		this.itemName = itemName;
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public double getPrice() {
		return price;
	}

}
