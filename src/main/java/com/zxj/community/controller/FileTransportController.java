package com.zxj.community.controller;

import com.alibaba.fastjson.JSON;
import com.zxj.community.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileTransportController {
    @RequestMapping("/file/upload")
    @ResponseBody
    public String upload() {
        FileDTO fileDTO=new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/img/wechat.png");
        String json=JSON.toJSONString(fileDTO);
        return json;
    }


}
