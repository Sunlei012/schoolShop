package com.sunlei.schoolshop.Service.ServiceImp;

import com.sunlei.schoolshop.Dao.GoodsDao;
import com.sunlei.schoolshop.Entity.Goods;
import com.sunlei.schoolshop.Service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GoodsServiceImp implements GoodsService {

    @Autowired
    public GoodsDao GoodsDao;


    @Override
    public Optional<Goods> getgoods(int GoodId){
        return GoodsDao.findById(GoodId);
    }

    @Override
    public List<Goods> getAllGoods(){
        return GoodsDao.findAll();
    }

    @Override
    public Goods addGoods(Goods Goods){
             GoodsDao.save(Goods);
            return GoodsDao.findById(Goods.getGoodsId()).get();
    }

    @Override
    public Goods updateGoods(Goods Goods) {
        GoodsDao.save(Goods);
        return GoodsDao.findById(Goods.getGoodsId()).get();
    }

    @Override
    public void delectGoods(Goods Goods){
        GoodsDao.deleteById(Goods.getGoodsId());
    }

//    public List<Goods> getGoodsByUser(){
//
//    }

    @Override
    public List<Goods> getCanBuyGoods(){
        List<Goods> canBuyGoods = new ArrayList<>();
        List<Goods> Goods = GoodsDao.findAll();
        for (int i = 0; i < Goods.size(); i++){
            if (Goods.get(i).getBuy()){
                canBuyGoods.add(Goods.get(i));
            }
        }
        return canBuyGoods;
    }
}
