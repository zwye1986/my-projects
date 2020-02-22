package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;


public class GamePic extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private byte[] gamePic;
	private String gameId;
    private int type; //图片类型
    
    public static final int Square = 1;
    public static final int Rectangle = 2;
    public static final int Other = 3;
	

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public byte[] getGamePic() {
		return gamePic;
	}

	public void setGamePic(byte[] gamePic) {
		this.gamePic = gamePic;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
