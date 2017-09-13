package dao;

import entity.InsuTest;

/**
 * Created by p on 2017/9/13.
 */
public interface InsuTestMapper {
    void insert(InsuTest entity);
    void update(InsuTest entity);
    InsuTest get(String phone);
}
