package com.diaosichengxuyuan.one.yuan.brush.ticket.web.login.filter;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.Constant;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.AESEncryptUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.account.AccountMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.account.entity.AccountDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.data.DataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 自定义登录filter，配置到UsernamePasswordAuthenticationFilter之前，不需要走到UsernamePasswordAuthenticationFilter处理
 *
 * @author liuhaipeng
 * @date 2019/6/17
 */
@Slf4j
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataService dataService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        //远程登录10086
        String originalPassword = AESEncryptUtil.decrypt(password,
            System.getProperty(Constant.Encryption.ENCRYPT_ALGORITHM_KEY));

        if(StringUtils.isEmpty(username)) {
            log.error(String.format("用户名为空，登录失败"));
            throw new BadCredentialsException("用户名为空，登录失败");
        } else if(StringUtils.isEmpty(password)) {
            log.error(String.format("密码为空，登录失败"));
            throw new BadCredentialsException("密码为空，登录失败");
        } else {
            log.info(String.format("远程登录12306成功，用户名：%s，密码：%s", username, passwordEncoder.encode(password)));
            insertOrUpdateAccount(username, password);
            return super.attemptAuthentication(request, response);
        }
    }

    private void insertOrUpdateAccount(String username, String password) {
        AccountDO accountDO = accountMapper.selectOne(AccountDO.builder().accountId(username).build());
        if(accountDO == null) {
            accountMapper.insertSelective(AccountDO.builder().createTime(new Date()).modifyTime(new Date()).accountId(
                username).password(passwordEncoder.encode(password)).build());
            //插入数据表
            dataService.increaseUserNumber();
        } else {
            accountMapper.updateByPrimaryKeySelective(AccountDO.builder().id(accountDO.getId()).modifyTime(new Date())
                .password(passwordEncoder.encode(password)).build());
        }
    }

}
