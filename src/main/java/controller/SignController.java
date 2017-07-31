package controller;

import dao.CodeMapper;
import dao.UserMapper;
import dto.FindPswParam;
import dto.SignInParam;
import dto.SignUpParam;
import dto.response.Response;
import dto.response.Status;
import entity.Code;
import entity.User;
import helper.Helper;
import helper.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by p on 2017/7/19.
 */
@RestController
@RequestMapping(value = "/")
@CrossOrigin
public class SignController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CodeMapper codeMapper;

    /**
     * 登录
     * @param param 用户参数
     * @return 返回信息，以及用户验证信息
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public Response signIn(@RequestBody SignInParam param){
        try {
            User user = userMapper.findByPhone(param.getPhone());
            if (user == null){
                return new Response(new Status(0,"没有该用户"));
            }
            if (!user.getPsw().equals(param.getPsw())){
                return new Response(new Status(0,"密码错误"));
            }
            userMapper.updateToken(param.getPhone(),param.getToken());
            return new Response(new Status(1,"登录成功"));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseHelper.SYSTEM_ERROR;
        }
    }

    /**
     * 注册
     * @param param 用户参数
     * @return 返回信息，以及用户验证信息
     */
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public Response signUp(@RequestBody SignUpParam param){
        try {
            Code code = codeMapper.findByPhone(param.getPhone());
            if (code == null || code.getCode() == null
                    || !code.getCode().equals(param.getVerCode())){
                return new Response(new Status(0,"验证信息错误，请重试"));
            }
            if (Helper.isExpired(code.getDate())){
                return new Response(new Status(0,"验证信息过期，请重试"));
            }
            User user = userMapper.findByPhone(param.getPhone());
            if (user!= null){
                return new Response(new Status(0,"该手机号已被注册"));
            }
            user = new User();
            user.setPhone(param.getPhone());
            user.setName("用户" + param.getPhone().substring(param.getPhone().length()-4));
            user.setPsw(param.getPsw());
            user.setToken(param.getToken());
            userMapper.insert(user);
            return new Response(new Status(1,"注册成功"));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseHelper.SYSTEM_ERROR;
        }
    }

    @RequestMapping(value = "/findPsw", method = RequestMethod.POST)
    public Response findPsw(@RequestBody FindPswParam param){
        try {
            Code code = codeMapper.findByPhone(param.getPhone());
            if (code == null || code.getCode() == null
                    || !code.getCode().equals(param.getVerCode())){
                return new Response(new Status(0,"验证信息错误，请重试"));
            }
            if (Helper.isExpired(code.getDate())){
                return new Response(new Status(0,"验证信息过期，请重试"));
            }
            User user = userMapper.findByPhone(param.getPhone());
            if (user == null){
                return new Response(new Status(0,"该手机号未注册"));
            }
            user.setPsw(param.getPsw());
            userMapper.updatePsw(param.getPhone(),param.getPsw());
            return new Response(new Status(1,"修改成功"));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseHelper.SYSTEM_ERROR;
        }
    }

    @RequestMapping(value = "getSignUpVerCode", method = RequestMethod.POST)
    public Response getSignUpVerCode(String phone){
        try {
            User user = userMapper.findByPhone(phone);
            if (user == null){
                return new Response(new Status(0,"该手机号已注册"));
            }
            return new Response<Code>(new Status(1,"获取成功"),getCode(phone));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseHelper.SYSTEM_ERROR;
        }
    }

    @RequestMapping(value = "getFindPswVerCode", method = RequestMethod.POST)
    public Response getFindPswVerCode(String phone){
        try {
            User user = userMapper.findByPhone(phone);
            if (user == null){
                return new Response(new Status(0,"该手机号未注册"));
            }
            return new Response<Code>(new Status(1,"获取成功"),getCode(phone));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseHelper.SYSTEM_ERROR;
        }
    }

    private Code getCode(String phone){
        Code oldCode = codeMapper.findByPhone(phone);
        Code newCode = new Code(phone);
        if (oldCode != null){
            codeMapper.update(newCode);
        }
        else {
            codeMapper.insert(newCode);
        }
        return newCode;
    }
}
