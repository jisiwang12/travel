package cn.gsq.travel.dao;

import cn.gsq.travel.domain.Category;

import java.util.List;

public interface CategoryDao {
    /**
     * 找到所有的分类页面
     * @return
     */
    public List<Category> findAll();

}
