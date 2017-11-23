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
import service.ProService;

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
    private ProService proService;

    @RequestMapping(value = "/getInsuPro", method = RequestMethod.POST)
    public Response getInsuPro(boolean reget) throws NoSuchFieldException, IllegalAccessException {

        return new Response<ArrayList<InsuPro>>().SUCCESS(proService.getInsuPro(reget));
    }

    @RequestMapping(value = "/getFinaPro", method = RequestMethod.POST)
    public Response getFinaPro(boolean reget) {

        return new Response<ArrayList<FinaPro>>().SUCCESS(proService.getFinaPro(reget));
    }

    @RequestMapping(value = "/getInsuProById", method = RequestMethod.POST)
    public Response getInsuProById(int id){
        return new Response<InsuPro>().SUCCESS(proService.getInsuProById(id));
    }
}
