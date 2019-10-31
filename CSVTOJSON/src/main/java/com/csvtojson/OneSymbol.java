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

public class OneSymbol {

	public void symbolOne(String filepath, String outputFile) {
		File file = new File(filepath);
		BufferedReader br;
		Final fi = new Final();
		String message_type1 = "";
		String cap_time1 = "";
		String id1 = "";
		List<Output> ot = new ArrayList<Output>();
		List<Data> dl = new ArrayList<Data>();
		ObjectMapper mapper = new ObjectMapper();
		File myfile = new File(outputFile);
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				if (st.contains("REAL TIME TRADE")) {
					message_type1 = "REAL TIME TRADE";
					String[] arr = st.split("\\s{2,}");
					String[] cap_time = arr[1].split("=");
					String[] tfp_id = arr[2].split("=");
					cap_time1 = cap_time[1];
					id1 = tfp_id[1].replaceAll("-", "");
				} else if (st.contains("BACKGROUND")) {
					message_type1 = "BACKGROUND";
					String[] arr = st.split("\\s{2,}");
					String[] cap_time = arr[1].split("=");
					String[] tfp_id = arr[2].split("=");
					cap_time1 = cap_time[1];
					id1 = tfp_id[1].replaceAll("-", "");
				} else if (!st.isEmpty()) {
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
						Message msg = new Message();
						msg.setUpdate_type(message_type1);
						msg.setCap_time(cap_time1);
						msg.setTfp_id(id1);
						Output output = new Output();
						output.setPayload(dl);
						List<Data> d2 = new ArrayList<Data>(output.getPayload());
						output.setMessage(msg);
						output.setPayload((d2));
						ot.add(output);
						dl.clear();
					}
				}
			}
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
