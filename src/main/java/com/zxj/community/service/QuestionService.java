package com.zxj.community.service;

import com.zxj.community.dto.PaginationDTO;
import com.zxj.community.dto.QuestionDTO;
import com.zxj.community.exception.CustomizeErrorCode;
import com.zxj.community.exception.CustomizeException;
import com.zxj.community.mapper.QuestionExtMapper;
import com.zxj.community.mapper.QuestionMapper;
import com.zxj.community.mapper.UserMapper;
import com.zxj.community.model.Question;
import com.zxj.community.model.QuestionExample;
import com.zxj.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionService {
     @Autowired
    private QuestionMapper questionMapper;
     @Autowired
    private  UserMapper        userMapper;
     @Autowired
     private QuestionExtMapper questionExtMapper;

    /**
     * 得到所有提问列表（包含User消息）
     * @return 包含用户消息的问题列表
     * @param page
     * @param size
     */
     public PaginationDTO list(Integer page, Integer size){

         PaginationDTO totalPagesDTO=new PaginationDTO();
         Integer totalPages;
         QuestionExample questionExample=new QuestionExample();
         Integer totalCnt= Math.toIntExact(questionMapper.countByExample(questionExample));

         totalPages=(totalCnt+size-1)/size;
         if(page<1) page=1;
         if(page>totalPages) page=totalPages;

         totalPagesDTO.setPagination(totalPages,page);

         Integer offset=size*(page-1);
          questionExample.setOrderByClause("gmt_create desc");
         //列出数据库里的每一条提问数据
         List<Question> questions=questionMapper.selectByExampleWithRowbounds(
                 questionExample,new RowBounds(offset,size));

         List<QuestionDTO> questionDTOs=new ArrayList<>();
         for(Question question:questions){
             User user=userMapper.selectByPrimaryKey(question.getCreator());
             QuestionDTO questionDTO=new QuestionDTO();
             //快速地把question对象copy到DTO
             BeanUtils.copyProperties(question,questionDTO);
             questionDTO.setUser(user);
             questionDTOs.add(questionDTO);
         }

         totalPagesDTO.setData(questionDTOs);
         return totalPagesDTO;
     }

    public PaginationDTO list(Long userId, Integer page, Integer size) {

        PaginationDTO totalPagesDTO=new PaginationDTO();

        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);

        Integer totalCnt= Math.toIntExact(questionMapper.countByExample(questionExample));

        Integer totalPages=(totalCnt+size-1)/size;
        if(page<1) page=1;
        if(page>totalPages) page=totalPages;

        totalPagesDTO.setPagination(totalPages,page);
       totalPagesDTO.setTotalCnt(totalCnt);
        Integer offset=size*(page-1);
        //列出数据库里的每一条提问数据
        List<Question> questions=questionMapper.selectByExampleWithRowbounds(questionExample,new RowBounds(offset,size));

        List<QuestionDTO> questionDTOs=new ArrayList<>();

        for(Question question:questions){
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            //快速地把question对象copy到DTO
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }

        totalPagesDTO.setData(questionDTOs);
        return totalPagesDTO;

    }

    public QuestionDTO getById(Long id) {
         Question question=questionMapper.selectByPrimaryKey(id);
         if(question==null) {
             throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUNT);
         }
         QuestionDTO questionDTO=new QuestionDTO();
         BeanUtils.copyProperties(question,questionDTO);
         User user=userMapper.selectByPrimaryKey(question.getCreator());
         questionDTO.setUser(user);
         return questionDTO;
    }

    public void createOrUpdate(Question question) {
         if(question.getId()==null){
             question.setGmtCreate(System.currentTimeMillis());
             question.setGmtModified(question.getGmtCreate());
             //insertSelective 对应的 SQL 语句加入了 NULL 检验，只会插入数据不为 null 的字段 这样默认值起作用
             //而 insert 会插入所有字段，会插入 null 数据。
             questionMapper.insertSelective(question);
         }else{
             Question updateQuestion=new Question();
             updateQuestion.setGmtModified(System.currentTimeMillis());
             updateQuestion.setTag(question.getTitle());
             updateQuestion.setDescription(question.getDescription());
             updateQuestion.setTag(question.getTag());
             updateQuestion.setTitle(question.getTitle());
             QuestionExample questionExample=new QuestionExample();
             questionExample.createCriteria().andIdEqualTo(question.getId());
             //更新不更新没更改的字段
             // updateByPrimaryKeySelective 会对字段进行判断再更新（如果为 Null 就忽略更新）
             //updateByPrimaryKey 对你注入的字段全部更新（不判断是否为 Null）
             int IsUpdate=questionMapper.updateByExampleSelective(updateQuestion,questionExample);
             if(IsUpdate!=1) {
                 throw new CustomizeException(CustomizeErrorCode.UPDATE_FAILED);
             }

         }
    }

    public void incViewCnt(Long id) {
//         Question question=questionMapper.selectByPrimaryKey(id);
//         Question updateQuestion=new Question();
//         updateQuestion.setViewCnt(question.getViewCnt()+1);
//         QuestionExample questionExample=new QuestionExample();
//         questionExample.createCriteria().andIdEqualTo(id);
//         questionMapper.updateByExampleSelective(updateQuestion,questionExample);
            Question question=new Question();
            question.setId(id);
            question.setViewCnt(1);
            questionExtMapper.incViewCnt(question);

    }

    public List<QuestionDTO> selectRelated(QuestionDTO query) {
         if(StringUtils.isBlank(query.getTag()))
             return Collections.emptyList();

         String[] tags=StringUtils.split(query.getTag(),",");
        String regexTag=Arrays.stream(tags).collect(Collectors.joining("|"));

        Question question=new Question();
        question.setId(query.getId());
        question.setTag(regexTag);
        List<Question> questions=questionExtMapper.selectRelated(question);
        List<QuestionDTO> questionDTOS=questions.stream().map(q -> {
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(q,questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());

        return questionDTOS;
    }
}
