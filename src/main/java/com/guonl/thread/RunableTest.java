package com.guonl.thread;

public class RunableTest implements Runnable{
	int ticket =10;

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			if(this.ticket>0){
				System.out.println(Thread.currentThread().getName()+"卖出"+this.ticket--);
			}
		}
	}

}
