package cn.yzw.servlet;
import cn.yzw.been.User;
import cn.yzw.sercice.ServiceFactory;
import cn.yzw.sercice.user.UserService;
import cn.yzw.until.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * main页面需要ajax使用的servlet
 */
@WebServlet("/home")
public class HomeServlet extends  BaseServlet {

    private   UserService userService;

    @Override
    public Class getServletClass() {
        return HomeServlet.class;
    }

    //当用户访问我们这个servlet的时候 先执行init
    @Override
    public void init() throws ServletException {
        userService=(UserService) ServiceFactory.getServiceImpl("userService");
    }


    /**
     *  分页的方法
     */
    public PageUtil findAllByPage(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("===========");
        //获取当前页面 pageIndex
        int pageIndex=Integer.parseInt(req.getParameter("pageIndex"));

        //创建PageUtil对象
        PageUtil pageUtil=new PageUtil();
        //把获取的数据封装到PageUtil
        pageUtil.setPageIndex(pageIndex);
        pageUtil.setTotalCount(userService.findRownum());
        //调用service代码 获取数据
        List<User> list = userService.findAllByPage(pageUtil);
        pageUtil.setList(list);
        System.out.println("pageUtil===>"+pageUtil);
        //返回PageUtil
        return  pageUtil;
    }


    public String deleteUser(HttpServletRequest req, HttpServletResponse resp){
        String id= req.getParameter("id");
        System.out.println("yyyyyy"+id+"wwwwwww");
        System.out.println(111111);
        int num= userService.deleteByCondition(id);
        System.out.println(id+"=============");
        if (num>0){
            return "main";
        }
        return "login";
    }
}