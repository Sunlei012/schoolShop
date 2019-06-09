package com.sunlei.schoolshop.Service;

import com.sunlei.schoolshop.Entity.Goods;
import com.sunlei.schoolshop.util.State;

import java.util.List;
import java.util.Optional;

public interface GoodsService {
     Optional<Goods> getgoods(int GoodId);
     List<Goods> getAllGoods();
     Goods addGoods(Goods Goods);
     Goods updateGoods(Goods Goods);
     void delectGoods(Goods Goods);
}
