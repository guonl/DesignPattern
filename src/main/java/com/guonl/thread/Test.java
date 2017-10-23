package com.guonl.thread;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		ThreadTest t1 = new ThreadTest();
		ThreadTest t2 = new ThreadTest();
		ThreadTest t3 = new ThreadTest();
		t1.start();
		t2.start();
		t3.start();
		Thread.sleep(1000);
		System.out.println("====================");
		RunableTest r1 = new RunableTest();
		new Thread(r1).start();
		new Thread(r1).start();
		new Thread(r1).start();

	}
}
