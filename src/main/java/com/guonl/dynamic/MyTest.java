package com.guonl.dynamic;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

public class MyTest implements Runnable {

	public MyTest() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		System.out.println("do Test run()...");
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print(
					"\nPls enter the input.\n1. do one more time\n0.exit the program\nonly accepted '1' or '0'\n~>");
			String in = sc.next();
			if ("1".equals(in)) {
				System.out.println("**********************************");
				System.out.println("Do load().");
				load();
				System.out.println("\n\n");
			} else if ("0".equals(in)) {
				System.out.println("Bye.");
				System.exit(0);
			} else {
				continue;
			}
		}
	}

	private void load() {
//		String jarName = "C.jar";
		try {
			A a = new A();
			a.action();
			System.out.println("********下面为静态*********");
			String name = a.getClass().getName();
//			Class aClass = Class.forName("com.guonl.dynamic.A");
			Class<?> aClass = Class.forName(name);
			IC ic = (IC)aClass.newInstance();
			ic.action();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		new MyTest();
	}

}
