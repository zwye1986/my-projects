CREATE PROCEDURE `clearUserInfo`(IN _mobileNumber varchar(20))
BEGIN
 DECLARE _userid varchar(255);
 DECLARE _inviteCodeFromOther VARCHAR(255);
	select id from ec_user where mobileNumber = _mobileNumber INTO _userid;
	select inviteCodeSelf from ec_user where mobileNumber = _mobileNumber INTO _inviteCodeFromOther;
	
	UPDATE ec_user SET inviteCodeFromOther = null WHERE inviteCodeFromOther = _inviteCodeFromOther;
	commit;
	
	
delete from ec_bankcard where bindUserId = _userid;
delete from ec_deal_detail where userid = _userid;
delete from ec_game_log where  EXISTS (select id from ec_user_game_relation where relationid=id  and userid = _mobileNumber);
DELETE  FROM ec_invite_benefit WHERE userid=_userid;
delete from ec_login_log where userid = _userid;
delete from ec_login_status where mobile = _mobileNumber;
delete from ec_lprecord where userid = _userid;
delete from ec_order where mobileNumberBuy = _mobileNumber;
delete from ec_project_user where userid = _userid;
delete from ec_recharge_record where userid = _userid;
delete from ec_security_center where userid = _userid;
delete from ec_user_convert_credits where user_id = _userid;
delete from ec_user_game_relation where userid = _mobileNumber;
delete from ec_user_obtain_credits where user_id = _userid;
delete from ec_user_question where userid = _userid;
delete from ec_user_role where user_id = _userid;
delete from ec_user_sign where mobilePhone = _mobileNumber;
delete from ec_user_wallet where userid = _userid;
delete from ec_withdrawal_record where userid = _userid;
delete from ec_user where mobileNumber = _mobileNumber;
delete from ec_user_detail where userid = _userid;
delete from ec_user_wallet where userid = _userid;
END