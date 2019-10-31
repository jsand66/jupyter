package com.csvtojson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MultipleFinal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MultipleModel message = new MultipleModel();

	private List<MultipleOutput> output = new ArrayList<MultipleOutput>();

	public List<MultipleOutput> getOutput() {
		return output;
	}

	public void setOutput(List<MultipleOutput> output) {
		this.output = output;
	}

	public MultipleModel getMessage() {
		return message;
	}

	public void setMessage(MultipleModel message) {
		this.message = message;
	}

}
