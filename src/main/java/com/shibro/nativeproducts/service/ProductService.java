package com.shibro.nativeproducts.service;

import com.shibro.nativeproducts.data.dto.HomePageInfo;
import com.shibro.nativeproducts.data.dto.HomePageInfoItem;
import com.shibro.nativeproducts.data.entity.ProductsInfo;
import com.shibro.nativeproducts.data.enums.ErrorCodeEnum;
import com.shibro.nativeproducts.data.enums.ProductTypeEnum;
import com.shibro.nativeproducts.data.vo.BaseRequestVo;
import com.shibro.nativeproducts.data.vo.BaseResponseVo;
import com.shibro.nativeproducts.data.vo.requestVo.DeleteProductInfoRequestVo;
import com.shibro.nativeproducts.data.vo.requestVo.HomePageInfoRequestVo;
import com.shibro.nativeproducts.data.vo.requestVo.InsertProductInfoRequestVo;
import com.shibro.nativeproducts.data.vo.requestVo.UpdateProductInfoRequestVo;
import com.shibro.nativeproducts.persistence.ProductsInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    @Resource
    private ProductsInfoMapper productsInfoMapper;

    public BaseResponseVo getHomePageInfo(HomePageInfoRequestVo param) {
        //设置分页
        if(Objects.nonNull(param.getCount())&&Objects.nonNull(param.getPage())){
            Integer start = (param.getPage()-1)*param.getCount();
            Integer end = param.getPage()*param.getCount();
            param.setPage(start);
            param.setCount(end);
        }
        convertParam(param);
        LOG.info("getHomePageInfo 入参:");
        HomePageInfo homePageInfo = new HomePageInfo();
        List<ProductsInfo> productsInfos = productsInfoMapper.selectByParam(param);
        Integer count = productsInfoMapper.selectCount(param);
        homePageInfo.setHomePageInfos(convertHomePageInfo(productsInfos));
        homePageInfo.setCount(count);
        BaseResponseVo responseVo = BaseResponseVo.successResponseVo(homePageInfo);
        return responseVo;
    }

    private void convertParam(HomePageInfoRequestVo param) {
        if(Objects.nonNull(param.getSort())){
            if(param.getSort().equals("recommendLevel")){
                param.setSort("recommend_level");
            }
        }
    }

    private List<HomePageInfoItem> convertHomePageInfo(List<ProductsInfo> productsInfos) {
        List<HomePageInfoItem> homePageInfoItems = new ArrayList<>();
        if(Objects.isNull(productsInfos)||productsInfos.size()==0){
            return homePageInfoItems;
        }
        HomePageInfoItem homePageInfoItem;
        for(ProductsInfo item:productsInfos){
            homePageInfoItem = new HomePageInfoItem();
            homePageInfoItem.setId(item.getId());
            homePageInfoItem.setName(item.getName());
            homePageInfoItem.setDescription(item.getDescription());
            homePageInfoItem.setType(item.getType());
            homePageInfoItem.setPrice(item.getPrice().toString());
            homePageInfoItem.setSaleAddress(item.getSaleAddress());
            homePageInfoItem.setRecommendLevel(item.getRecommendLevel());
            homePageInfoItem.setDifficulty(item.getDifficulty());
            homePageInfoItem.setMainPictureUrl(item.getMainPictureUrl());
            homePageInfoItem.setOtherPictureUrls(convertOtherPictureUrls(item.getOtherPictureUrl()));
            homePageInfoItems.add(homePageInfoItem);
        }
        return homePageInfoItems;
    }

    /**
     * 库中存的  是字符串，返回给前端以List数组形式返回
     * @param otherPictureUrl
     * @return
     */
    private List<String> convertOtherPictureUrls(String otherPictureUrl) {
       List<String> otherPictureUrls  = Arrays.asList(otherPictureUrl.split(","));
       return otherPictureUrls;
    }

    public BaseResponseVo insertProductInfo(InsertProductInfoRequestVo requestVo) {
        Boolean flag =true;
        try{
            ProductsInfo productsInfo = new ProductsInfo();
            productsInfo.setName(requestVo.getName());
            productsInfo.setDescription(requestVo.getDescription());
            productsInfo.setType(requestVo.getType());
            productsInfo.setPrice(new BigDecimal(requestVo.getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
            productsInfo.setSaleAddress(requestVo.getSaleAddress());
            productsInfo.setRecommendLevel(requestVo.getRecommendLevel());
            productsInfo.setDifficulty(requestVo.getDifficulty());
            productsInfo.setMainPictureUrl(requestVo.getMainPictureUrl());
            productsInfo.setOtherPictureUrl(list2String(requestVo.getOtherPictureUrls()));
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

    private String list2String(List<String> otherPictureUrls) {
        return otherPictureUrls.stream().collect(Collectors.joining(","));
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
            ProductsInfo productsInfo = new ProductsInfo();
            productsInfo.setId(requestVo.getId());
            productsInfo.setName(requestVo.getName());
            productsInfo.setDescription(requestVo.getDescription());
            productsInfo.setType(requestVo.getType());
            productsInfo.setPrice(new BigDecimal(requestVo.getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
            productsInfo.setSaleAddress(requestVo.getSaleAddress());
            productsInfo.setRecommendLevel(requestVo.getRecommendLevel());
            productsInfo.setDifficulty(requestVo.getDifficulty());
            productsInfo.setMainPictureUrl(requestVo.getMainPictureUrl());
            productsInfo.setOtherPictureUrl(list2String(requestVo.getOtherPictureUrls()));
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

    public static void main(String[] args) {
    }

    public BaseResponseVo queryProductType(BaseRequestVo requestVo) {
        return BaseResponseVo.successResponseVo(ProductTypeEnum.getProductType());
    }
}
