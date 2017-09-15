package dao;

import entity.FinaPro;
import entity.InsuPro;

import java.util.List;

/**
 * Created by p on 2017/9/14.
 */
public interface FinaProMapper {
    List<FinaPro> getPro(int level);

}
