package com.zxj.community.controller;

import com.zxj.community.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class FileTransportController {
    @RequestMapping(value = "/file/upload",method = RequestMethod.POST)
    @ResponseBody
    public FileDTO upload() {
        FileDTO fileDTO=new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/img/wechat.png");
        return fileDTO;
    }


}
