package cn.gsq.travel.service;

import cn.gsq.travel.domain.Route;

import java.util.List;

public interface FavortieService {
    public Boolean isFavorite(int uid, int rid);

    public void addLike(int rid,int uid);

    public List<Route> findFeS(int uid);
}
