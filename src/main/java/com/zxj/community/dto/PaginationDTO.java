package com.zxj.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class  PaginationDTO<T> {
    private List<T> data;
    private boolean showBegin;
    private boolean showEnd;
    private  boolean showPreviousPage;
    private boolean showNextPage;
    private Integer page;
    private Integer totalPages;
    private Integer totalCnt;
    private List<Integer> pageList=new ArrayList<>();

    public void setPagination(Integer totalPages,Integer page){
        this.page=page;
        this.totalPages=totalPages;

        for(int i=1;i<=3;i++){
            if(page-i>=0) pageList.add(0,page-i+1);;
            if(page+i<=totalPages) pageList.add(page+i);
        }


        showBegin= !pageList.contains(1);
        showEnd= !pageList.contains(totalPages);

        showPreviousPage= page != 1;
        showNextPage= !page.equals(totalPages);

    }

}
