package cn.gsq.travel.dao;

import cn.gsq.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {
    /**
     * 通过rid找到图片
     * @param rid
     * @return
     */
    public List<RouteImg> findImgByRid(int rid);
}
