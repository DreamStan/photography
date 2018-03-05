package com.photograph.service.impl;

import com.photograph.dao.PermissionDao;
import com.photograph.dao.UserDao;
import com.photograph.pojo.User;
import com.photograph.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Eminem on 2018/1/18.
 * 登录校验
 */
@Component
public class CustomizeUserdetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 身份验证
     * @param username 登录名
     * @return 校验是否成功
     * @throws UsernameNotFoundException 未找到用户
     */
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        User user = userDao.checkLogin(username);
        if (user!=null){
                Collection authList = getAuthorities(user.getRid());
                userDetails = new org.springframework.security.core.userdetails.User(username,user.getPassword()
                        ,true,true,true,true,authList);
            return userDetails;
        }else{
            throw new UsernameNotFoundException("user:"+username+"doo not exist!");
        }
    }

    /**
     * 检查权限
     * @param rid 权限id
     * @return 返回权限
     */
    private Collection getAuthorities(int rid) {
        Permission permission = permissionDao.findByRoleId(rid);
        List authList = new ArrayList();
        authList.add(new SimpleGrantedAuthority(permission.getRolename()));
        return authList;
    }
}
