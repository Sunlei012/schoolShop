package com.sunlei.schoolshop.Controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sunlei.schoolshop.Dao.GoodsDao;
import com.sunlei.schoolshop.Entity.Goods;
import com.sunlei.schoolshop.Service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/Goods")
public class GoodsController  {
    @Autowired
    GoodsService GoodsService;


    @GetMapping(value = "/goods")
    public String GetGoods(@RequestParam(name = "id") Integer id){
        String i = GoodsService.getgoods(id).toString();
        Goods Goods = GoodsService.getgoods(id).get();
        String s = JSON.toJSONString(Goods);
        return s;
    }

    @GetMapping(value = "/show")
    public String GetAllGoods(){
        List<Goods> ListGoods = GoodsService.getAllGoods();
        String s = JSON.toJSONString(ListGoods);
        return s;
    }

}
