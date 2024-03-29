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
					String out= runContainerScript(container_id);
					String[] contDetails=out.split(",");
					//String container_name = runContainerScript(container_id);
					String container_name = contDetails[0];
					System.out.println("container Name" + container_name);
					//String port = runContainerPort(container_id);
					String port = contDetails[1];
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
			// String out=saveWorkspace(container_id);
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
		return "Saved Workspace and Jupyter stopped";
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

	/*public String saveWorkspace(String container_id) {
		String output = null;

		try {
			File file = new File(getClass().getClassLoader().getResource("jupyter_save_work.sh").getFile());
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
	} */

	public JSONObject checkContainer() {
		String output = null;
		JSONObject object = new JSONObject();

		try {
			File file = new File(getClass().getClassLoader().getResource("check_container.sh").getFile());
			String cmd = "sh " + file;
			Runtime run = Runtime.getRuntime();
			Process pr = run.exec(cmd);
			pr.waitFor();
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = "";
			while ((line = buf.readLine()) != null) {
				output = line;
				System.out.println("out " + output);
			}
			if (output.contains("no")) {
				object.put("status","no");
			}
			else {
				String[] arr = output.split(",");
				System.out.println("port:" + arr[2]);
				System.out.println("id:" + arr[0]);
				System.out.println("name:" + arr[1]);
				object.put("status", "yes");
				object.put("container_id", arr[0]);
				object.put("container_name", arr[1]);
				object.put("port", arr[2]);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return object;
	}

}
