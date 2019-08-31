package cn.gsq.travel.dao;

import cn.gsq.travel.domain.Favorite;
import cn.gsq.travel.domain.Route;

import java.util.Date;
import java.util.List;

public interface FavoriteDao {
    public Favorite findFavorite(int rid, int uid);


    void addLike(int rid, String date, int uid);

    public List<Route> findFeS(int uid);
}
