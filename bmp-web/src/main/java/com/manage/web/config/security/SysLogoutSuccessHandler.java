package com.manage.web.config.security;

import com.alibaba.fastjson.JSONObject;
import com.manage.common.util.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: springboot
 * @Package: com.ydm.springboot.config.security
 * @ClassName: MyLogoutSuccessHandler
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2019/10/25 15:18
 * @Version: 1.0
 */
@Component
public class SysLogoutSuccessHandler implements LogoutSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(SysLogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, IOException {
        logger.info("登出成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(ServerResponse.createBySuccessMessage("登出成功")));
    }
}
