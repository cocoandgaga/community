package com.zxj.community.service;

import com.zxj.community.dto.PaginationDTO;
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
     * @param page
     * @param size
     */
     public PaginationDTO list(Integer page, Integer size){

         PaginationDTO totalPagesDTO=new PaginationDTO();
         Integer totalCnt=questionMapper.count();
         Integer totalPages=(totalCnt+size-1)/size;
         if(page<1) page=1;
         if(page>totalPages) page=totalPages;

         totalPagesDTO.setPagination(totalPages,page);

         Integer offset=size*(page-1);
         //列出数据库里的每一条提问数据
         List<Question> questions=questionMapper.list(offset,size);
         List<QuestionDTO> questionDTOs=new ArrayList<>();
         for(Question question:questions){
             User user=userMapper.findById(question.getCreator());
             QuestionDTO questionDTO=new QuestionDTO();
             //快速地把question对象copy到DTO
             BeanUtils.copyProperties(question,questionDTO);
             questionDTO.setUser(user);
             questionDTOs.add(questionDTO);
         }

         totalPagesDTO.setQuestionDTOs(questionDTOs);
         return totalPagesDTO;
     }

    public PaginationDTO list(String userId, Integer page, Integer size) {
        PaginationDTO totalPagesDTO=new PaginationDTO();
        Integer totalCnt=questionMapper.countByUserId(userId);
        Integer totalPages=(totalCnt+size-1)/size;
        if(page<1) page=1;
        if(page>totalPages) page=totalPages;

        totalPagesDTO.setPagination(totalPages,page);

        Integer offset=size*(page-1);
        //列出数据库里的每一条提问数据
        List<Question> questions=questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOs=new ArrayList<>();
        for(Question question:questions){
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            //快速地把question对象copy到DTO
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }

        totalPagesDTO.setQuestionDTOs(questionDTOs);
        return totalPagesDTO;

    }

    public QuestionDTO getById(Integer id) {
         Question question=questionMapper.getById(id);
         QuestionDTO questionDTO=new QuestionDTO();
         BeanUtils.copyProperties(question,questionDTO);
         User user=userMapper.findById(question.getCreator());
         questionDTO.setUser(user);
         return questionDTO;
    }

    public void createOrUpdate(Question question) {
         if(question.getId()==null){
             question.setGmtCreate(System.currentTimeMillis());
             question.setGmtModified(question.getGmtCreate());
             questionMapper.create(question);
         }else{
             question.setGmtModified(System.currentTimeMillis());
             questionMapper.update(question);
         }
    }
}
