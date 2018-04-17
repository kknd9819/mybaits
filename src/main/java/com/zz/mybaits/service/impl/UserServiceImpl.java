package com.zz.mybaits.service.impl;

import com.zz.mybaits.config.security.JwtUser;
import com.zz.mybaits.entity.Role;
import com.zz.mybaits.entity.User;
import com.zz.mybaits.mapper.RoleMapper;
import com.zz.mybaits.mapper.UserMapper;
import com.zz.mybaits.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void register(User user) {
        User byUsername = userMapper.findByUsername(user.getUsername());
        if(byUsername != null){
            throw new RuntimeException("用户已存在");
        }
        User u = new User();
        u.setUsername(user.getUsername());
        u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userMapper.insertSelective(u);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        } else {
            List<Role> roles = roleMapper.findUserRoleByUserId(user.getId());
            List<String> authentications = roles.stream().map(Role::getName).collect(Collectors.toList());
            return new JwtUser(user.getUsername(), user.getPassword(), authentications.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        }
    }
}
