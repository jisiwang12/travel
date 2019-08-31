package cn.gsq.travel.dao.impl;

import cn.gsq.travel.dao.UserDao;
import cn.gsq.travel.domain.User;
import cn.gsq.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import static cn.gsq.travel.util.JDBCUtils.getDataSource;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

    @Override
    public User findUserByUsername(String username) {
        try {
            String sql = "select * from tab_user where username= ?";
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
            return user;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username, password, name, birthday, sex, telephone, email,status,code) value (?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode()
        );

    }


    public User findUserByCode(String code) {
        try {
            String sql = "select * from tab_user where code= ?";
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), code);
            return user;
        } catch (Exception e) {
            return null;
        }

    }

    public void updataState(User userByCode) {
        int id = userByCode.getUid();
        String sql="update tab_user set status='Y' where uid= ?";
        jdbcTemplate.update(sql, id);

    }

    /*
        登录验证
    */
    public User findUserByNamePassword(String username, String password) {

        try {
            String sql = "select * from tab_user where username= ? and password=?";
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
            return user;
        } catch (Exception e) {
            return null;
        }

    }

}
