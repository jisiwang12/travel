package cn.gsq.travel.dao;

import cn.gsq.travel.domain.User;

public interface UserDao {
    /*
    * 寻找数据库中的用户
    * 信息
    * */
    public User findUserByUsername(String username);

    /*
     * 注册用户
     * */
    public void save(User user);
}
