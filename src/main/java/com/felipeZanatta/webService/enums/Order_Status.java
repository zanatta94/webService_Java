package com.felipeZanatta.webService.enums;


// Atribuido valor aos enums
// valueOf percorre as opções desse enum, se exite returna qual é, se não gera a exceção


public enum Order_Status {
	
	WATTING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVDERED(4),
	CANCELED(5);
	
	
	private int code;
	
	private Order_Status(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static Order_Status valueOf(int code) {
		for(Order_Status value : Order_Status.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid order status code");
	}
}
