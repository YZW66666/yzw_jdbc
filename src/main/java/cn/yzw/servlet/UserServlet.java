package cn.yzw.servlet;
import cn.yzw.been.User;
import cn.yzw.sercice.ServiceFactory;
import cn.yzw.sercice.user.UserService;
import cn.yzw.until.Md5Encrypt;
import cn.yzw.until.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/login")
public class UserServlet extends  BaseServlet{

    //不实例化service层对象  让工厂去实例化
    private UserService  userService;


    private  ResultUtil util=new ResultUtil();
    //当用户访问我们这个servlet的时候 先执行init
    @Override
    public void init() throws ServletException {
        userService=(UserService) ServiceFactory.getServiceImpl("userService");
    }
    @Override
    public Class getServletClass() {
        return UserServlet.class;
    }
    //注册
    public  String  register(HttpServletRequest req, HttpServletResponse resp){
        //获取用户输入的参数
        String userName=req.getParameter("username");
        String password=req.getParameter("password");
        User user=new User();
        user.setUserName(userName);

        try {
            user.setPassWord(Md5Encrypt.getEncryptedPwd(password));
            System.out.println(user.getPassWord());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int num= userService.add(user);
        System.out.println(num);
        if (num>0){
            return  "main";
        }else{
            return "register";
        }
    }
    //验证用户名是否存在
    public ResultUtil validateName(HttpServletRequest req, HttpServletResponse resp){
        String userName=req.getParameter("username");
        String passwordInDB= userService.validateName(userName);
        if (passwordInDB==null){
            //可以注册
            util.resultSuccess();
        }else {
            util.resultFail("改昵称已经被使用");
            System.out.println("===============");
        }
        System.out.println(util.getStatus());
        return util;
    }

    public String login(HttpServletRequest req, HttpServletResponse resp){
        //获取用户登录的用户名和密码
        String userName=req.getParameter("username");
        String password=req.getParameter("password");
        //验证用户名是否存在
        String passwordInDB= userService.validateName(userName);
        if (passwordInDB!=null){
            //用户名正确  并且能找到密码
            try {
                if (Md5Encrypt.validPassword(password,passwordInDB)){ //验证密码是否正确
                    User user=  userService.login(userName,passwordInDB);
                    //保存对象
                    req.getSession().setAttribute("loginUser",user);
                    return "main";
                }else {
                    System.out.println("=====================");
                    System.out.println("=======possWord======");
                    System.out.println("=====================");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{ //用户名错误
            System.out.println("UserName Not");
        }
        return "login";
    }

}
