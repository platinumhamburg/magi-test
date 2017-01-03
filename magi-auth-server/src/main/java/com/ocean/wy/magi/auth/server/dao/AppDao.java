package com.ocean.wy.magi.auth.server.dao;

import java.util.List;

import com.ocean.wy.magi.auth.server.entity.App;

/**
 * <p>User: Ocean.wy
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface AppDao {

    public App createApp(App app);
    public App updateApp(App app);
    public void deleteApp(Long appId);

    public App findOne(Long appId);
    public List<App> findAll();

    Long findAppIdByAppKey(String appKey);
}
