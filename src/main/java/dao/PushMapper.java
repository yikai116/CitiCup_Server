package dao;

import entity.Push;

import java.util.List;

/**
 * Created by p on 2017/11/19.
 */
public interface PushMapper {
    void insert(Push push);
    void update(Push push);
    Push get(String phone);
    List<Push> getAll();
}
