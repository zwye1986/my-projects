package com.venada.efinance.business.impl;

import com.venada.efinance.business.AdvertisePositionBussiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.AdvertisePosition;
import com.venada.efinance.service.AdvertisePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangwenyun on 14/11/24.
 */
@Service
public class AdvertisePositionBussinessImpl implements AdvertisePositionBussiness {
    @Autowired
    private AdvertisePositionService advertisePositionService;
    @Override
    public List<AdvertisePosition> getAdvertisePositionList() throws BusinessException {
        return advertisePositionService.findObjects("getAdvertisePositionList",null);
    }
}
