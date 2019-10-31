package com.csvtojson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Final implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Output> output = new ArrayList<Output>();

	public List<Output> getOutput() {
		return output;
	}

	public void setOutput(List<Output> output) {
		this.output = output;
	}

}
