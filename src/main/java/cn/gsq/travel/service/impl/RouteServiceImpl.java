package cn.gsq.travel.service.impl;

import cn.gsq.travel.dao.RouteDao;
import cn.gsq.travel.dao.RouteImgDao;
import cn.gsq.travel.dao.impl.RouteDaoImpl;
import cn.gsq.travel.dao.impl.RouteImgDaoImpl;
import cn.gsq.travel.dao.impl.SellerDaoImpl;
import cn.gsq.travel.domain.PageBean;
import cn.gsq.travel.domain.Route;
import cn.gsq.travel.domain.RouteImg;
import cn.gsq.travel.domain.Seller;
import cn.gsq.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDaoImpl sellerDao = new SellerDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int pageSize, int currentPage, String rname) {
        PageBean<Route> routePageBean = new PageBean<>();
        routePageBean.setCurrentPage(currentPage);
        routePageBean.setPageSize(pageSize);
        int totalCount = routeDao.findTotalPage(cid,rname);
        routePageBean.setTotalCount(totalCount,rname);
        int totalPage=totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        routePageBean.setTotalPage(totalPage);
        int start=(currentPage-1)*pageSize;
        List<Route> list = routeDao.findRoute(cid, start, pageSize,rname);
        routePageBean.setList(list);
        return routePageBean;
    }

    /**
     * 详情页
     *
     * @param i
     * @param rid
     * @return
     */
    @Override
    public Route findOne(int rid, int sid) {
        Route route = routeDao.findOne(rid);
        List<RouteImg> img = routeImgDao.findImgByRid(rid);
        Seller seller = sellerDao.findBySid(sid);
        route.setRouteImgList(img);
        route.setSeller(seller);
        return route;
    }
}
