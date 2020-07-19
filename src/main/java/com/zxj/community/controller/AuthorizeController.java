package com.zxj.community.controller;

import com.zxj.community.dto.AccessTokenDTO;
import com.zxj.community.dto.GiteeUser;
import com.zxj.community.mapper.UserMapper;
import com.zxj.community.model.User;
import com.zxj.community.provider.GiteeProvider;
import com.zxj.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@Controller
public class AuthorizeController {

    @Autowired
    private UserService userService;
    @Autowired
    private GiteeProvider giteeProvider;
    @Value("${gitee.client.id}")
    private String     clientId;
    @Value("${gitee.client.secret}")
    private String     clientSecret;
    @Value("${gitee.redirect.uri}")
    private String     redirectUri;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);

        String accessToken = giteeProvider.getAccessToken(accessTokenDTO);//ctr+alt+v
        System.out.println("token:" + accessToken);
        GiteeUser giteeUser = giteeProvider.getUser(accessToken);
        System.out.println("userName=" + giteeUser.getName());
        if (giteeUser != null && giteeUser.getId() != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setBio(giteeUser.getBio());
            user.setName(giteeUser.getName());
            user.setAvatarUrl(giteeUser.getAvatarUrl());
            user.setAccountId(String.valueOf(giteeUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token", token));
            //登陆成功
            return "redirect:/";
        } else {
            log.error("第三方接入登陆失败");
            //登陆失败，重新登陆
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {

        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
