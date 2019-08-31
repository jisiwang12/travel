package ServletTest;

import cn.gsq.travel.dao.impl.FavoriteDaoImpl;
import cn.gsq.travel.dao.impl.RouteDaoImpl;
import cn.gsq.travel.domain.PageBean;
import cn.gsq.travel.domain.Route;
import cn.gsq.travel.domain.User;
import cn.gsq.travel.service.impl.FavoriteServiceImpl;
import cn.gsq.travel.service.impl.RouteServiceImpl;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class ServletTest {

    public void add(User user) {
        user.setUid(1);
        System.out.println(user);
    }
@Test
    public void addTest() {
    User user = new User();
    user.setName("name");
    add(user);
    System.out.println(user);
}
@Test
    public void extendTest() {
        MeTest meTest = new MeTest();
        meTest.setName("hello");
        meTest.setSex("男");
        String name = meTest.getName();
        System.out.println(name);
    }

}
