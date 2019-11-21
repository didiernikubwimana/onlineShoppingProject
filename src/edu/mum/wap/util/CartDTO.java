package edu.mum.wap.util;

import edu.mum.wap.model.Product;

public class CartDTO {
	private Product product;
	private int quantity;
	private double totalAmount;

	public CartDTO(Product product, int quantity, double totalAmount) {
		this.product = product;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
