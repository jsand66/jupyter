package com.csvtojson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Output implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Message message = new Message();

	private List<Data> payload = new ArrayList<Data>();

	public List<Data> getPayload() {
		return payload;
	}

	public void setPayload(List<Data> payload) {
		this.payload = payload;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
