package controller;

import dao.FinaProMapper;
import dao.InsuProMapper;
import dao.InsuTestMapper;
import dao.RiskAbilityMapper;
import dto.response.Response;
import entity.FinaPro;
import entity.InsuPro;
import entity.InsuTest;
import entity.RiskAbility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by p on 2017/9/14.
 */
@RestController
@RequestMapping(value = "/set")
@CrossOrigin
public class GetProController {

    @Autowired
    InsuProMapper insuProMapper;
    @Autowired
    FinaProMapper finaProMapper;
    @Autowired
    InsuTestMapper insuTestMapper;
    @Autowired
    RiskAbilityMapper abilityMapper;

    @RequestMapping(value = "/getInsuPro", method = RequestMethod.POST)
    public Response getInsuPro(boolean reget,HttpServletRequest request) {
        ArrayList<InsuPro> pros = new ArrayList<InsuPro>();
        if (!reget) {
            InsuTest test = insuTestMapper.get(String.valueOf(request.getAttribute("phone")));
            if (test == null) {
                List<InsuPro> temp = insuProMapper.getPro("");
                Collections.shuffle(temp);
                if (temp.size() > 0)
                    pros.addAll(temp.subList(0, temp.size() > 5 ? 5 : temp.size()));
            } else {
                ArrayList<String> keys = new ArrayList<String>(Arrays.asList(test.getKeyword().split(",")));
                for (String key : keys) {
                    List<InsuPro> temp = insuProMapper.getPro(key);
                    System.out.println(key + "  " + temp.size());
                    if (temp.size() > 0)
                        pros.addAll(temp.subList(0, temp.size() > 2 ? 2 : temp.size()));
                }
                Collections.shuffle(pros);
            }
        }
        if (pros.size() < 8) {
            int x = pros.size();
            List<InsuPro> temp = insuProMapper.getPro("");
            Collections.shuffle(temp);
            for (int i = 0; i < 8 - x; i++){
                pros.add(temp.get((new Random().nextInt(temp.size()))));
            }
        }
        return new Response<ArrayList<InsuPro>>().SUCCESS(pros);
    }

    @RequestMapping(value = "/getFinaPro", method = RequestMethod.POST)
    public Response getFinaPro(boolean reget,HttpServletRequest request) {
        ArrayList<FinaPro> pros = new ArrayList<FinaPro>();
        if (!reget) {
            RiskAbility ability = abilityMapper.get(String.valueOf(request.getAttribute("phone")));
            if (ability == null) {
                List<FinaPro> temp = finaProMapper.getPro(6);
                Collections.shuffle(temp);
                if (temp.size() > 0)
                    pros.addAll(temp.subList(0, temp.size() > 5 ? 5 : temp.size()));
            } else {
                List<FinaPro> temp = finaProMapper.getPro(getLevel(ability.getScore())+1);
                Collections.shuffle(temp);
                if (temp.size() > 0)
                    pros.addAll(temp.subList(0, temp.size() > 5 ? 5 : temp.size()));
            }
        }
        if (pros.size() < 8) {
            int x = pros.size();
            List<FinaPro> temp = finaProMapper.getPro(6);
            Collections.shuffle(temp);
            for (int i = 0; i < 8 - x; i++){
                pros.add(temp.get((new Random().nextInt(temp.size()))));
            }
        }
        return new Response<ArrayList<FinaPro>>().SUCCESS(pros);
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
}
