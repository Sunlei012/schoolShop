# 校园卖
##模块概要
1. 附近商品展示
2. 商品详情
3. 商品预定
4. 个人中心
5. 上架商品
6. 即时通讯

##tabbar
1. 商品（index）
2. 加号（上架）
3. 个人中心

##附近商品展示
1. 首页index
2. 宫格展示页面
3. 
```
    GET
       show(String localPosition)
       return json(goodsId,title,price,imgPath,categoryId)
```

##商品详情
1. 上方为img，title，price，下方为详情
2. load()内
```
    GET
        Goods(int goodsId)
        return (goodsId,title,price,List imgPath,categoryId,details)
```
3. 最下方为商品预定与联系卖家
4. 商品预定
    1. 
    ```
    POST
        int reserve(int goodsId,int userId)
        return 0/1
    ```
    2. 0为预定失败，1为预定成功跳转预定成功页面
5. 联系卖家

##个人中心
1. 个人头像
2. 个人昵称
2. 定位地点
3. 修改密码
4. 已预定商品
5. 上架商品
6. 下架商品

##上架商品
1. 商品属性
2. 
```
    POST
        int Goods(GoodsProperty)
        return 0/1
```
3. 0为预定失败，1为上架成功跳转上架成功页面

##即时通讯
