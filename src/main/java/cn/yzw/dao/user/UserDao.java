package cn.yzw.dao.user;

import cn.yzw.been.User;
import cn.yzw.dao.IBaseDao;

public interface UserDao extends IBaseDao<User> {
    String validateName(String userName);
    //特有的登录
    User login(String userName, String password);
}
