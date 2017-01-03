package com.ocean.wy.magi.auth.server.web.controller;

import com.ocean.wy.magi.auth.server.entity.Resource;
import com.ocean.wy.magi.auth.server.service.AuthorizationService;
import com.ocean.wy.magi.auth.server.service.ResourceService;
import com.ocean.wy.magi.auth.server.web.bind.annotation.CurrentUser;
import com.ocean.wy.magi.auth.server.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

/**
 * <p>User: Ocean.wy
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
public class IndexController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping("/")
    public String index(@CurrentUser com.ocean.wy.magi.auth.server.entity.User loginUser, Model model) {
        Set<String> permissions = authorizationService.findPermissions(Constants.SERVER_APP_KEY, loginUser.getUsername());
        List<Resource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


}
