package com.zxj.community.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zxj.community.dto.AccessTokenDTO;
import com.zxj.community.dto.GiteeUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 获取令牌并用令牌请求用户消息
 */
@Component
public class GiteeProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType=MediaType.get("application/json;charset=utf-8");
        OkHttpClient client=new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://gitee.com/oauth/token?grant_type=authorization_code")
                .post(body)
                .addHeader("Accept","application/json")
                .build();
         try (Response response = client.newCall(request).execute()) {
            String resp=response.body().string();
            JSONObject object=  JSONObject.parseObject(resp);
            System.out.println(object.toString());
            String token=object.getString("access_token");
            System.out.println("令牌"+token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GiteeUser getUser(String accessToken){

        OkHttpClient client=new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://gitee.com/api/v5/user?access_token="+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String resp=response.body().string();
            System.out.println("回来了"+resp);
            GiteeUser githubUser=JSON.parseObject(resp,GiteeUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
