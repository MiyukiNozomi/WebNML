package org.webnml.server;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomSTDOUT extends PrintStream {

	private String prefix;
	private DateTimeFormatter dtf = DateTimeFormatter
			.ofPattern("dd/MM/yyyy-HH:mm:ss");

	public CustomSTDOUT(PrintStream sst, String prefix) {
		super(sst);
		this.prefix = prefix;
	}

	@Override
	public void println(String arg0) {
		String msg;
		if (arg0.contains(">>")) {
			msg = dtf.format(LocalDateTime.now()) + " [" + prefix + "]"
					+ Thread.currentThread().getName() + "::" + arg0;
		} else {
			msg = dtf.format(LocalDateTime.now()) + " [" + prefix + "]"
					+ Thread.currentThread().getName() + " > " + arg0;
		}
		super.println(msg);
	}

	@Override
	public void println(int arg0) {
		super.println(dtf.format(LocalDateTime.now()) + " [" + prefix + "]"
				+ Thread.currentThread().getName() + " > " + arg0);
	}
	
	@Override
	public void println(char[] arg0) {
		super.println(dtf.format(LocalDateTime.now()) + " [" + prefix + "]"
				+ Thread.currentThread().getName() + " > " + new String(arg0));
	}
	
	@Override
	public void println(Object arg0) {
		super.println(dtf.format(LocalDateTime.now()) + " [" + prefix + "]"
				+ Thread.currentThread().getName() + " > " + arg0);
	}
	
	@Override
	public void println(float arg0) {
		super.println(dtf.format(LocalDateTime.now()) + " [" + prefix + "]"
				+ Thread.currentThread().getName() + " > " + arg0);
	}
	
	@Override
	public void println(double arg0) {
		super.println(dtf.format(LocalDateTime.now()) + " [" + prefix + "]"
				+ Thread.currentThread().getName() + " > " + arg0);
	}
	
	@Override
	public void println(boolean arg0) {
		super.println(dtf.format(LocalDateTime.now()) + " [" + prefix + "]"
				+ Thread.currentThread().getName() + " > " + arg0);
	}
	
	@Override
	public void println(char arg0) {
		super.println(dtf.format(LocalDateTime.now()) + " [" + prefix + "]"
				+ Thread.currentThread().getName() + " > " + arg0);
	}
	
	@Override
	public void println(long arg0) {
		super.println(dtf.format(LocalDateTime.now()) + " [" + prefix + "]"
				+ Thread.currentThread().getName() + " > " + arg0);
	}

	public String getPrefix() {
		return prefix;
	}

}
