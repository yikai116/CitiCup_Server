package controller;

import dao.*;
import dto.response.FinaPreferInfo;
import dto.response.InsuPreferInfo;
import dto.response.Response;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.SimilarityService;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by p on 2017/9/13.
 */
@RestController
@RequestMapping(value = "/set")
@CrossOrigin
public class TestController {
    @Autowired
    private RiskAbilityMapper abilityMapper;
    @Autowired
    private InsuPreferMapper insuPreferMapper;
    @Autowired
    private FinaPreferMapper finaPreferMapper;
    @Autowired
    private InsuTestMapper insuTestMapper;
    @Autowired
    private SimilarityService similarityService;
    @Autowired
    private InsuProMapper insuProMapper;
    @Autowired
    private ClickMapper clickMapper;

    @RequestMapping(value = "/setAbility", method = RequestMethod.POST)
    public Response setRiskAbility(int sco, HttpServletRequest request) {
        RiskAbility ability = new RiskAbility();
        ability.setPhone(String.valueOf(request.getAttribute("phone")));
        ability.setScore(sco);
        if (abilityMapper.get(ability.getPhone()) != null) {
            abilityMapper.update(ability);
        } else {
            abilityMapper.insert(ability);
        }
        return Response.SUCCESS;
    }


    @RequestMapping(value = "/setKeyword", method = RequestMethod.POST)
    public Response setKeyword(String keyword, HttpServletRequest request) throws NoSuchFieldException, IllegalAccessException, IOException {
        System.out.println(keyword);
        InsuTest entity = new InsuTest();
        entity.setPhone(String.valueOf(request.getAttribute("phone")));
        ArrayList<String> keys = new ArrayList<String>(Arrays.asList(keyword.split(",")));
        similarityService.initInsuTest(keys);

        return Response.SUCCESS;
    }

    @RequestMapping(value = "/verTest", method = RequestMethod.POST)
    public Response verTest(HttpServletRequest request) throws NoSuchFieldException, IllegalAccessException, IOException {
        InsuTest test = insuTestMapper.get(String.valueOf(request.getAttribute("phone")));
        if (test == null)
            return Response.SYSTEM_ERROR;
        return Response.SUCCESS;
    }


    @RequestMapping(value = "/setInsuPrefer", method = RequestMethod.POST)
    public Response setInsuPrefer(@RequestBody InsuPreferInfo info, HttpServletRequest request) {
        InsuPrefer entity = info.toInsuPrefer(String.valueOf(request.getAttribute("phone")));

        if (insuPreferMapper.get(entity.getPhone()) != null) {
            insuPreferMapper.update(entity);
        } else {
            insuPreferMapper.insert(entity);
        }
        return Response.SUCCESS;
    }


    @RequestMapping(value = "/setFinaPrefer", method = RequestMethod.POST)
    public Response setFinaPrefer(@RequestBody FinaPreferInfo info, HttpServletRequest request) {
        FinaPrefer entity = info.toFinaPrefer(String.valueOf(request.getAttribute("phone")));

        if (finaPreferMapper.get(entity.getPhone()) != null) {
            finaPreferMapper.update(entity);
        } else {
            finaPreferMapper.insert(entity);
        }
        return Response.SUCCESS;
    }

    @RequestMapping(value = "/clickInsuPro", method = RequestMethod.POST)
    public void clickInsuPro(int id, HttpServletRequest request) throws IllegalAccessException, NoSuchFieldException, IOException {
        Click click = new Click();
        click.setTime(new Time(new Date().getTime()));
        click.setPhone(String.valueOf(request.getAttribute("phone")));
        click.setId(id);
        clickMapper.insert(click);

        InsuPro pro = insuProMapper.getOneById(id);
        ArrayList<String> keys = new ArrayList<>();
        keys.add(pro.getType());
        similarityService.initInsuTest(keys);
        return;
    }
}
