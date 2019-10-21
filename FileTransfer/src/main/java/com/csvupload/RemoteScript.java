package com.csvupload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class RemoteScript {

	public String startJupyter() {
		String output = null;
		try {
			JSch jsch = new JSch();
			Session session;
			InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
			Properties env = new Properties();
			env.load(input);

			session = jsch.getSession(env.getProperty("remote.user"), env.getProperty("remote.ip.address"), 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(env.getProperty("remote.user.password"));
			session.connect();

			ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
			InputStream in = channelExec.getInputStream();
			channelExec.setCommand("sh " + env.getProperty("remote.script.jupyter.start"));
			channelExec.connect();

			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				output = line;
			}
			int exitStatus = channelExec.getExitStatus();
			if (exitStatus > 0) {
				System.out.println("Remote script exec error! " + exitStatus);
			}
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

	public String stopJupyter(String container_id) {
		try {
			JSch jsch = new JSch();
			Session session;
			InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
			Properties env = new Properties();
			env.load(input);
			session = jsch.getSession(env.getProperty("remote.user"), env.getProperty("remote.ip.address"), 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(env.getProperty("remote.user.password"));
			session.connect();
			ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
			channelExec.setCommand("sh " + env.getProperty("remote.script.jupyter.stop") + " " + container_id);
			channelExec.connect();
			InputStream in = channelExec.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			int exitStatus = channelExec.getExitStatus();
			if (exitStatus > 0) {
				System.out.println("Remote script exec error! " + exitStatus);
			}
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Jupyter stopped";
	}

	public void writeFile(String container_id,String fileName) {
		try {
			JSch jsch = new JSch();
			Session session;
			InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
			Properties env = new Properties();
			env.load(input);
			session = jsch.getSession(env.getProperty("remote.user"), env.getProperty("remote.ip.address"), 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(env.getProperty("remote.user.password"));
			session.connect();
			ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
			channelExec.setCommand("sh " + env.getProperty("remote.script.jupyter.copyfile") + " " + container_id+" "+fileName);
			channelExec.connect();
			InputStream in = channelExec.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			int exitStatus = channelExec.getExitStatus();
			if (exitStatus > 0) {
				System.out.println("Remote script exec error! " + exitStatus);
			}
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
