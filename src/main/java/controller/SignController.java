package controller;

import dao.CodeMapper;
import dao.UserMapper;
import dto.FindPswParam;
import dto.SignInParam;
import dto.SignUpParam;
import dto.response.Response;
import dto.response.Status;
import dto.response.UserInfo;
import entity.Code;
import entity.User;
import helper.Helper;
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
                return Response.NO_USER;
            }
            if (!user.getPsw().equals(param.getPsw())){
                return Response.PSW_ERROR;
            }
            User tokenUser = userMapper.findByToken(param.getToken());
            if (tokenUser != null){
                userMapper.updateToken(tokenUser.getPhone(),null);
            }
            userMapper.updateToken(param.getPhone(),param.getToken());
            return new Response<UserInfo>().SUCCESS(user.toUserInfo());
        }
        catch (Exception e){
            e.printStackTrace();
            return Response.SYSTEM_ERROR;
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
            //验证验证码
            Code code = codeMapper.findByPhone(param.getPhone());
            if (code == null || code.getCode() == null
                    || !code.getCode().equals(param.getVerCode())){
                return Response.VERCODE_ERROR;
            }

            User user = userMapper.findByPhone(param.getPhone());
            if (user!= null){
                return Response.USER_REGISTERED;
            }
            User tokenUser = userMapper.findByToken(param.getToken());
            if (tokenUser != null){
                userMapper.updateToken(tokenUser.getPhone(),null);
            }
            user = new User();
            user.setPhone(param.getPhone());
            user.setName("用户" + param.getPhone().substring(param.getPhone().length()-4));
            user.setPsw(param.getPsw());
            user.setToken(param.getToken());
            userMapper.insert(user);
            return new Response<UserInfo>().SUCCESS(user.toUserInfo());
        }
        catch (Exception e){
            e.printStackTrace();
            return Response.SYSTEM_ERROR;
        }
    }

    /**
     * 找回密码
     * @param param 用户参数
     * @return 返回信息
     */
    @RequestMapping(value = "/findPsw", method = RequestMethod.POST)
    public Response findPsw(@RequestBody FindPswParam param){
        try {
            //验证验证码
            Code code = codeMapper.findByPhone(param.getPhone());
            if (code == null || code.getCode() == null
                    || !code.getCode().equals(param.getVerCode())){
                return Response.VERCODE_ERROR;
            }

            User user = userMapper.findByPhone(param.getPhone());
            if (user == null){
                return Response.NO_USER;
            }
            user.setPsw(param.getPsw());
            userMapper.updatePsw(param.getPhone(),param.getPsw());
            return Response.SUCCESS;
        }
        catch (Exception e){
            e.printStackTrace();
            return Response.SYSTEM_ERROR;
        }
    }

    /**
     * 得到注册验证码
     * @param phone 用户手机号
     * @return 返回信息
     */
    @RequestMapping(value = "getSignUpVerCode", method = RequestMethod.POST)
    public Response getSignUpVerCode(String phone){
        try {
            User user = userMapper.findByPhone(phone);
            if (user != null){
                return Response.USER_REGISTERED;
            }
            return new Response<String>().SUCCESS(getCode(phone).getCode());
        }
        catch (Exception e){
            e.printStackTrace();
            return Response.SYSTEM_ERROR;
        }
    }

    /**
     * 得到找回密码验证码
     * @param phone 用户手机号
     * @return 返回信息
     */
    @RequestMapping(value = "getFindPswVerCode", method = RequestMethod.POST)
    public Response getFindPswVerCode(String phone){
        try {
            User user = userMapper.findByPhone(phone);
            if (user == null){
                return Response.NO_USER;
            }
            return new Response<String>().SUCCESS(getCode(phone).getCode());
        }
        catch (Exception e){
            e.printStackTrace();
            return Response.SYSTEM_ERROR;
        }
    }

    /**
     * 验证token
     * @param token token
     * @return 验证结果
     */
    @RequestMapping(value = "verToken", method = RequestMethod.POST)
    public Response verToken(String token){
        try {
            User user = userMapper.findByToken(token);
            if (user == null){
                return Response.INVALIED_TOKEN_ERROR;
            }
            return Response.SUCCESS;
        }
        catch (Exception e){
            e.printStackTrace();
            return Response.SYSTEM_ERROR;
        }
    }

    /**
     * 辅助得到验证码
     * @param phone 手机号
     * @return 验证码
     */
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
