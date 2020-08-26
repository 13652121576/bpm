/**
 * 
 */
package com.manage.web.config.security;

import com.alibaba.fastjson.JSONObject;
import com.manage.common.sysenum.ResultCode;
import com.manage.common.util.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author ydm
 *
 */
@Component("sysAuthenctiationFailureHandler")
public class SysAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
		logger.debug("登录失败{}",exception);
		//返回json数据
		ServerResponse<Object> result = null;
		if (exception instanceof AccountExpiredException) {
			//账号过期
			result = ServerResponse.createByErrorMessage(ResultCode.USER_ACCOUNT_EXPIRED.getMessage());
		} else if (exception instanceof DisabledException) {
			//账号不可用
			result = ServerResponse.createByErrorMessage(ResultCode.USER_ACCOUNT_DISABLE.getMessage());
		} else if (exception instanceof BadCredentialsException) {
			//密码错误
			result = ServerResponse.createByErrorMessage(ResultCode.USER_CREDENTIALS_ERROR.getMessage());
		} else if (exception instanceof CredentialsExpiredException) {
			//密码过期
			result = ServerResponse.createByErrorMessage(ResultCode.USER_CREDENTIALS_EXPIRED.getMessage());
		} else if (exception instanceof LockedException) {
			//账号锁定
			result = ServerResponse.createByErrorMessage(ResultCode.USER_ACCOUNT_LOCKED.getMessage());
		} else if (exception instanceof UsernameNotFoundException) {
			//用户不存在
			result = ServerResponse.createByErrorMessage(ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
		}else if (exception instanceof InternalAuthenticationServiceException) {
			//参数缺失
			result = ServerResponse.createByErrorMessage(ResultCode.PARAM_NOT_COMPLETE.getMessage());
		}else if (exception instanceof AuthenticationServiceException) {
			//不支持get
			result = ServerResponse.createByErrorMessage(ResultCode.PARAM_NOT_COMPLETE.getMessage());
		}else{
			//其他错误
			result = ServerResponse.createByErrorMessage(ResultCode.COMMON_FAIL.getMessage());
		}
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(JSONObject.toJSONString(result));

	}

}
