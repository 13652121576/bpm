/**
 * 
 */
package com.manage.web.config.security;

import com.alibaba.fastjson.JSONObject;
import com.manage.common.util.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


/**
 * @author ydm
 *
 */
@Component("imoocAuthenticationSuccessHandler")
public class SysAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
		logger.debug("登录成功{}",authentication);
		User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.debug("登录成功User{}",authentication);
		//给用户jwt加密生成token
		String token = JWTUtils.sign(userDetails, 1000L* 60L*10L);
		HashMap<String, String> userData = new HashMap<>();
		userData.put("JwtToken",token);
		ServerResponse userResponse = ServerResponse.createBySuccess(userData);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(JSONObject.toJSONString(userResponse));
	}

}
