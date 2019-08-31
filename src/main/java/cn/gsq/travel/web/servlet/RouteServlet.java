package cn.gsq.travel.web.servlet;

import cn.gsq.travel.domain.PageBean;
import cn.gsq.travel.domain.Route;
import cn.gsq.travel.domain.User;
import cn.gsq.travel.service.FavortieService;
import cn.gsq.travel.service.RouteService;
import cn.gsq.travel.service.impl.FavoriteServiceImpl;
import cn.gsq.travel.service.impl.RouteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 分页
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private FavortieService favortieService = new FavoriteServiceImpl();
    private RouteService routeService = new RouteServiceImpl();
    public void pageQuery(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cidStr = req.getParameter("cid");
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");
        String rname = req.getParameter("rname");
        if (rname != null && rname.length() != 0) {
            rname = new String(rname.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        }
        if ("null".equals(rname)) {
            rname=null;
        }
        int currentPage=0;
        int cid=0;
        int pageSize=0;
        if (cidStr != null && cidStr.length() != 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        if (currentPageStr != null && currentPageStr.length() != 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage=1;
        }
        if (pageSizeStr != null && pageSizeStr.length() != 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize=5;
        }


        PageBean<Route> routePageBean = routeService.pageQuery(cid, pageSize, currentPage,rname);
        resp.setContentType("application/json;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), routePageBean);
    }

    /**
     * route详情页
     * @param req
     * @param resp
     * @throws IOException
     */
    public void findOne(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String ridStr = req.getParameter("rid");
        String sidStr = req.getParameter("sid");
        int sid=0;
        int rid=0;
        if (ridStr != null && ridStr.length() != 0) {
            rid = Integer.parseInt(ridStr);
        }
        if (sidStr != null && sidStr.length() != 0) {
             sid = Integer.parseInt(sidStr);
        }
        Route one = routeService.findOne(rid,sid);
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(resp.getWriter(),one);
    }

    /**
     * 判断是否收藏了
     * @param req
     * @param resp
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String ridStr = req.getParameter("rid");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int uid;
        if (user != null) {
            uid = user.getUid();
        } else {
            uid=0;
        }
        int rid=0;
        if (ridStr != null && ridStr.length() != 0) {
            rid = Integer.parseInt(ridStr);
        }
        Boolean flag = favortieService.isFavorite(rid, uid);
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(resp.getWriter(),flag);
    }

    /**
     * 添加收藏
     * @param req
     * @param resp
     * @throws IOException
     */
    public void like(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String ridStr = req.getParameter("rid");
//        ObjectMapper objectMapper = new ObjectMapper();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int uid;
        if (user != null) {
            uid = user.getUid();
        } else {
            return;
        }
        int rid=0;
        if (ridStr != null && ridStr.length() != 0) {
            rid = Integer.parseInt(ridStr);
        }

//        resp.setContentType("application/json;charset=utf-8");
//        objectMapper.writeValue(resp.getWriter(),flag);

        favortieService.addLike(rid,uid);

    }

    /**
     * 查找已收藏的记录
     * @param req
     * @param resp
     * @throws IOException
     */
    public void findFes(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int uid;
        if (user == null) {
            return;
        } else {
            uid = user.getUid();
        }

        List<Route> list = favortieService.findFeS(uid);
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(resp.getWriter(),list);

    }

    }
