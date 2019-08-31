package cn.gsq.travel.service.impl;

import cn.gsq.travel.dao.CategoryDao;
import cn.gsq.travel.dao.impl.CategoryDaoImpl;
import cn.gsq.travel.domain.Category;
import cn.gsq.travel.service.CategoryService;
import cn.gsq.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();
        ArrayList<Category> categories =null;
//        Set<String> category = jedis.zrange("category", 0, -1);
        Set<Tuple> category = jedis.zrangeWithScores("category", 0, -1);
        if (category == null || category.size() == 0) {
            categories= (ArrayList<Category>) categoryDao.findAll();
            for (Category c : categories) {
                jedis.zadd("category", c.getCid(), c.getCname());
            }
            System.out.println("mysql");
            return categories;
        } else {
            categories= new ArrayList<Category>();
            for (Tuple cate:category
                 ) {
                Category category1 = new Category();
                category1.setCname(cate.getElement());
                category1.setCid((int) cate.getScore());
                categories.add(category1);
            }
            System.out.println("redis");
            return categories;

        }

    }
}
