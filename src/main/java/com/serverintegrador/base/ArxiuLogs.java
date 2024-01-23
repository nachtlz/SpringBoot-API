package com.serverintegrador.base;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

public class ArxiuLogs {
	static private final String NOMARX = "logs.txt";
	
	static public void guardarExcepcio(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(NOMARX, true));
			bw.newLine();
			bw.newLine();
			bw.write("************** ERROR ************* " + (new Date()).toString());
			bw.newLine();
			bw.write(sw.toString());
			bw.newLine();
			bw.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	static public void guardarMissatge(String msg) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(NOMARX, true));
			bw.newLine();
			bw.newLine();
			bw.write("+++++++++++++++ MSG +++++++++++++++ " + (new Date()).toString());
			bw.newLine();
			bw.write(msg);
			bw.newLine();
			bw.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
}
