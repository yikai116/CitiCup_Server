package dao;

import entity.FinaPrefer;

/**
 * Created by p on 2017/9/13.
 */
public interface FinaPreferMapper {
    void insert(FinaPrefer entity);
    void update(FinaPrefer entity);
    FinaPrefer get(String phone);
}
