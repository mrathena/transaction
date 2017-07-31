package com.mrathena.transaction.tool;

public class ThreadKit {

	public static String getCurrentThreadName() {
		return Thread.currentThread().getName();
	}
	
	public static void currentThreadSleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {}
	}
	
}