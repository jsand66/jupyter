package com.jupyter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONObject;

public class RemoteScript {

	public JSONObject startJupyter() {
		String output = null;
		JSONObject object = new JSONObject();
		try {
			File file = new File(getClass().getClassLoader().getResource("jupyter_start.sh").getFile());
			String cmd = "sh " + file;
			Runtime run = Runtime.getRuntime();
			Process pr = run.exec(cmd);
			pr.waitFor();
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = "";

			while ((line = buf.readLine()) != null) {
				output = line;
				System.out.println("out " + output);
				if (!output.isEmpty()) {
					String container_id = output.replaceAll("\\s", "");
					String container_name = runContainerScript(container_id);
					System.out.println("container Name" + container_name);
					String port = runContainerPort(container_id);
					System.out.println("port:" + port);
					object.put("container_id", container_id);
					object.put("container_name", container_name);
					object.put("port", port);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return object;
	}

	public String stopJupyter(String container_id) {
		try {
			System.out.println("contid:" + container_id);
			File file = new File(getClass().getClassLoader().getResource("jupyter_stop.sh").getFile());
			String cmd = "sh " + file + " " + container_id;
			System.out.println("cmd:" + cmd);

			Runtime run = Runtime.getRuntime();
			Process pr = run.exec(cmd);
			pr.waitFor();
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = "";
			while ((line = buf.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Jupyter stopped";
	}

	public void writeFile(String container_id, String fileName) {
		try {
			File file = new File(getClass().getClassLoader().getResource("jupyter_copy.sh").getFile());
			String cmd = "sh " + file + " " + container_id + " " + fileName;
			Runtime run = Runtime.getRuntime();
			Process pr = run.exec(cmd);
			pr.waitFor();
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = "";
			while ((line = buf.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String runContainerScript(String container_id) {
		String output = null;

		try {
			File file = new File(getClass().getClassLoader().getResource("jupyter_container.sh").getFile());
			String cmd = "sh " + file + " " + container_id;
			Runtime run = Runtime.getRuntime();
			Process pr = run.exec(cmd);
			pr.waitFor();
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = "";
			while ((line = buf.readLine()) != null) {
				output = line;
				System.out.println("out " + output);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return output;
	}

	public String runContainerPort(String container_id) {
		String output = null;

		try {
			File file = new File(getClass().getClassLoader().getResource("jupyter_container_port.sh").getFile());
			String cmd = "sh " + file + " " + container_id;
			Runtime run = Runtime.getRuntime();
			Process pr = run.exec(cmd);
			pr.waitFor();
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = "";
			while ((line = buf.readLine()) != null) {
				output = line;
				System.out.println("out " + output);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return output;
	}

}
