package controller;

import dao.UserInfoMapper;
import dto.response.Response;
import dto.response.Status;
import entity.RiskAbility;
import entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by p on 2017/9/15.
 */
@RestController
@RequestMapping(value = "/set")
@CrossOrigin
public class UserInfoController {

    @Autowired
    UserInfoMapper infoMapper;
    @RequestMapping(value = "/setUserInfo", method = RequestMethod.POST)
    public Response setUserInfo(@RequestBody UserInfo userInfo, HttpServletRequest request) {

        userInfo.setPhone(String.valueOf(request.getAttribute("phone")));
        if (infoMapper.get(userInfo.getPhone()) != null){
            infoMapper.update(userInfo);
        }
        else {
            infoMapper.insert(userInfo);
        }
        return Response.SUCCESS;
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public Response getUserInfo(HttpServletRequest request) {
        return new Response<UserInfo>().SUCCESS(infoMapper.get(String.valueOf(request.getAttribute("phone"))));
    }

}
