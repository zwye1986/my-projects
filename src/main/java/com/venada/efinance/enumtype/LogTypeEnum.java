package com.venada.efinance.enumtype;

/**
 * 日志类型
 * @author yma
 */
public enum LogTypeEnum {
	SystemConfig("系统配置参数", 1), SecurityCenter("用户安全中心", 2),GameConfirm("用户领取游戏",3),OrderKS("酷刷订单",4),MessageRule("短信接受配置",5),RechargeLog("用户充值",6),Unknow("未知",100);

	private String name;
	private int index;

	private LogTypeEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		String un = "N/A";
		for (LogTypeEnum us : LogTypeEnum.values()) {
			if (us.getIndex() == index) {
				un = us.name;
			}
		}
		return un;
	}
	
	public static LogTypeEnum getEnum(int index) {
		for (LogTypeEnum us : LogTypeEnum.values()) {
			if (us.getIndex() == index) {
				return us;
			}
		}
		return LogTypeEnum.Unknow;
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