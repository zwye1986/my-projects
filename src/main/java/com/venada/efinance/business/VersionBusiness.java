package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.Version;







/***
 * 
 * @author xupei
 *
 */

public interface VersionBusiness {
   void addVersion(Version version) throws BusinessException;
   
   void updateVersion(Version version) throws BusinessException;
    
	Version getLastApp() throws BusinessException;
}
