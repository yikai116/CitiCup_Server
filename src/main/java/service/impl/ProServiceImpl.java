package service.impl;

import component.KeyWord;
import dao.FinaProMapper;
import dao.InsuProMapper;
import dao.InsuTestMapper;
import dao.RiskAbilityMapper;
import entity.FinaPro;
import entity.InsuPro;
import entity.InsuTest;
import entity.RiskAbility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ProService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by p on 2017/11/17.
 */
@Service
public class ProServiceImpl implements ProService {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private InsuProMapper insuProMapper;
    @Autowired
    private InsuTestMapper insuTestMapper;
    @Autowired
    private FinaProMapper finaProMapper;
    @Autowired
    private RiskAbilityMapper abilityMapper;
    @Autowired
    private KeyWord keyWord;

    @Override
    public ArrayList<InsuPro> getInsuPro(boolean reget) throws NoSuchFieldException, IllegalAccessException {
        ArrayList<InsuPro> pros = new ArrayList<InsuPro>();
        String[] keys;
        if (!reget)
            keys = getKey();
        else
            keys = new String[]{""};
        pros.addAll(insuProMapper.getPro(keys));
        if (pros.size() < 8) {
            pros.addAll(insuProMapper.getPro(new String[]{""}));
        }
        return pros;
    }

    @Override
    public ArrayList<FinaPro> getFinaPro(boolean reget) {
        ArrayList<FinaPro> pros = new ArrayList<>();
        if (!reget) {
            RiskAbility ability = abilityMapper.get(String.valueOf(request.getAttribute("phone")));
            if (ability == null) {
                pros = (ArrayList<FinaPro>) finaProMapper.getPro(6);
            } else {
                pros = (ArrayList<FinaPro>) finaProMapper.getPro(getLevel(ability.getScore()) + 1);
            }
        }
        if (pros.size() < 8) {
            List<FinaPro> temp = finaProMapper.getPro(6);
            Collections.shuffle(temp);
            pros.addAll(temp);
        }
//        Collections.shuffle(pros);
        return pros;
    }


    private int getLevel(int sco) {
        if (sco < 30)
            return 1;
        if (sco < 40)
            return 2;
        if (sco < 62)
            return 3;
        if (sco < 78)
            return 4;
        else return 5;
    }

    private String[] getKey() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<String> keys = new ArrayList<>();
        InsuTest test = insuTestMapper.get(String.valueOf(request.getAttribute("phone")));

        if (test != null) {
            int[] temp = {-1, -1, -1, -1};
            for (int k = 0; k < 4; k++) {
                int max = new Random().nextInt(10) % 10;
                for (int j = 0; j < k; j++) {
                    if (max == temp[j]) {
                        max = new Random().nextInt(10) % 10;
                        j = 0;
                        continue;
                    }
                }
                for (int i = 0; i < keyWord.length; i++) {
                    boolean flag = false;
                    for (int j = 0; j < k; j++) {
                        if (i == temp[j]) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        continue;
                    }
                    if (getData(keyWord.keys_en[max], test) < getData(keyWord.keys_en[i], test)) {
                        max = i;
                    }
                }
                temp[k] = max;
                keys.addAll(Arrays.asList(getWords(keyWord.keys_en[max])));
            }
        }
        return keys.toArray(new String[0]);
    }

    private float getData(String key, InsuTest test) throws IllegalAccessException, NoSuchFieldException {
        Class<?> cls = InsuTest.class;
        Field f = null;
        f = cls.getDeclaredField(key);
        f.setAccessible(true);//为 true 则表示反射的对象在使用时取消 Java 语言访问检查
//        System.out.println(key + " : " + f.getFloat(test));
        return f.getFloat(test);
    }

    private String[] getWords(String key) throws NoSuchFieldException, IllegalAccessException {
        Class<?> cls = KeyWord.class;
        Field f = null;
        f = cls.getDeclaredField(key);
        f.setAccessible(true);//为 true 则表示反射的对象在使用时取消 Java 语言访问检查
        System.out.println(key);
        return (String[]) f.get(keyWord);
    }
}
