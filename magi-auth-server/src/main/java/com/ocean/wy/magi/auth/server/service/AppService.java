package com.ocean.wy.magi.auth.server.service;

import java.util.List;

import com.ocean.wy.magi.auth.server.entity.App;

/**
 * <p>User: Ocean.wy
 * <p>Date: 16-1-28
 * <p>Version: 1.0
 */
public interface AppService {


    public App createApp(App app);
    public App updateApp(App app);
    public void deleteApp(Long appId);

    public App findOne(Long appId);
    public List<App> findAll();

    /**
     * 根据appKey查找AppId
     * @param appKey
     * @return
     */
    public Long findAppIdByAppKey(String appKey);
}
