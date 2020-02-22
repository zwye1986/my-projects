package com.venada.efinance.pojo;



/***
 * 
 * @author xupei
 * 
 */
public class GameForAPI {
	/**
	 * 
	 */
	

	private String id;
	private String type; // 类型

	private String gameDescrip; // 游戏描述

	private String gameName; // 游戏名称
	private int clickNum; // 游戏点击次数

	private String square;
	private String rectangle;

	// 复合属性
	private int deposit; // 押金
	private int clicks; // 周期
	private double reward; // 奖励纳币
	private int punish; // 罚金
	private int players;//游戏人数

	private String typeName; // 类型名称
	
	private int isMember;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGameDescrip() {
		return gameDescrip;
	}

	public void setGameDescrip(String gameDescrip) {
		this.gameDescrip = gameDescrip;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}

	public String getSquare() {
		return square;
	}

	public void setSquare(String square) {
		this.square = square;
	}

	public String getRectangle() {
		return rectangle;
	}

	public void setRectangle(String rectangle) {
		this.rectangle = rectangle;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getIsMember() {
		return isMember;
	}

	public void setIsMember(int isMember) {
		this.isMember = isMember;
	}

	public int getPlayers() {
		return players;
	}

	public void setPlayers(int players) {
		this.players = players;
	}
	
	
	

}
