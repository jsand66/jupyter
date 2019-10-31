package com.csvtojson;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String update_type = "";

	private String cap_time = "";

	private String tfp_id = "";

	public String getUpdate_type() {
		return update_type;
	}

	public void setUpdate_type(String update_type) {
		this.update_type = update_type;
	}

	public String getCap_time() {
		return cap_time;
	}

	public void setCap_time(String cap_time) {
		this.cap_time = cap_time;
	}

	public String getTfp_id() {
		return tfp_id;
	}

	public void setTfp_id(String tfp_id) {
		this.tfp_id = tfp_id;
	}

}
