package com.venada.efinance.enumtype;

/**
 * 用户状态枚举类
 * 
 * @author sunwen
 */
public enum UserStatus {
	//初始状态是指注册成功，但是还没有登录过
	Init("初始",0),Normal("正常", 1), Frozen("冻结", 2), Cancel("注销", 3),freedom("自由套现",4),Unknow("未知",100);

	private String name;
	private int index;

	private UserStatus(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		String un = "N/A";
		for (UserStatus us : UserStatus.values()) {
			if (us.getIndex() == index) {
				un = us.name;
			}
		}
		return un;
	}
	
	public static UserStatus getEnum(int index) {
		for (UserStatus us : UserStatus.values()) {
			if (us.getIndex() == index) {
				return us;
			}
		}
		return UserStatus.Unknow;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public static void main(String args[]){
		UserStatus userStatus = getEnum(1);
		System.out.println(userStatus.index + "-" + userStatus.name);
		
	}
}