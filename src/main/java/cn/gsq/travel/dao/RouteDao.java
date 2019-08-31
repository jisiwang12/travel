package cn.gsq.travel.dao;

import cn.gsq.travel.domain.Route;

import java.util.List;

/**
 * 分页信息查询
 */
public interface RouteDao {
    /**
     * 页面信息
     * @return
     */
    public List<Route> findRoute(int cid, int start, int pageSize, String rname);

    /**
     * 总记录数
     * @return
     */
    public int findTotalPage(int cid, String rname);

    public Route findOne(int rid);
}

