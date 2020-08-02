package com.manage.web.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity//开启权限验证
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启注解全局方法细粒度控制
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;//注入我们自己的userDetailsService
    @Autowired
    private SysAuthenctiationFailureHandler sysAuthenctiationFailureHandler;//注入我们自己登陆失败处理器
    @Autowired
    private SysAuthenticationSuccessHandler sysAuthenticationSuccessHandler;//注入我们自己登陆成功处理器
    @Autowired
    private SysAuthenticationProvider sysAuthenticationProvider;  //注入我们自己的认证处理
    @Autowired
    private SysAuthenticationEntryPoint sysAuthenticationEntryPoint;//注入我们自己统一未登录处理json
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;//注入我们自己jwt过滤器

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SysPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter,UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().anyRequest().authenticated()
             .and()
                .formLogin().loginPage("/login").permitAll()//登录允许所有用户
                .failureHandler(sysAuthenctiationFailureHandler)
                .successHandler(sysAuthenticationSuccessHandler)
             .and()
                .exceptionHandling()
                .authenticationEntryPoint(sysAuthenticationEntryPoint)
             .and()
                .logout().logoutUrl("/logout")//自定义退出的地址
                .logoutSuccessHandler(new SysLogoutSuccessHandler())//退出成功后的操作
                .deleteCookies("JSESSIONID")//删除当前的JSESSIONID
             .and()
                .cors().and().csrf().disable();//禁止跨站伪造请求关闭

            }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/druid/**", "/css/**", "/plugins/**", "/js/**", "/html/**", "/images/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //开启自定义provider
        auth.authenticationProvider(sysAuthenticationProvider);
        //指定我们自己的userDetailsService
        //开启自定义认证方式
        auth.userDetailsService(userDetailsService).passwordEncoder(new SysPasswordEncoder());
    }
}

