package com.venada.efinance.enumtype;

/**
 * 用户状态枚举类
 * 
 * @author sunwen
 */
public enum CardTypeEnum {
	Debit("普通借记卡", 1), Credit("信用卡", 2),Unknow("未知",100);

	private String name;
	private int index;

	private CardTypeEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		String un = "N/A";
		for (CardTypeEnum us : CardTypeEnum.values()) {
			if (us.getIndex() == index) {
				un = us.name;
			}
		}
		return un;
	}
	
	public static CardTypeEnum getEnum(int index) {
		for (CardTypeEnum us : CardTypeEnum.values()) {
			if (us.getIndex() == index) {
				return us;
			}
		}
		return CardTypeEnum.Unknow;
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
}