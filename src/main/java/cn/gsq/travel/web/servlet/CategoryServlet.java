package cn.gsq.travel.web.servlet;


import cn.gsq.travel.domain.Category;
import cn.gsq.travel.service.CategoryService;
import cn.gsq.travel.service.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService categoryService=new CategoryServiceImpl();

    /**
     * 查询所有
     * @param req
     * @param resp
     */
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Category> all = categoryService.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(resp.getWriter(),all);
    }
}
