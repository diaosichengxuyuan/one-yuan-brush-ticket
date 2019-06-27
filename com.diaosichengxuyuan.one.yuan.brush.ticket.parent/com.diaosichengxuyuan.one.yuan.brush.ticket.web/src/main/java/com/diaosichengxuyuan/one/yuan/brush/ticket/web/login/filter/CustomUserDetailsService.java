package com.diaosichengxuyuan.one.yuan.brush.ticket.web.login.filter;

import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.account.AccountMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.account.entity.AccountDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 自定义user查询服务
 *
 * @author liuhaipeng
 * @date 2019/6/17
 */
@Service("userDetailService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDO accountDO = accountMapper.selectOne(AccountDO.builder().accountId(username).build());
        if(accountDO == null) {
            return null;
        }

        return User.withUsername(accountDO.getAccountId()).password(accountDO.getPassword()).roles("USER").build();
    }

}
