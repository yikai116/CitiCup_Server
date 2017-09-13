package dao;

import entity.InsuPrefer;

/**
 * Created by p on 2017/9/13.
 */
public interface InsuPreferMapper {
    void insert(InsuPrefer entity);
    void update(InsuPrefer entity);
    InsuPrefer get(String phone);
}
