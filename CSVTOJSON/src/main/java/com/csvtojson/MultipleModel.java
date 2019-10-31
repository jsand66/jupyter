package com.csvtojson;

import java.io.Serializable;

public class MultipleModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String flavour = "";

	private String feed = "";

	private String cap_time = "";

	private String tfp_id = "";

	private String src_ip = "";

	private String src_port = "";

	private String dst_ip = "";

	private String dst_port = "";

	private String system_id = "";

	private String payload_length = "";

	private String payload_compression = "";

	public String getFlavour() {
		return flavour;
	}

	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}

	public String getFeed() {
		return feed;
	}

	public void setFeed(String feed) {
		this.feed = feed;
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

	public String getSrc_ip() {
		return src_ip;
	}

	public void setSrc_ip(String src_ip) {
		this.src_ip = src_ip;
	}

	public String getSrc_port() {
		return src_port;
	}

	public void setSrc_port(String src_port) {
		this.src_port = src_port;
	}

	public String getDst_ip() {
		return dst_ip;
	}

	public void setDst_ip(String dst_ip) {
		this.dst_ip = dst_ip;
	}

	public String getDst_port() {
		return dst_port;
	}

	public void setDst_port(String dst_port) {
		this.dst_port = dst_port;
	}

	public String getSystem_id() {
		return system_id;
	}

	public void setSystem_id(String system_id) {
		this.system_id = system_id;
	}

	public String getPayload_length() {
		return payload_length;
	}

	public void setPayload_length(String payload_length) {
		this.payload_length = payload_length;
	}

	public String getPayload_compression() {
		return payload_compression;
	}

	public void setPayload_compression(String payload_compression) {
		this.payload_compression = payload_compression;
	}

}
