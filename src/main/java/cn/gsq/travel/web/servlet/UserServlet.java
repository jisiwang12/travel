package cn.gsq.travel.web.servlet;


import cn.gsq.travel.domain.ResultInfo;
import cn.gsq.travel.domain.User;
import cn.gsq.travel.service.UserService;
import cn.gsq.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    /**
     * 用户登录
     * @param request
     * @param response
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.login(username, password);
        ResultInfo resultInfo = new ResultInfo();
        if (user != null) {
            String Y = "Y";
            String status = user.getStatus();
            if (Y.equalsIgnoreCase(status)) {
                resultInfo.setFlag(true);
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
            } else {
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("未激活");
            }
        } else {
            resultInfo.setErrorMsg("用户名或密码错误");
            resultInfo.setFlag(false);
        }
        ObjectMapper map = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        map.writeValue(response.getWriter(),resultInfo);

    }

    /**
     * 用户注册
     * @param request
     * @param response
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String check = request.getParameter("check");
        //返回数据的实体类
        ResultInfo resultInfo = new ResultInfo();
        HttpSession session = request.getSession();
        String checkcode = (String) session.getAttribute("CHECKCODE_SERVER");
        //防止验证码被多次使用
        session.removeAttribute("CHECKCODE_SERVER");
        //检验验证码,错误则直接返回
        if (checkcode==null|| !(check.equalsIgnoreCase(checkcode))) {
            resultInfo.setErrorMsg("验证码错误");
            resultInfo.setFlag(false);
            response.setContentType("application/json;charset=utf-8");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getWriter(),resultInfo);
            return;
        }
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
            UserService userServce = new UserServiceImpl();
            //调用service方法进行注册
            Boolean flag = userServce.register(user);

            if (flag) {
                resultInfo.setFlag(true);
            } else {
                resultInfo.setErrorMsg("注册失败");
                resultInfo.setFlag(false);
            }
            response.setContentType("application/json;charset=utf-8");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getWriter(),resultInfo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过Session查找当前登录的用户
     * @param req
     * @param resp
     * @throws IOException
     */
    public void findUser(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(resp.getWriter(),user);
    }

    public void exit(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    public void active(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        if (code != null) {
            UserServiceImpl userService = new UserServiceImpl();
            boolean flag = userService.active(code);
            String msg = null;
            if (flag) {
                msg = "<a href='../login.html'>注册成功，请登录</a>";
            } else {
                msg = "<a>注册失败，请联系管理员</a>";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }

}
