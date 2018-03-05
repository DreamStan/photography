package com.photograph.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;


/**
 * Created by Eminem on 2018/1/12.
 * 安全控制
 */
@EnableWebSecurity //开启Security功能
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    /**
     * 加密
     * @return 对前台输入的密码加密
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 权限拦截
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests() //定义那些url需要被保护
                .antMatchers("/static/**","/","/home").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/submission/**").hasRole("USER")
                .antMatchers("/webUploader/**").permitAll()
                .antMatchers("/uploadImage/**").permitAll()
                .antMatchers("/showImg/**").permitAll()
                .antMatchers("/touxiang/**").permitAll()
                .antMatchers("/picture/**").permitAll()
                .antMatchers("/image/**").permitAll()
//                .antMatchers("/details/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin() //页面需要登陆时指定登录页面
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    /**
     * 交给userDetailsService校验
     * @param auth 自定义user details
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}