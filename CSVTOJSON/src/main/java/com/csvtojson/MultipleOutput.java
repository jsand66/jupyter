package com.csvtojson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MultipleOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Data> payload = new ArrayList<Data>();

	public List<Data> getPayload() {
		return payload;
	}

	public void setPayload(List<Data> payload) {
		this.payload = payload;
	}

}
