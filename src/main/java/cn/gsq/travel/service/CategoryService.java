package cn.gsq.travel.service;

import cn.gsq.travel.domain.Category;

import java.util.List;

public interface CategoryService {
    /**
     *寻找所有的分类信息
     * @return
     */
    public List<Category> findAll();
}
