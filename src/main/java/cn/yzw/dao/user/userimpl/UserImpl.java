package cn.yzw.dao.user.userimpl;

import cn.yzw.been.User;
import cn.yzw.dao.user.UserDao;
import cn.yzw.until.BaseDao;
import cn.yzw.until.PageUtil;
import cn.yzw.until.ResultSetUtil;

import java.io.Serializable;
import java.util.List;

public class UserImpl  extends BaseDao implements UserDao {

    @Override
    public int findRownum() {
        String sql="SELECT COUNT(UserName) as count FROM yzw_user";
        rs=executeQuery(sql);
        int count=0;
        try {
            if (rs.next()){
                count= rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }



    @Override
    public List<User> findAllByPage(PageUtil util, Object... params) {
        String sql="SELECT userName,password,id FROM yzw_user limit ?,?";
        Object[] p={(util.getPageIndex()-1)*util.getPageSize(),util.getPageSize()};
        rs= executeQuery(sql,p);
        List<User> list= ResultSetUtil.eachList(rs,User.class);
        return list;
    }




    @Override
    public User login(String userName, String password) {
        String sql="SELECT userName,password FROM yzw_user where userName=? and password=?";
        Object [] params={userName,password};
        rs=executeQuery(sql,params);
        User users= ResultSetUtil.eachOne(rs,User.class);
        return users;
    }



    @Override
    public String validateName(String userName) {
        String sql = "SELECT password FROM yzw_user WHERE userName=?";
        rs = executeQuery(sql, userName);
        String password = null;
        try { //获取密码
            if (rs.next()) {
                password = rs.getString("password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }



    @Override
    public int add(User user) {
        String sql="INSERT INTO yzw_user(UserName,PassWord) VALUES(?,?)";
        Object[] params={user.getUserName(),user.getPassWord()};
        return executeUpdate(sql,params);
    }

    @Override
    public int deleteByCondition(Serializable id) {
            String sql="delete from yzw_user where id=?";
            int num= executeUpdate(sql,id);
            return num;
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






}
