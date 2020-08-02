package com.manage.web.config.security;

import com.alibaba.fastjson.JSONObject;
import com.manage.common.util.ServerResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version : V1.0
 * @ClassName: com.manage.web.config.securitySysAuthenticationEntryPoint
 * @Description: SysAuthenticationEntryPoint
 * @Auther: ydm
 * @Date: 2020/8/1
 */
@Component
public class SysAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSONObject.toJSONString(ServerResponse.createByErrorMessage("匿名用户访问无权限")));

    }
}
