package com.ocean.wy.magi.auth.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocean.wy.magi.auth.server.dao.AppDao;
import com.ocean.wy.magi.auth.server.entity.App;

import java.util.List;

/**
 * <p>User: Ocean.wy
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service

public class AppServiceImpl implements AppService {

    @Autowired
    private AppDao appDao;

    public App createApp(App app) {
        return appDao.createApp(app);
    }

    public App updateApp(App app) {
        return appDao.updateApp(app);
    }

    public void deleteApp(Long appId) {
        appDao.deleteApp(appId);
    }

	public App findOne(Long appId) {
		// TODO Auto-generated method stub
		return appDao.findOne(appId);
	}

	public List<App> findAll() {
		// TODO Auto-generated method stub
		return appDao.findAll();
	}

	public Long findAppIdByAppKey(String appKey) {
		// TODO Auto-generated method stub
		return appDao.findAppIdByAppKey(appKey);
	}

    
}
