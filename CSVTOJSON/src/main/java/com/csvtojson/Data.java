package com.csvtojson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Data implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String format = "";

	private String lrt_token_id = "";

	private String lrt_type = "";

	private String lrtvalue = "";

	private List<LRTType> lrt_value = new ArrayList<LRTType>();

	public String getLrtvalue() {
		return lrtvalue;
	}

	public void setLrtvalue(String lrtvalue) {
		this.lrtvalue = lrtvalue;
	}

	public List<LRTType> getLrt_value() {
		return lrt_value;
	}

	public void setLrt_value(List<LRTType> lrt_value) {
		this.lrt_value = lrt_value;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getLrt_token_id() {
		return lrt_token_id;
	}

	public void setLrt_token_id(String lrt_token_id) {
		this.lrt_token_id = lrt_token_id;
	}

	public String getLrt_type() {
		return lrt_type;
	}

	public void setLrt_type(String lrt_type) {
		this.lrt_type = lrt_type;
	}

}
