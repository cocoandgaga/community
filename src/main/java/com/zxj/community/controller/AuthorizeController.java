package com.zxj.community.controller;

import com.zxj.community.dto.AccessTokenDTO;
import com.zxj.community.dto.GiteeUser;
import com.zxj.community.provider.GiteeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GiteeProvider giteeProvider;
    @Value("${gitee.client.id}")
    private String        clientId;
    @Value("${gitee.client.secret}")
    private String clientSecret;
    @Value("${gitee.redirect.uri}")
    private String redirectUri;


    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);

        String accessToken=giteeProvider.getAccessToken(accessTokenDTO);//ctr+alt+v
        System.out.println("token:"+accessToken);
        GiteeUser user =giteeProvider.getUser(accessToken);
        System.out.println("userName="+user.getName());

        return "index";

    }
}
