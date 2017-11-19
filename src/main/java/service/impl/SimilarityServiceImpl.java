package service.impl;

import dao.InsuTestMapper;
import entity.InsuTest;
import component.WordSimilarity;
import component.KeyWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.SimilarityService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by p on 2017/11/15.
 */
@Service
public class SimilarityServiceImpl implements SimilarityService {


    @Autowired
    HttpServletRequest request;

    @Autowired
    InsuTestMapper testMapper;

    @Autowired
    WordSimilarity similarity;

    @Autowired
    KeyWord keyWord;

    @Override
    public void initInsuTest(ArrayList<String> list) throws NoSuchFieldException, IllegalAccessException, IOException {
        String phone = String.valueOf(request.getAttribute("phone"));
        InsuTest entity = testMapper.get(phone);
        boolean flag = false;
        if (entity == null) {
            entity = new InsuTest();
            entity.setPhone(phone);
            flag = true;
        }
        calculate(entity,list);
        if (flag) {
            testMapper.insert(entity);
        }
        else {
            testMapper.update(entity);
        }
    }

    private float getSimilarity(String str1, String str2){
        return (float) similarity.getSimilarity(str1,str2);
    }

    private void calculate(InsuTest test, ArrayList<String> list) throws IOException, IllegalAccessException, NoSuchFieldException {

        similarity.readCiLin();
        for (String str : list) {
            for (int i = 0; i < keyWord.keys_en.length; i++) {
                Class<?> cls = InsuTest.class;
                Field f = null;
                try {
                    f = cls.getDeclaredField(keyWord.keys_en[i]);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                f.setAccessible(true);//为 true 则表示反射的对象在使用时取消 Java 语言访问检查
                try {
                    System.out.println("before" + f.get(test));
                    System.out.println(str + "  " + (keyWord.keys_cn[i]));
                    float a = getSimilarity(str, keyWord.keys_cn[i]);
                    System.out.println("similarity:" + a);
                    f.setFloat(test, (float)f.get(test) + a);
                    System.out.println("after" + f.get(test));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        float sum = 0;
        for (int i = 0; i < keyWord.keys_en.length; i++){
            Class<?> cls = InsuTest.class;
            Field f = null;
            f = cls.getDeclaredField(keyWord.keys_en[i]);
            f.setAccessible(true);//为 true 则表示反射的对象在使用时取消 Java 语言访问检查
            sum += f.getFloat(test);
        }
        for (int i = 0; i < keyWord.keys_en.length; i++){
            Class<?> cls = InsuTest.class;
            Field f = null;
            f = cls.getDeclaredField(keyWord.keys_en[i]);
            f.setAccessible(true);//为 true 则表示反射的对象在使用时取消 Java 语言访问检查
            f.setFloat(test, (float)f.get(test)/sum * 10);
        }
    }
}
