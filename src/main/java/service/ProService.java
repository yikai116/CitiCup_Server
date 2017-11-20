package service;

import entity.FinaPro;
import entity.InsuPro;

import java.util.ArrayList;

/**
 * Created by p on 2017/11/17.
 */
public interface ProService {
    ArrayList<InsuPro> getInsuPro(boolean reget) throws NoSuchFieldException, IllegalAccessException;

    ArrayList<FinaPro> getFinaPro(boolean reget);

    InsuPro getInsuProById(String id);
}
