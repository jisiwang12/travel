package cn.gsq.travel.dao.impl;

import cn.gsq.travel.dao.CategoryDao;
import cn.gsq.travel.domain.Category;
import cn.gsq.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> findAll() {
        String sql="select * from tab_category";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }
}
