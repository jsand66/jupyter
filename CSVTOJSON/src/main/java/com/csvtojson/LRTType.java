package com.csvtojson;

import java.io.Serializable;

public class LRTType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String lrt_type = "";

	private String lrtvalue = "";

	private String lrt_token = "";

	public String getLrt_token() {
		return lrt_token;
	}

	public void setLrt_token(String lrt_token) {
		this.lrt_token = lrt_token;
	}

	public String getLrt_type() {
		return lrt_type;
	}

	public void setLrt_type(String lrt_type) {
		this.lrt_type = lrt_type;
	}

	public String getLrtvalue() {
		return lrtvalue;
	}

	public void setLrtvalue(String lrtvalue) {
		this.lrtvalue = lrtvalue;
	}

}
