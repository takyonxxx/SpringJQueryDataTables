package com.biliyor.model;

public enum CityNames {

	Ankara("Ankara"), Bilecik("Bilecik"), Eskisehir("Eskişehir");
	private String text;

	private CityNames(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}

