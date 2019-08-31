package cn.gsq.travel.service.impl;

import cn.gsq.travel.dao.FavoriteDao;
import cn.gsq.travel.dao.impl.FavoriteDaoImpl;
import cn.gsq.travel.domain.Favorite;
import cn.gsq.travel.domain.Route;
import cn.gsq.travel.service.FavortieService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FavoriteServiceImpl implements FavortieService {
    FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public Boolean isFavorite(int rid, int uid) {
        Favorite favorite = favoriteDao.findFavorite(rid, uid);
        if (favorite != null) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void addLike(int rid, int uid) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
        favoriteDao.addLike(rid, format,uid);


    }

    @Override
    public List<Route> findFeS(int uid) {
        List<Route> list = favoriteDao.findFeS(uid);
        return list;
    }


}
