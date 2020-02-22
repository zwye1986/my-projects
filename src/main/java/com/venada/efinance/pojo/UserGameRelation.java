package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class UserGameRelation extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String gameId;
	private int punish;
	private double reward;
	private int status;
	private BigDecimal banance;
	private Date invalidTime;

	private double realReward;
	private String gameName;
	private String relationId;
	private int playnum;
	private int clicks;
	private int deposit;
	private String gameUrl;
	
	private int policyClicks;
	private double policyReward;
	private int policyPunish;
	private int policyDeposit;
	
	private Date clickDate;

	private Game ecGame;
	public static final int InprocessStatus = 1;
	public static final int FinishedStatus = 2;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public int getPunish() {
		return punish;
	}

	public void setPunish(int punish) {
		this.punish = punish;
	}

	public double getReward() {
		return reward;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getRealReward() {
		return realReward;
	}

	public void setRealReward(double realReward) {
		this.realReward = realReward;
	}



	public int getPlaynum() {
		return playnum;
	}

	public void setPlaynum(int playnum) {
		this.playnum = playnum;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getGameUrl() {
		return gameUrl;
	}

	public void setGameUrl(String gameUrl) {
		this.gameUrl = gameUrl;
	}

	public BigDecimal getBanance() {
		return banance;
	}

	public void setBanance(BigDecimal banance) {
		this.banance = banance;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public int getPolicyClicks() {
		return policyClicks;
	}

	public void setPolicyClicks(int policyClicks) {
		this.policyClicks = policyClicks;
	}

	public double getPolicyReward() {
		return policyReward;
	}

	public void setPolicyReward(double policyReward) {
		this.policyReward = policyReward;
	}

	public int getPolicyPunish() {
		return policyPunish;
	}

	public void setPolicyPunish(int policyPunish) {
		this.policyPunish = policyPunish;
	}

	public int getPolicyDeposit() {
		return policyDeposit;
	}

	public void setPolicyDeposit(int policyDeposit) {
		this.policyDeposit = policyDeposit;
	}

	public Date getClickDate() {
		return clickDate;
	}

	public void setClickDate(Date clickDate) {
		this.clickDate = clickDate;
	}

	public Game getEcGame() {
		return ecGame;
	}

	public void setEcGame(Game ecGame) {
		this.ecGame = ecGame;
	}

	

}
