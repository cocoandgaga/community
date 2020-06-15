package com.zxj.community.service;

import com.zxj.community.dto.QuestionDTO;
import com.zxj.community.mapper.QuestionMapper;
import com.zxj.community.mapper.UserMapper;
import com.zxj.community.model.Question;
import com.zxj.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
     @Autowired
    private QuestionMapper questionMapper;
     @Autowired
    private UserMapper userMapper;

    /**
     * 得到所有提问列表（包含User消息）
     * @return 包含用户消息的问题列表
     */
     public List<QuestionDTO> list(){
         //列出数据库里的每一条提问数据
         List<Question> questions=questionMapper.list();
         List<QuestionDTO> questionDTOList=new ArrayList<>();
         for(Question question:questions){
             User user=userMapper.findById(question.getCreator());
             QuestionDTO questionDTO=new QuestionDTO();
             //快速地把question对象copy到DTO
             BeanUtils.copyProperties(question,questionDTO);
             questionDTO.setUser(user);
             questionDTOList.add(questionDTO);
         }
         return questionDTOList;
     }
}
