package dao;

import entity.User;

/**
 * Created by p on 2017/7/30.
 */
public interface UserMapper {
    User findByPhone(String phone);
    void insert(User user);
    void updateToken(String phone,String token);
    void updatePsw(String phone,String psw);
}
