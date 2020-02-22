package com.venada.efinance.business.impl;

import com.venada.efinance.business.SystemConfigBusiness;
import com.venada.efinance.business.ValidateCodeBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.ValidateCode;
import com.venada.efinance.service.ValidateCodeService;
import com.venada.efinance.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Service
public class ValidateCodeBusinessImpl implements ValidateCodeBusiness {
	@Autowired
	private ValidateCodeService codeService;
	@Autowired
	private SmsService smsService;
	@Autowired
	private SystemConfigBusiness systemConfigBusiness;
	@Override
	public List<ValidateCode> queryLastestCodeByMobileNumber(String mobileNumber)
			throws BusinessException {
		return codeService.findObjects("queryLastestCodeByMobileNumber", mobileNumber);
	}
	@Override
	public int queryCrossTimeById(String id) throws BusinessException {
		return (Integer) codeService.getObject("queryCrossTimeById", id);
	}
	@Override
	public void addValiDateCode(ValidateCode validateCode)
			throws BusinessException {
		codeService.saveObject("addValiDateCode", validateCode);
	}
	
	@Override
	@Transactional(rollbackFor = {Exception.class,ServiceException.class})
	public void sendSms(String mobileNumber, String sms,String initPassword)
			throws BusinessException {
		smsService.sendSms(mobileNumber, "", "MB-2014011415",initPassword);
        deleteUserCode(mobileNumber);
		ValidateCode vc = new ValidateCode();
		vc.setId(UUID.randomUUID().toString());
		vc.setCode(initPassword);
		vc.setMobileNumber(mobileNumber);
		addValiDateCode(vc);
	}

    @Override
	public List<ValidateCode> queryLastestCodeByMobileNumberAndPassword(
			Map<String, String> map) throws BusinessException {
		return codeService.findObjects("queryLastestCodeByMobileNumberAndPassword", map);
	}
	@Override
	public List<ValidateCode> queryLastestCodeByMobileNumberAndEncodedPassword(
			Map<String, String> map) throws BusinessException {
		return codeService.findObjects("queryLastestCodeByMobileNumberAndEncodedPassword", map);
	}
	@Override
	public List<ValidateCode> queryValidateCodeByConditions(
			Map<String, Object> map,PaginationMore page) throws BusinessException {
		page.setTotalRows(queryCountOfValidateCodeByConditions(map));
		return codeService.selectList("queryValidateCodeByConditions", map, page);
	}
	@Override
	public int queryCountOfValidateCodeByConditions(Map<String, Object> map)
			throws BusinessException {
		return (Integer) codeService.getObject("queryCountOfValidateCodeByConditions", map);
	}

    @Override
    public ValidateCode queryLastestCodeByMobileNumberAndCode(Map<String, Object> map) throws BusinessException {
        return (ValidateCode) codeService.getObject("queryLastestCodeByMobileNumberAndCode", map);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,BusinessException.class,ServiceException.class})
    public void sendSmsByTemple(String mobileNumber, String code, String temple) throws BusinessException {
        smsService.sendSms(mobileNumber, "", temple,code);
        ValidateCode vc = new ValidateCode();
        vc.setId(UUID.randomUUID().toString());
        vc.setCode(code);
        vc.setMobileNumber(mobileNumber);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("mobileNumber", mobileNumber);
        map.put("code",code);
        deleteUserCode(mobileNumber);
        addValiDateCode(vc);
    }

    @Override
    public void deleteUserCode(String mobileNumber) throws BusinessException {
        codeService.deleteObject("deleteUserCode",mobileNumber);
    }

}
