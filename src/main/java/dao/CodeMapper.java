package dao;

import entity.Code;

/**
 * Created by p on 2017/7/31.
 */
public interface CodeMapper {
    Code findByPhone(String phone);
    void insert(Code code);
    void update(Code code);
}
