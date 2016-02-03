package com.spfi.ims.helper;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.BackDateProfileDao;

public class ValidatorHelper {
    
    public static int backDateHelper(LoginUser loginUser, String menuUrl) {
        BackDateProfileDao bdpDao = DaoFactory.createBackDateProfileDao();
        return bdpDao.checkIsValid(menuUrl, loginUser.getUserId());
    }
    
}
