package com.venada.efinance.weixin.service.test;

/**
 * 测试并发
 * @author hepei
 *
 */
public class ThreadDemo {
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 2; ++i) {
			new Thread(new MyThread(i)).start();
		}
	}
}