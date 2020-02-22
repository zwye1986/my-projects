package com.venada.efinance.util;

public class LevelUtils {
	/**
	 * 根据积分获取等级
	 * @param i
	 * @return
	 */
	public static double getLevel(double i){
		double  result=(1/3.0);
		double r=Math.pow(i, result);
		if(r>100d){
			r=100d;
		}
		return r;
		
	}
	
	public static int getNextLevel(int i,int nextLevel){
		int result=0;
		result=(nextLevel*nextLevel*nextLevel*100+1)-i;
		return result;
	}
	
	public static int getNextLevel(int nowLevel){
		int result=0;
		result=(nowLevel*nowLevel*nowLevel*100+1);
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(getLevel(97809175/100.0));
		System.out.println(Math.ceil(LevelUtils.getLevel(94119200/100.0)));
		System.out.println(getNextLevel(97809175,100));
		System.out.println(getNextLevel(100));
		int level= (int)  Math.ceil(getLevel(100000001/100.0))/10+1;
		System.out.println( level);
	}

}
