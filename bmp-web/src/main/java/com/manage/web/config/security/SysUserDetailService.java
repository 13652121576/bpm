package com.manage.web.config.security;



import com.manage.common.dto.SysUserDto;
import com.manage.common.entity.sys.SysPower;
import com.manage.common.entity.sys.SysUser;
import com.manage.service.sysService.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SysUserDetailService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysPowerService sysPowerService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //通过username（登录时输入的用户名）然后到数据库中找到对应的用户信息，并构建成我们自己的UserInfo来返回。
        logger.info("用户名"+username);
        if(StringUtils.isBlank(username)){
            throw new InternalAuthenticationServiceException("参数缺失");
        }
        SysUserDto sysUserDto = new SysUserDto();
        sysUserDto.setUserName(username);
        List<SysUser> sysUsers = sysUserService.getList(sysUserDto);
        SysUser sysUser = null;
        if(sysUsers.size()!=0){
            //数据库用户
            sysUser = sysUsers.get(0);
            //查询用户权限
            HashSet<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
            List<SysPower> sysPowers = sysPowerService.getPowerByUserId(sysUser.getId());
            for (SysPower sysPower:sysPowers){
                String powerName = sysPower.getActionUrl();
                if(StringUtils.isNoneEmpty(powerName)){
                    authorities.add(new SimpleGrantedAuthority(powerName));
                    logger.debug("权限{}",powerName);
                }
            }
            logger.info("用户密码"+sysUser.getPassword());
            //返回用户信息
            User user=new User(sysUser.getUserName(), sysUser.getPassword(),authorities);
            return user;
        }else{
            throw new UsernameNotFoundException("账号不存在");
        }
    }
}




