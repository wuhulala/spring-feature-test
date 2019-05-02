package com.wuhulala.springboot.mybatis.model;

import java.io.Serializable;

public class Country implements Serializable {
	private Long id;
	private String countryname;
	private String countrycode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	@Override
	public String toString() {
		return "Country{" +
				"id=" + id +
				", countryname='" + countryname + '\'' +
				", countrycode='" + countrycode + '\'' +
				'}';
	}
}
