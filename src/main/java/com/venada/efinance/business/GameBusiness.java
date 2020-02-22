package com.venada.efinance.business;


import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.LessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
/***
 * 
 * @author xupei
 *
 */

public interface GameBusiness {
	
	public void addGame(Game obj) throws  BusinessException;
	
	public void addGamePolicy(GamePolicy obj) throws  BusinessException;
	
	public void delGame(Map<String,Object> map) throws  BusinessException;
	
	public void delVersionById(String id) throws  BusinessException;
	
	public void delGameById(String id) throws  BusinessException;
	
	public void delRecommendGameById(String id) throws  BusinessException;
	
	public void addGameRank(GameRank obj) throws  BusinessException;
	
	public void addGamePic(GamePic gamePic) throws  BusinessException;
	
	public void delGamePic(Map<String,Object> map) throws  BusinessException;
	
	public void addGameRecommend(GameRecommend obj) throws  BusinessException;
	
	public void delGameRecommend(Map<String,Object> map) throws  BusinessException;
	
	public void delGameRank(Map<String,Object> map) throws  BusinessException;
	
	public void addGameRankByJob(GameRank obj) throws  BusinessException;
	
	public void addGameRecommendByJob(GameRecommend obj) throws  BusinessException;
	
	public Game getGame(String gamename) throws  BusinessException; 
	
	public Version getVersion(String id) throws  BusinessException; 
	
	public Game getGameById(String id) throws  BusinessException;
	
	public void updateGame(Game game) throws  BusinessException; 
	
	public void updateGameRecommend(GameRecommend gameRecommend) throws  BusinessException; 
	
	public void updateUserGameRelation(UserGameRelation userGameRelation) throws  BusinessException; 
	
	public void updateGamePolicy(GamePolicy obj) throws  BusinessException; 
	
	public GamePic getGamePic(String id) throws  BusinessException;
	
	public Game getGameDetail(String id) throws  BusinessException; 
	
	public GameRank getGameRank(String gamename) throws  BusinessException; 
	
	public GamePolicy getGamePolicyByGameId(String gameId) throws  BusinessException; 
	
	public GamePolicy getGamePolicyByRelationId(String relationId) throws  BusinessException; 
	
	public UserGameRelation getUserGameRelationById(String id) throws  BusinessException; 
	
	public List<UserGameRelation> queryUserGameRelationByIds(List<String> ids) throws  BusinessException; 
	
	public GameRecommend getGameRecommend(String gamename) throws  BusinessException; 
	
	public GameRecommend getGameRecommendById(String id) throws  BusinessException; 
	
	public void addGameByJob(Game obj) throws  BusinessException;
	
	public void addUserGameRelationList(List<UserGameRelation> list) throws  BusinessException;
	
	public void addGameLogList(List<GameLog> list) throws  BusinessException;
	
	public List<GameLog> queryGameLog(Map<String,Object> map) throws  BusinessException;
	
	public List<UserGameRelation> queryUserGameRelationList(Map<String,Object> map) throws  BusinessException;
	
	public String addGameType(GameType obj) throws  BusinessException;
	
	public GameType getGameType(GameType obj) throws  BusinessException;
	
	public List<GameRank> queryGameRanks(Map<String,Object> map) throws  BusinessException;
	
	public List<GamePic> queryGamePics(Map<String,Object> map) throws  BusinessException;
	
	public List<GameRank> queryGameRanksInIndex() throws  BusinessException;
	
	public List<Game> queryGameForRank(Map<String,Object> map) throws  BusinessException;
	
	public List<GameRecommend> queryGameRecommendInIndex() throws  BusinessException;
	
	public List<GameRecommend> queryGameRecommendByRand() throws  BusinessException;
	
	public List<Game> queryOtherGames(String gameId) throws  BusinessException;
	
	public List<GameRecommend> queryGameRecommendAPI(Map<String,Object> map) throws  BusinessException;
	
	public List<GameRecommend> queryGameRecommendInSearch() throws  BusinessException;
	
	public List<Game> queryGames(Map<String,Object> map) throws  BusinessException;
	
	public List<Game> queryGamesByRand(Map<String,Object> map) throws  BusinessException;
	
	public List<Game> queryUnionGames() throws  BusinessException;
	
	public List<Game> querySixGamesByReward() throws  BusinessException;
	
	public List<GameRecommend> queryGameRecommend(Map<String,Object> condition,PaginationMore page) throws  BusinessException;
	
	public List<Game> queryGames(Map<String,Object> condition,PaginationMore page) throws BusinessException ;
	
	public List<Version> queryVersions(Map<String,Object> condition,PaginationMore page) throws BusinessException ;
	
	public List<Game> queryGamesByIdList(List<String> list) throws  BusinessException;
	
	public List<Game> queryGamesByUserList(List<String> list) throws  BusinessException;
	
	public int getGamesCounts(Map<String,Object> map) throws  BusinessException;
	
	public int getVersionCounts(Map<String,Object> map) throws  BusinessException;
	
	public int getRecommendGamesCounts(Map<String, Object> map) throws BusinessException;
	
	public int getGamePlayCounts(String gameId) throws BusinessException;
	
	public void addGameRecommend(Game game)  throws BusinessException;
	
	public List<UserGameRelation> queryAllGameTaskDetails(Map<String,Object> condition,PaginationMore page) throws BusinessException ;
	
	public List<UserGameRelation> queryNewAllGameTaskDetails(Map<String,Object> condition) throws BusinessException ;
	
	public List<UserGameRelation> queryAllGameTaskDetailsOptimize(Map<String,Object> condition,PaginationMore page) throws BusinessException ;
	
	public List<UserGameRelation> queryAllGameTaskDetails(Map<String,Object> condition) throws BusinessException ;
	
	public UserGameRelation getUserGameBenefitInfo (Map<String,Object> condition) throws BusinessException ;

	public int getAllGameTaskDetailsCount(Map<String, Object> condition) throws BusinessException;
	
	public List<GameType> queryAllGameType(Map<String,Object> map) throws BusinessException;
	
	public String[] confirmPlay(int num,User user,Game game,String addr) throws BusinessException,LessException, ParseException;
	
	public int getRestNum(String seq) throws BusinessException,LessException, ParseException;

	
	public int startPlay(String ids) throws BusinessException;
	
	public void finishTask(String ids,String addr) throws BusinessException;
	
	public void gameOp() throws BusinessException;
	
	public boolean payment(String addr) throws Exception;
	
	public List<LoginStatus> queryLoginStatus(String mobile)throws Exception;
	
	public void addLoginStatus(LoginStatus status) throws BusinessException;
	
	public List<Game> queryVipGamesForSimple(Map<String,Object> map) throws BusinessException;
	
	public BigDecimal getGameAmountTotalByUserid(Map<String,Object> condition)  throws BusinessException;

	public BigDecimal getGameRewardTotalByUserid(Map<String,Object> condition)  throws BusinessException;
	
	public int getChristmasGameRest() throws BusinessException;
	
	public int getTaskNum(Map<String,Object> map) throws BusinessException;
}
