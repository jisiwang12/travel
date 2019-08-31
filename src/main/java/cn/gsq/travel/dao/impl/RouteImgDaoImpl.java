package cn.gsq.travel.dao.impl;

import cn.gsq.travel.dao.RouteImgDao;
import cn.gsq.travel.domain.RouteImg;
import cn.gsq.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteImgDaoImpl implements RouteImgDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<RouteImg> findImgByRid(int rid) {
        String sql = "select * from tab_route_img where rid= ? ";
        return template.query(sql,new BeanPropertyRowMapper<>(RouteImg.class), rid);
    }
}
