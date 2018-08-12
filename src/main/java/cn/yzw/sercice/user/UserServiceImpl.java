package cn.yzw.sercice.user;

import cn.yzw.been.User;
import cn.yzw.dao.user.UserDao;
import cn.yzw.dao.user.userimpl.UserImpl;
import cn.yzw.until.PageUtil;

import java.io.Serializable;
import java.util.List;

public class UserServiceImpl implements UserService{




    //
    private UserDao userDao =new UserImpl();
    @Override
    public List<User> findAllByPage(PageUtil util, Object... params) {
        return userDao.findAllByPage(util,params);
    }
    //登陆
    @Override
    public User login(String userName,  String password) {
        return userDao.login(userName,password);
    }
    //注册
    @Override
    public int add(User user) {
        return userDao.add(user);
    }
    //判断是否用户时候存在
    @Override
    public String validateName(String userName) {
        return userDao.validateName(userName);
    }

    @Override
    public int deleteByCondition(Serializable id) {

        return userDao.deleteByCondition(id);
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public User findByCondition(Serializable id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
    @Override
    public int findRownum() {
        return userDao.findRownum();
    }

}
