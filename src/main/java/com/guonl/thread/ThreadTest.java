package com.guonl.thread;

public class ThreadTest extends Thread {
	int ticket = 10;

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			if (this.ticket > 0) {
				System.out.println(Thread.currentThread().getName() + "买票" + this.ticket--);
			}

		}

	}

}
