package dao;

import entity.UserInfo;

/**
 * Created by p on 2017/9/13.
 */
public interface UserInfoMapper {
    void insert(UserInfo info);
    void update(UserInfo info);
    UserInfo get(String phone);
}
