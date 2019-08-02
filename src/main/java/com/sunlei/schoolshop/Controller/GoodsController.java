package com.sunlei.schoolshop.Controller;

import com.alibaba.fastjson.JSON;
import com.sunlei.schoolshop.Annotation.PassToken;
import com.sunlei.schoolshop.Entity.Goods;
import com.sunlei.schoolshop.Service.ServiceImp.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author 孙磊
 * 关于商品的Controller类
 */
@RestController
@RequestMapping(value = "/Goods")
public class GoodsController  {
    @Autowired
    private GoodsServiceImpl goodsServiceImpl;
    @PassToken
    @GetMapping(value = "/goods")
    public String getGoods(@RequestParam(name = "id") Integer id){
        String i = goodsServiceImpl.getgoods(id).toString();
        Goods Goods = goodsServiceImpl.getgoods(id).get();
        String s = JSON.toJSONString(Goods);
        return s;
    }
    @PassToken
    @GetMapping(value = "/showAll")
    public String getAllGoods(){
        List<Goods> ListGoods = goodsServiceImpl.getAllGoods();
        String s = JSON.toJSONString(ListGoods);
        return s;
    }
    @PassToken
    @GetMapping(value = "/show")
    public String getCanBuyGoods(){
        List<Goods> goods = goodsServiceImpl.getCanBuyGoods();
        return JSON.toJSONString(goods);
    }

}
