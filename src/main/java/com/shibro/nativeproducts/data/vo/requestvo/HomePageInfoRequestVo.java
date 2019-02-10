package com.shibro.nativeproducts.data.vo.requestvo;

import com.shibro.nativeproducts.data.vo.BaseRequestVo;
import lombok.Data;


@Data
public class HomePageInfoRequestVo extends BaseRequestVo {
    private Integer id;   //主键
    private String sort;   //排序字段
    private String sortType;  //排序类别    asc  desc
    private String keyWord;   //关键字
    private Integer page;  //第几页
    private Integer count;  //每页多少条
}
