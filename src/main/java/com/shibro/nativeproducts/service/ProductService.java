package com.shibro.nativeproducts.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.shibro.nativeproducts.data.dto.HomePageInfo;
import com.shibro.nativeproducts.data.dto.HomePageInfoItem;
import com.shibro.nativeproducts.data.entity.ProductsInfo;
import com.shibro.nativeproducts.data.enums.ErrorCodeEnum;
import com.shibro.nativeproducts.data.vo.BaseResponseVo;
import com.shibro.nativeproducts.data.vo.RequestVo.DeleteProductInfoRequestVo;
import com.shibro.nativeproducts.data.vo.RequestVo.InsertProductInfoRequestVo;
import com.shibro.nativeproducts.data.vo.RequestVo.UpdateProductInfoRequestVo;
import com.shibro.nativeproducts.persistence.ProductsInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    @Resource
    private ProductsInfoMapper productsInfoMapper;

    public BaseResponseVo getHomePageInfo() {
        LOG.info("getHomePageInfo 入参:");
        HomePageInfo homePageInfo = new HomePageInfo();
        List<ProductsInfo> productsInfos = productsInfoMapper.selectAll();
        homePageInfo.setHomePageInfos(convertHomePageInfo(productsInfos));
        BaseResponseVo responseVo = BaseResponseVo.successResponseVo(homePageInfo);
        return responseVo;
    }

    private List<HomePageInfoItem> convertHomePageInfo(List<ProductsInfo> productsInfos) {
        List<HomePageInfoItem> homePageInfoItems = new ArrayList<>();
        HomePageInfoItem homePageInfoItem;
        for(ProductsInfo item:productsInfos){
            homePageInfoItem = new HomePageInfoItem();
            homePageInfoItem.setName(item.getName());
            homePageInfoItem.setDescription(item.getDescription());
            homePageInfoItem.setType(item.getType());
            homePageInfoItem.setPrice(item.getPrice());
            homePageInfoItem.setSaleAddress(item.getSaleAddress());
            homePageInfoItem.setRecommendLevel(item.getRecommendLevel());
            homePageInfoItem.setDifficulty(item.getDifficulty());
            homePageInfoItem.setMainPictureUrl(item.getMainPictureUrl());
            homePageInfoItem.setOtherPictureUrls(convertOtherPictureUrls(item.getOtherPictureUrl()));
            homePageInfoItems.add(homePageInfoItem);
        }
        return homePageInfoItems;
    }

    private List<String> convertOtherPictureUrls(String otherPictureUrl) {
       List<String> otherPictureUrls  = JSON.parseObject(otherPictureUrl,new TypeReference<List<String>>(){});
       return otherPictureUrls;
    }

    public BaseResponseVo insertProductInfo(InsertProductInfoRequestVo requestVo) {
        Boolean flag =true;
        try{
            ProductsInfo productsInfo = JSON.parseObject(JSON.toJSONString(requestVo),ProductsInfo.class);
            productsInfo.setOtherPictureUrl(stringSplit2List2Json(productsInfo.getOtherPictureUrl()));
            productsInfoMapper.insertSelective(productsInfo);
        }catch (Exception e){
            LOG.error("插入土特产信息失败,message:{}",e.getMessage());
            flag = false;
        }
        if(flag){
            return new BaseResponseVo(ErrorCodeEnum.SUCCESS);
        }else{
            return BaseResponseVo.failResponseVo();
        }
    }

    private String stringSplit2List2Json(String otherPictureUrl) {
        return JSON.toJSONString(Arrays.asList(otherPictureUrl.split(",")));
    }

    public BaseResponseVo deleteProductInfo(DeleteProductInfoRequestVo requestVo) {
        Boolean flag =true;
        try{
            productsInfoMapper.deleteByPrimaryKey(requestVo.getId());
        }catch (Exception e){
            LOG.error("删除土特产信息失败,message:{}",e.getMessage());
            flag = false;
        }
        if(flag){
            return new BaseResponseVo(ErrorCodeEnum.SUCCESS);
        }else{
            return BaseResponseVo.failResponseVo();
        }
    }

    public BaseResponseVo updateProductInfo(UpdateProductInfoRequestVo requestVo) {
        Boolean flag =true;
        try{
            ProductsInfo productsInfo = JSON.parseObject(JSON.toJSONString(requestVo),ProductsInfo.class);
            productsInfo.setOtherPictureUrl(stringSplit2List2Json(productsInfo.getOtherPictureUrl()));
            productsInfoMapper.updateByPrimaryKeySelective(productsInfo);
        }catch (Exception e){
            LOG.error("更新土特产信息失败,message:{}",e.getMessage());
            flag = false;
        }
        if(flag){
            return new BaseResponseVo(ErrorCodeEnum.SUCCESS);
        }else{
            return BaseResponseVo.failResponseVo();
        }
    }
}
