package com.ocean.wy.magi.auth.server.api;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ocean.wy.magi.auth.server.utils.JsonUtils;

/**
 * 类ApiBaseController.java的实现描述：
 * 
 * @author ocean.wy
 */
public class ApiBaseController {

    protected Log logger = LogFactory.getLog(this.getClass());

    /**
     * 输出json数据
     * 
     * @param response
     * @param result
     */
    public void outputToJSON(HttpServletResponse response, Object result) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            if (null != result) {
                response.getWriter().write(JsonUtils.toJsonString(result));
            }
            response.flushBuffer();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
