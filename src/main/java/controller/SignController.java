package controller;

import dao.UserMapper;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by p on 2017/7/19.
 */
@RestController
@RequestMapping(value = "/sign")
@CrossOrigin
public class SignController {
    @Autowired
    UserMapper userMapper;
    @RequestMapping(value = "/up",method = RequestMethod.POST)
    public String test(){
        return "123456";
    }
}
