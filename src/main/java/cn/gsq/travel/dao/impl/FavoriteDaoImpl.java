package cn.gsq.travel.dao.impl;

import cn.gsq.travel.dao.FavoriteDao;
import cn.gsq.travel.domain.Favorite;
import cn.gsq.travel.domain.Route;
import cn.gsq.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Favorite findFavorite(int rid, int uid) {
        try {
            String sql="select * from tab_favorite where rid= ? and uid= ? ";
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(Favorite.class), rid, uid);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void addLike(int rid, String date, int uid) {
        String sql="insert into tab_favorite(rid,date,uid) value (?,?,?)";
        template.update(sql, rid,new Date(), uid);
    }

    @Override
    public List<Route> findFeS(int uid) {
        String sql="select * from tab_route,tab_favorite where tab_favorite.uid=? and tab_route.rid=tab_favorite.rid";
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class),uid);
    }


}
