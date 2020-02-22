package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;

/**
 * 
 * @author xupei
 * 
 */
public class GamePolicy extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private String gameId; // 游戏ID
	private int clicks; // 点击次数
	private double reward; // 奖励纳币
	private int punish; // 惩罚纳币
	private int deposit; //押金

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public double getReward() {
		return reward;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}

	public int getPunish() {
		return punish;
	}

	public void setPunish(int punish) {
		this.punish = punish;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	
	

}
