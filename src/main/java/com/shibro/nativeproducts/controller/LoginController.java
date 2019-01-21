package com.shibro.nativeproducts.controller;

import com.shibro.nativeproducts.data.vo.BaseResponseVo;
import com.shibro.nativeproducts.data.vo.requestvo.login.*;
import com.shibro.nativeproducts.service.LoginServce;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class LoginController {

    @Resource
    private LoginServce loginServce;

    /**
     * 登录
     * @param requestVo
     * @return
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public BaseResponseVo login(@RequestBody LoginRequestVo requestVo){
        return loginServce.login(requestVo);
    }


    /**
     * 退出
     * @param requestVo
     * @return
     */
    @RequestMapping(value = "/user/logout", method = RequestMethod.POST)
    public BaseResponseVo logout(@RequestBody LogoutRequestVo requestVo){
        return loginServce.logout(requestVo);
    }

    /**
     *  注册
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public BaseResponseVo registerUser(@RequestBody RegisterRequestVo requestVo){
        return loginServce.registerUser(requestVo);
    }

    /**
     * 删除
     * @param requestVo
     * @return
     */
    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public BaseResponseVo deleteUser(@RequestBody DeleteUserRequestVo requestVo){
        return loginServce.deleteUser(requestVo);
    }

    /**
     * 修改用户名密码
     */
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public BaseResponseVo updateUser(@RequestBody UpdateUserRequestVo requestVo){
        return loginServce.updateUser(requestVo);
    }
}
