package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.AdvertisePosition;

import java.util.List;

/**
 * Created by zhangwenyun on 14/11/24.
 */
public interface AdvertisePositionBussiness {
    public List<AdvertisePosition> getAdvertisePositionList() throws BusinessException;
}
