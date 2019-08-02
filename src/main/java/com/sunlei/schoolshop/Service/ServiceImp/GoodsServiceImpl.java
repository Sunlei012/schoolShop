package com.sunlei.schoolshop.Service.ServiceImp;

import com.sunlei.schoolshop.Dao.GoodsDao;
import com.sunlei.schoolshop.Entity.Goods;
import com.sunlei.schoolshop.Service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author 孙磊
 * 商品Service实现类
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    public GoodsDao goodsdao;


    @Override
    public Optional<Goods> getgoods(int goodid){
        return goodsdao.findById(goodid);
    }

    @Override
    public List<Goods> getAllGoods(){
        return goodsdao.findAll();
    }

    @Override
    public Goods addGoods(Goods goods){
             goodsdao.save(goods);
            return goodsdao.findById(goods.getGoodsId()).get();
    }

    @Override
    public Goods updateGoods(Goods goods) {
        goodsdao.save(goods);
        return goodsdao.findById(goods.getGoodsId()).get();
    }

    @Override
    public void delectGoods(Goods goods){
        goodsdao.deleteById(goods.getGoodsId());
    }

//    public List<Goods> getGoodsByUser(){
//
//    }

    @Override
    public List<Goods> getCanBuyGoods(){
        List<Goods> canBuyGoods = new ArrayList<>();
        List<Goods> Goods = goodsdao.findAll();
        for (int i = 0; i < Goods.size(); i++){
            if (Goods.get(i).getBuy()){
                canBuyGoods.add(Goods.get(i));
            }
        }
        return canBuyGoods;
    }
}
