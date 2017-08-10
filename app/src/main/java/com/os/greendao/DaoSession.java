package com.os.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.os.police.data.ThirdAppInfoDao;
import com.os.police.data.UserThirdAppOrderDao;

import com.os.greendao.ThirdAppInfoDaoDao;
import com.os.greendao.UserThirdAppOrderDaoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig thirdAppInfoDaoDaoConfig;
    private final DaoConfig userThirdAppOrderDaoDaoConfig;

    private final ThirdAppInfoDaoDao thirdAppInfoDaoDao;
    private final UserThirdAppOrderDaoDao userThirdAppOrderDaoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        thirdAppInfoDaoDaoConfig = daoConfigMap.get(ThirdAppInfoDaoDao.class).clone();
        thirdAppInfoDaoDaoConfig.initIdentityScope(type);

        userThirdAppOrderDaoDaoConfig = daoConfigMap.get(UserThirdAppOrderDaoDao.class).clone();
        userThirdAppOrderDaoDaoConfig.initIdentityScope(type);

        thirdAppInfoDaoDao = new ThirdAppInfoDaoDao(thirdAppInfoDaoDaoConfig, this);
        userThirdAppOrderDaoDao = new UserThirdAppOrderDaoDao(userThirdAppOrderDaoDaoConfig, this);

        registerDao(ThirdAppInfoDao.class, thirdAppInfoDaoDao);
        registerDao(UserThirdAppOrderDao.class, userThirdAppOrderDaoDao);
    }
    
    public void clear() {
        thirdAppInfoDaoDaoConfig.getIdentityScope().clear();
        userThirdAppOrderDaoDaoConfig.getIdentityScope().clear();
    }

    public ThirdAppInfoDaoDao getThirdAppInfoDaoDao() {
        return thirdAppInfoDaoDao;
    }

    public UserThirdAppOrderDaoDao getUserThirdAppOrderDaoDao() {
        return userThirdAppOrderDaoDao;
    }

}