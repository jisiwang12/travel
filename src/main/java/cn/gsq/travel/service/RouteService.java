package cn.gsq.travel.service;

import cn.gsq.travel.domain.PageBean;
import cn.gsq.travel.domain.Route;

/**
 * 分页
 */
public interface RouteService {
    /**
     * 分页方法
     * @return
     * @param cid
     * @param pageSize
     * @param currentPage
     * @param rname
     */
    public PageBean<Route> pageQuery(int cid, int pageSize, int currentPage, String rname);

    /**
     * 详情页
     *
     * @param i
     * @param rid
     * @return
     */
    public Route findOne(int i, int rid);
}
