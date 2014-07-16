package com.app.wms.web.util;

import com.app.wms.engine.db.dao.ApprovalRangeDao;
import com.app.wms.engine.db.dao.UserDao;
import com.app.wms.engine.db.dao.UserRoleDao;
import com.app.wms.engine.db.dto.ApprovalRange;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.UserRole;
import com.app.wms.engine.db.exceptions.ApprovalRangeDaoException;
import com.app.wms.engine.db.exceptions.UserDaoException;
import com.app.wms.engine.db.exceptions.UserRoleDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.math.BigDecimal;
import java.util.List;

public class FyaUtility {

    public static String checkPOApprovalAuth(String userId, BigDecimal amount)
            throws ApprovalRangeDaoException, UserRoleDaoException, UserDaoException {

        /* DATA | get initial value */
        String isApproved = "Y";

        /* DAO | Define needed dao here */
        UserDao userDao = DaoFactory.createUserDao();

        /* TRANSACTION | Something complex here */
        // compare login user role
        User u = userDao.findByPrimaryKey(userId);
        String x = checkPOApprovalWait(amount, "code");
        if (x.equals(u.getRoleCode())) {
            isApproved = "N";
        } else if (u.getRoleCode().contains("MAN") && x.contains("MAN")) {
            isApproved = "N";
        }

        return isApproved;

    }

    public static String checkPOApprovalWait(BigDecimal amount, String type)
            throws ApprovalRangeDaoException, UserRoleDaoException {

        /* DATA | get initial value */
        String wait = "";

        /* DAO | Define needed dao here */
        UserRoleDao userRoleDao = DaoFactory.createUserRoleDao();
        ApprovalRangeDao approvalRangeDao = DaoFactory.createApprovalRangeDao();

        /* TRANSACTION | Something complex here */
        // get approval range and compare it with current amount
        List<ApprovalRange> ars = approvalRangeDao.findAll();
        for (ApprovalRange x : ars) {
            if (x.getFromAmount().compareTo(amount) < 1) {
                wait = x.getRoleCode();
                break;
            }
        }

        if (type.equals("name")) {
            UserRole ur = userRoleDao.findWhereRoleCodeEquals(wait).get(0);
            wait = ur.getRoleName();
        }

        return wait;

    }

}
