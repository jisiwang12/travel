package cn.gsq.travel.dao.impl;

import cn.gsq.travel.dao.RouteDao;
import cn.gsq.travel.domain.Route;
import cn.gsq.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 分页查询
     * @param cid 分类导航栏
     * @param start 开始页码
     * @param pageSize 每页展示多少数据
     * @param rname 搜索的数据
     * @return
     */
    @Override
    public List<Route> findRoute(int cid, int start, int pageSize, String rname) {
//        String sql="select * from tab_route where cid=? limit ? , ? ";
        String sql="select * from tab_route where 1=1 ";
        StringBuilder stringBuilder = new StringBuilder(sql);
        ArrayList arrayList = new ArrayList();
        if (cid != 0) {
            stringBuilder.append(" and cid= ? ");
            arrayList.add(cid);
        }
        if (rname != null && rname.length() != 0) {
            stringBuilder.append(" and rname like ? ");
            arrayList.add(("%"+rname+"%"));
        }
        stringBuilder.append(" limit ? , ? ");
        arrayList.add(start);
        arrayList.add(pageSize);
        List<Route> list = template.query(stringBuilder.toString(), new BeanPropertyRowMapper<>(Route.class), arrayList.toArray());
        return list;
    }

    /**
     * 查找符合条件的总页数
     * @param cid
     * @param rname
     * @return
     */
    @Override
    public int findTotalPage(int cid, String rname) {
        String sql="select count(*) from tab_route where 1=1 ";
        StringBuilder stringBuilder = new StringBuilder(sql);
        ArrayList list = new ArrayList();
        if (cid != 0) {
            stringBuilder.append(" and cid= ? ");
            list.add(cid);
        }
        if (rname != null && rname.length()!=0 && !"null".equals(rname)) {
            stringBuilder.append(" and rname like ? ");
            list.add("%"+rname+"%");
        }

        return template.queryForObject(stringBuilder.toString(), Integer.class, list.toArray());

    }

    /**
     * 寻找一个route
     * @param rid
     * @return
     */
    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid= ? ";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Route.class), rid);
    }
}
