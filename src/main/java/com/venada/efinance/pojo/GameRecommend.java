package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;

/***
 * 
 * @author xupei
 * 
 */
public class GameRecommend extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String developer; // 开发者
	private String type; // 类型
	private String language; // 语言
	private String theme; // 题材
	private String gameDescrip; // 游戏描述
	private String gameUrl; // 游戏地址
	private int playCounts = 0; // 游戏次数 默认为0
	private int category; // 游戏分类
	private String gamePicUrl;//默认游戏的图片外网地址
	private String gameName; //游戏名称
	private int recommendOrderBy; //游戏名称
	
	private int punish;
	private int deposit; //押金
	private int clicks; // 周期
	private double reward; // 奖励纳币
	private int isMember;  //默认0是普通游戏1是会员游戏
	private String gameId;
	private String square;
	private String rectangle;
	private String typeName;
	private String stars;
	

	public static final int FLASH_GAME = 1; // flash小游戏
	public static final int WEB_GAME = 2; // 网页游戏
	
	public static final String GAMETYPE_OTHER="其他";

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getGameDescrip() {
		return gameDescrip;
	}

	public void setGameDescrip(String gameDescrip) {
		this.gameDescrip = gameDescrip;
	}

	public String getGameUrl() {
		return gameUrl;
	}

	public void setGameUrl(String gameUrl) {
		this.gameUrl = gameUrl;
	}

	public int getPlayCounts() {
		return playCounts;
	}

	public void setPlayCounts(int playCounts) {
		this.playCounts = playCounts;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getGamePicUrl() {
		return gamePicUrl;
	}

	public void setGamePicUrl(String gamePicUrl) {
		this.gamePicUrl = gamePicUrl;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public double getReward() {
		return reward;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public int getPunish() {
		return punish;
	}

	public void setPunish(int punish) {
		this.punish = punish;
	}

	public int getRecommendOrderBy() {
		return recommendOrderBy;
	}

	public void setRecommendOrderBy(int recommendOrderBy) {
		this.recommendOrderBy = recommendOrderBy;
	}

	public int getIsMember() {
		return isMember;
	}

	public void setIsMember(int isMember) {
		this.isMember = isMember;
	}
	
	

}
