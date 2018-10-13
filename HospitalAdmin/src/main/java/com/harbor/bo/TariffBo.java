package com.harbor.bo;

public class TariffBo {
	
	private long tariff_id;
	private long rate_id;
	private long hid;
	private String tariff_name;
	private String service_name;
	private String service_category;
	private String rates;
	private String doctor_name;
	private String department;
	private Boolean is_mandatory;
	

	public long getTariff_id() {
		return tariff_id;
	}

	public void setTariff_id(long tariff_id) {
		this.tariff_id = tariff_id;
	}

	public long getRate_id() {
		return rate_id;
	}

	public void setRate_id(long rate_id) {
		this.rate_id = rate_id;
	}

	public long getHid() {
		return hid;
	}

	public void setHid(long hid) {
		this.hid = hid;
	}

	public String getTariff_name() {
		return tariff_name;
	}

	public void setTariff_name(String tariff_name) {
		this.tariff_name = tariff_name;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getService_category() {
		return service_category;
	}

	public void setService_category(String service_category) {
		this.service_category = service_category;
	}

	public String getRates() {
		return rates;
	}

	public void setRates(String rates) {
		this.rates = rates;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Boolean getIs_mandatory() {
		return is_mandatory;
	}

	public void setIs_mandatory(Boolean is_mandatory) {
		this.is_mandatory = is_mandatory;
	}


}
