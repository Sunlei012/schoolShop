package com.sunlei.schoolshop.Service;

import com.sunlei.schoolshop.Entity.Goods;

import java.util.List;
import java.util.Optional;

/**
 * @author 孙磊
 * 商品Service接口类
 */
public interface GoodsService {
     Optional<Goods> getgoods(int GoodId);
     List<Goods> getAllGoods();
     Goods addGoods(Goods Goods);
     Goods updateGoods(Goods Goods);
     void delectGoods(Goods Goods);
      List<Goods> getCanBuyGoods();
}
