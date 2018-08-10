package cn.yzw.sercice.user;

import cn.yzw.been.User;
import cn.yzw.sercice.IBaseService;

public interface UserService extends IBaseService<User> {
    String validateName(String userName);
    //特有的登陆方法
    User login(String userName, String password);
}
