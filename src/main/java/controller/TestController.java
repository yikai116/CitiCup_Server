package controller;

import dao.*;
import dto.response.FinaPreferInfo;
import dto.response.InsuPreferInfo;
import dto.response.Response;
import dto.response.Status;
import entity.FinaPrefer;
import entity.InsuPrefer;
import entity.InsuTest;
import entity.RiskAbility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by p on 2017/9/13.
 */
@RestController
@RequestMapping(value = "/set")
@CrossOrigin
public class TestController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CodeMapper codeMapper;
    @Autowired
    private RiskAbilityMapper abilityMapper;
    @Autowired
    private InsuTestMapper insuTestMapper;
    @Autowired
    private InsuPreferMapper insuPreferMapper;
    @Autowired
    private FinaPreferMapper finaPreferMapper;

    @RequestMapping(value = "/setAbility", method = RequestMethod.POST)
    public Response setRiskAbility(int sco, HttpServletRequest request) {
        RiskAbility ability = new RiskAbility();
        ability.setPhone(String.valueOf(request.getAttribute("phone")));
        ability.setScore(sco);
        if (abilityMapper.get(ability.getPhone())!=null){
            abilityMapper.update(ability);
        }
        else {
            abilityMapper.insert(ability);
        }
        return Response.SUCCESS;
    }

    @RequestMapping(value = "/getAbility", method = RequestMethod.POST)
    public Response getRiskAbility(HttpServletRequest request) {
        RiskAbility ability = abilityMapper.get(String.valueOf(request.getAttribute("phone")));

        if (ability!=null){
            return new Response<Integer>().SUCCESS(ability.getScore());
        }
        else {
            return new Response(new Status(0,"您还没进行风险测试~"));
        }
    }

    @RequestMapping(value = "/setKeyword", method = RequestMethod.POST)
    public Response setKeyword(String keyword, HttpServletRequest request) {
        InsuTest entity = new InsuTest();
        entity.setPhone(String.valueOf(request.getAttribute("phone")));
        entity.setKeyword(keyword);
        if (insuTestMapper.get(entity.getPhone())!=null){
            insuTestMapper.update(entity);
        }
        else {
            insuTestMapper.insert(entity);
        }
        return Response.SUCCESS;
    }

    @RequestMapping(value = "/getKeyword", method = RequestMethod.POST)
    public Response getKeyword(HttpServletRequest request) {
        InsuTest entity = insuTestMapper.get(String.valueOf(request.getAttribute("phone")));

        if (entity!=null){
            return new Response<String>().SUCCESS(entity.getKeyword());
        }
        else {
            return new Response(new Status(0,"您还没进行保险测试~"));
        }
    }

    @RequestMapping(value = "/setInsuPrefer", method = RequestMethod.POST)
    public Response setInsuPrefer(@RequestBody InsuPreferInfo info, HttpServletRequest request) {
        InsuPrefer entity = info.toInsuPrefer(String.valueOf(request.getAttribute("phone")));

        if (insuPreferMapper.get(entity.getPhone())!=null){
            insuPreferMapper.update(entity);
        }
        else {
            insuPreferMapper.insert(entity);
        }
        return Response.SUCCESS;
    }

    @RequestMapping(value = "/getInsuPrefer", method = RequestMethod.POST)
    public Response getInsuPrefer(HttpServletRequest request) {
        InsuPrefer entity = insuPreferMapper.get(String.valueOf(request.getAttribute("phone")));

        if (entity!=null){
            return new Response<InsuPreferInfo>().SUCCESS(entity.toInsuPreferInfo());
        }
        else {
            return new Response(new Status(0,"您还没进行保险偏好设置~"));
        }
    }

    @RequestMapping(value = "/setFinaPrefer", method = RequestMethod.POST)
    public Response setFinaPrefer(@RequestBody FinaPreferInfo info, HttpServletRequest request) {
        FinaPrefer entity = info.toFinaPrefer(String.valueOf(request.getAttribute("phone")));

        if (finaPreferMapper.get(entity.getPhone())!=null){
            finaPreferMapper.update(entity);
        }
        else {
            finaPreferMapper.insert(entity);
        }
        return Response.SUCCESS;
    }

    @RequestMapping(value = "/getFinaPrefer", method = RequestMethod.POST)
    public Response getFinaPrefer(HttpServletRequest request) {
        FinaPrefer entity = finaPreferMapper.get(String.valueOf(request.getAttribute("phone")));

        if (entity!=null){
            return new Response<FinaPreferInfo>().SUCCESS(entity.toFinaPreferInfo());
        }
        else {
            return new Response(new Status(0,"您还没进行理财偏好设置~"));
        }
    }

}
