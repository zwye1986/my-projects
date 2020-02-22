package com.venada.efinance.enumtype;

/**
 * 交易类型枚举类
 * 
 * @author sunwen
 */
public enum DealType {
	T("提现手续费",1),
	G("游戏返利", 2), 
	D("短信费用", 3), 
	K("开启会员中心功能费", 4),
	Y("游戏押金", 5),
	R("充值", 6),
	W("提现", 7),
	P("游戏罚金", 8),
	B("邀请奖励", 9),
    Z("支持众筹项目金额", 0),
    V("赠送好友VIP费用", 10),
    N("续费VIP会员", 11),
    C("提现失败的退款",12),
	Unknow("未知",100);

	private String name;
	private int index;

	private DealType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		String un = "N/A";
		for (DealType us : DealType.values()) {
			if (us.getIndex() == index) {
				un = us.name;
			}
		}
		return un;
	}
	
	public static DealType getEnum(int index) {
		for (DealType us : DealType.values()) {
			if (us.getIndex() == index) {
				return us;
			}
		}
		return DealType.Unknow;
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
		DealType userStatus = getEnum(1);
		System.out.println(userStatus.index + "-" + userStatus.name);
		
	}
}