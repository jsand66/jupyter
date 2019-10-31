package com.csvtojson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MultipleSymbols {

	public void symbolMutlple(String filepath, String outputFile) {
		File file = new File(filepath);
		BufferedReader br;
		MultipleFinal fi = new MultipleFinal();
		String flavour = "";
		String feed = "";
		String cap_time1 = "";
		String src_ip = "";
		String src_port = "";
		String dst_ip = "";
		String dst_port = "";
		String system_id = "";
		String payload_length = "";
		String payload_compression = "";
		String tfp_id = "";
		List<MultipleOutput> ot = new ArrayList<MultipleOutput>();
		List<Data> dl = new ArrayList<Data>();
		ObjectMapper mapper = new ObjectMapper();
		File myfile = new File(outputFile);
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				if (st.contains("CAP TIME")) {
					String[] arr = st.split("\\s{4,}");
					flavour = arr[1];
					feed = arr[2];
					String[] cap_time = arr[3].split("=");
					String[] id = arr[4].split("=");
					cap_time1 = cap_time[1];
					tfp_id = id[1].replaceAll("-", "");
				} else if (st.contains("SRC IP")) {
					String[] arr = st.split(":");
					src_ip = arr[1];
				} else if (st.contains("SRC PORT")) {
					String[] arr = st.split(":");
					src_port = arr[1];
				} else if (st.contains("DST IP")) {
					String[] arr = st.split(":");
					dst_ip = arr[1];
				} else if (st.contains("DST PORT")) {
					String[] arr = st.split(":");
					dst_port = arr[1];
				} else if (st.contains("SYSTEM ID")) {
					String[] arr = st.split(":");
					system_id = arr[1];
				} else if (st.contains("PAYLOAD LENGTH")) {
					String[] arr = st.split(":");
					payload_length = arr[1];
				} else if (st.contains("PAYLOAD COMPRESSION")) {
					String[] arr = st.split(":");
					payload_compression = arr[1];
				} else if (st.contains("TFP ID")) {
					System.out.println();
				} else if (!st.isEmpty()) {
					if (!st.contains("Record")) {
						if (!st.startsWith("-")) {
							Data d2 = new Data();
							String d1 = st.replaceAll("\\)", "\\)|").replaceAll("\\s{4,}", "");
							String data[] = d1.split("\\|");
							String[] val = data[1].replaceAll("\\(", "").replaceAll("\\)", "").split(" ");
							d2.setLrt_type(data[1]);
							d2.setFormat(data[0]);
							d2.setLrt_token_id(val[1]);
							d2.setLrt_value(data[2]);
							dl.add(d2);
						} else {
							MultipleOutput output = new MultipleOutput();
							output.setPayload(dl);
							List<Data> d2 = new ArrayList<Data>(output.getPayload());
							output.setPayload((d2));
							ot.add(output);
							dl.clear();
						}

					}

				}

			}
			MultipleModel model = new MultipleModel();
			model.setFlavour(flavour);
			model.setFeed(feed);
			model.setCap_time(cap_time1);
			model.setTfp_id(tfp_id);
			model.setSrc_ip(src_ip);
			model.setSrc_port(src_port);
			model.setDst_ip(dst_ip);
			model.setDst_port(dst_port);
			model.setSystem_id(system_id);
			model.setPayload_length(payload_length);
			model.setPayload_compression(payload_compression);
			fi.setMessage(model);
			fi.setOutput(ot);
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.writeValue(myfile, fi);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
