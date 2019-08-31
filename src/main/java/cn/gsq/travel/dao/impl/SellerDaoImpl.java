package cn.gsq.travel.dao.impl;

import cn.gsq.travel.dao.SellerDao;
import cn.gsq.travel.domain.Seller;
import cn.gsq.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SellerDaoImpl implements SellerDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Seller findBySid(int sid) {
        String sql = "select * from tab_seller where sid= ? ";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(Seller.class),sid);
    }
}
