package com.zxj.community.controller;

import com.zxj.community.dto.FileDTO;
import com.zxj.community.provider.FileTransProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


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
        if(result!=null){
            fileDTO.setSuccess(1);
            fileDTO.setMessage("上传失败");
        }

        else{
            fileDTO.setSuccess(0);
            fileDTO.setMessage("上传成功");
        }

        fileDTO.setUrl(result);
        return fileDTO;
    }


}
