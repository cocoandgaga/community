package com.zxj.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOList;
    private boolean showBegin;
    private boolean showEnd;
    private  boolean showPreviousPage;
    private boolean showNextPage;
    private Integer page;
    private Integer totalPages;
    private Integer totalCnt;

    private List<Integer> pageList=new ArrayList<>();

    public void setPagination(Integer totalCnt,Integer page,Integer size){

        this.totalPages=(totalCnt+size-1)/size;

        //负数和超过的页数输入就转化为page=1 小数点的页就转化为整数
        this.page=(page<1||page>totalPages)?1:page;

        for(int i=1;i<=3;i++){
            if(page-i>=0) pageList.add(0,page-i+1);;
            if(page+i<=totalPages) pageList.add(page+i);
        }


        showBegin= !pageList.contains(1);
        showEnd= !pageList.contains(totalCnt);

        showPreviousPage= page != 1;
        showNextPage= !page.equals(totalCnt);


    }

}
