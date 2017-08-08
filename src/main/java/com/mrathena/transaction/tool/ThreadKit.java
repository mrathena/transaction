package com.mrathena.transaction.tool;

public class ThreadKit {

	public static String getName() {
		return Thread.currentThread().getName();
	}
	
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {}
	}
	
}