package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;
import com.venada.efinance.enumtype.LogTypeEnum;

public class OperationLog extends BaseEntity {

	private static final long serialVersionUID = 6677821653201522866L;
	
	private LogTypeEnum logType ;
	
	private String dataOld ;
	
	private String dataNew ;
	
	private String remark ;

	public int getLogType() {
		return logType.getIndex();
	}

	public void setLogType(int index) {
		this.logType = LogTypeEnum.getEnum(index);
	}

	public String getDataOld() {
		return dataOld;
	}

	public void setDataOld(String dataOld) {
		this.dataOld = dataOld;
	}

	public String getDataNew() {
		return dataNew;
	}

	public void setDataNew(String dataNew) {
		this.dataNew = dataNew;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
