package dao;

import entity.InsuPro;

import java.util.List;

/**
 * Created by p on 2017/9/14.
 */
public interface InsuProMapper {
    List<InsuPro> getPro(String[] key);
    InsuPro getOneRand();
    InsuPro getOneById(int id);
}
