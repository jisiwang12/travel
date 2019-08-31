package cn.gsq.travel.service.impl;

import cn.gsq.travel.dao.UserDao;
import cn.gsq.travel.dao.impl.UserDaoImpl;
import cn.gsq.travel.domain.User;
import cn.gsq.travel.service.UserService;
import cn.gsq.travel.util.MailUtils;
import cn.gsq.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public Boolean register(User user) {
        UserDao userDao = new UserDaoImpl();
        User u = userDao.findUserByUsername(user.getUsername());
        if (u == null) {
            user.setCode(UuidUtil.getUuid());
            user.setStatus("N");
            userDao.save(user);
            String content = "<a href='http://localhost:8080/travel/user/active?code=" + user.getCode() + "'>点击验证邮箱</a>";
            new Thread(new Runnable() {
                @Override
                public void run() {
                    MailUtils.sendMail(user.getEmail(), content, "激活邮件");
                }
            }).start();

            return true;
        } else {

            return false;
        }
    }

    /**
     * 判断用户是否激活
     * @param code
     * @return
     */
    public boolean active(String code) {
        UserDaoImpl userDao = new UserDaoImpl();
        User userByCode = userDao.findUserByCode(code);
        if (userByCode != null) {
            userDao.updataState(userByCode);
            return true;
        } else {
            return false;
        }


    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.findUserByNamePassword(username, password);
        if (user != null) {
            return user;
        }
        return null;
    }

}
