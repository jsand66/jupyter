package com.jupyter;

import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/filetransfer")
public class Controller {

	@RequestMapping(value = "/uploadCSV", method = RequestMethod.POST)
	public @ResponseBody String uploadCSV(@RequestParam String data, HttpServletResponse res,
			@RequestParam String filename, @RequestParam String container_id) {
		res.setHeader("Access-Control-Allow-Origin", "*");
		String base64toString = new String(Base64.decodeBase64(data));
		RemoteScript rs = new RemoteScript();
		try {
			FileWriter fw = new FileWriter("/tmp/" + filename);
			fw.write(base64toString);
			fw.close();
			rs.writeFile(container_id, filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "File Uploaded Successfully";
	}

	@RequestMapping(value = "/startJupyter", method = RequestMethod.GET)
	public @ResponseBody String startJupyter(HttpServletResponse res) {
		res.setHeader("Access-Control-Allow-Origin", "*");
		RemoteScript rs = new RemoteScript();
		JSONObject resposne = rs.startJupyter();
		return resposne.toString();
	}

	@RequestMapping(value = "/stopJupyter", method = RequestMethod.POST)
	public @ResponseBody String stopJupyter(@RequestParam String container_id, HttpServletResponse res) {
		res.setHeader("Access-Control-Allow-Origin", "*");
		RemoteScript rs = new RemoteScript();
		if (!container_id.isEmpty()) {
			String response = rs.stopJupyter(container_id);
			return "Jupyter Stopped";
		} else {
			return "Jupyter Stopped";
		}
	}

	@RequestMapping(value = "/checkContainer", method = RequestMethod.POST)
	public @ResponseBody String checkContainer(HttpServletResponse res)
	{
		res.setHeader("Access-Control-Allow-Origin", "*");
		RemoteScript rs = new RemoteScript();
		JSONObject response = null;
		response = rs.checkContainer();

		return response.toString();
	}
}
