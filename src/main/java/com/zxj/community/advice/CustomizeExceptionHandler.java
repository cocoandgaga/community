package com.zxj.community.advice;

import com.alibaba.fastjson.JSON;
import com.zxj.community.dto.ResultDTO;
import com.zxj.community.exception.CustomizeErrorCode;
import com.zxj.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(Throwable e, Model model, HttpServletResponse response) {
        String contentType = response.getContentType();
        if ("application/json".equals(contentType)) {
            ResultDTO resultDTO = null;
            if (e instanceof CustomizeException) {
                //赋值code、message的封装
                resultDTO = resultDTO.errorOf((CustomizeException) e);
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return null;
        }
        if (e instanceof CustomizeException) {
            model.addAttribute("message", e.getMessage());
        } else {
            model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
        }
        return new ModelAndView("error");//定义的页面

    }
}
