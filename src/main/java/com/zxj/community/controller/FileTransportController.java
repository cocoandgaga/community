package com.zxj.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.zxj.community.dto.FileDTO;
import com.zxj.community.provider.FileTransProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;


@Controller
@RequestMapping("/file")
public class FileTransportController {

    @Autowired
    private FileTransProvider fileTransProvider;

    @RequestMapping("/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) throws IOException {

       MultipartHttpServletRequest multipartHttpServletRequest= (MultipartHttpServletRequest) request;
        MultipartFile file=multipartHttpServletRequest.getFile("editormd-image-file");
        String result= fileTransProvider.upload(file.getInputStream(),file.getOriginalFilename());
        FileDTO fileDTO=new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl(result);
        return fileDTO;
    }


}
