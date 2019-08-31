package cn.gsq.travel.dao;

import cn.gsq.travel.domain.Seller;

public interface SellerDao {

    public Seller findBySid(int sid);
}
