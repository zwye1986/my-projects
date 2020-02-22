package com.venada.efinance.enumtype;

import java.util.LinkedHashMap;
import java.util.Map;

public enum TradeStatus {

	NoOrder {
		public String getName() {
			return "未形成订单";
		}
	},

	CreateOrder {
		public String getName() {
			return "形成订单";
		}
	},
	TradeCommit {
		public String getName() {
			return "完成交易";
		}
	},
	TradeCancel {
		public String getName() {
			return "订单取消";
		}
	},
	PayIng {
		public String getName() {
			return "支付中";
		}
	},
	PayFailure {
		public String getName() {
			return "支付失败";
		}
	},
	PayTimeOut {
		public String getName() {
			return "支付超时";
		}
	}
	;
	public abstract String getName();

	public Map<String, String> getMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		TradeStatus[] name = TradeStatus.class.getEnumConstants();
		for (TradeStatus s : name) {
			map.put(s.name(), s.getName());
		}
		return map;
	}
}
