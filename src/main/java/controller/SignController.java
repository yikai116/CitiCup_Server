package controller;

import dao.CodeMapper;
import dao.QuestionMapper;
import dao.UserMapper;
import dto.FindPswParam;
import dto.QuesAns;
import dto.SignInParam;
import dto.SignUpParam;
import dto.response.Response;
import dto.response.UserInfo;
import entity.Question;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.SignService;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

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

    @Autowired
    private SignService service;

    @Autowired
    private QuestionMapper questionMapper;
    /**
     * 登录
     *
     * @param param 用户参数
     * @return 返回信息，以及用户验证信息
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public Response signIn(@RequestBody SignInParam param) {
        User user = userMapper.findByPhone(param.getPhone());
        if (user == null) {
            return Response.NO_USER;
        }
        if (!user.getPsw().equals(param.getPsw())) {
            return Response.PSW_ERROR;
        }
        User tokenUser = userMapper.findByToken(param.getToken());
        if (tokenUser != null) {
            userMapper.updateToken(tokenUser.getPhone(), null);
        }
        userMapper.updateToken(param.getPhone(), param.getToken());
        UserInfo info = new UserInfo();
        info.setName(user.getName());
        info.setPhone(user.getPhone());
        info.setAvatar(user.getAvatar());
        return new Response<UserInfo>().SUCCESS(info);

    }

    /**
     * 注册
     *
     * @param param 用户参数
     * @return 返回信息，以及用户验证信息
     */
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public Response signUp(@RequestBody SignUpParam param) {

        // 验证验证码
        if (!service.verCode(param.getPhone(),param.getVerCode())){
            return Response.VERCODE_ERROR;
        }

        // 判断token是否已经存在
        User user = userMapper.findByPhone(param.getPhone());
        if (user != null) {
            return Response.USER_REGISTERED;
        }

        return new Response<UserInfo>().SUCCESS(service.signUp(param));

    }

    /**
     * 找回密码
     *
     * @param param 用户参数
     * @return 返回信息
     */
    @RequestMapping(value = "/findPsw", method = RequestMethod.POST)
    public Response findPsw(@RequestBody FindPswParam param) {
        // 验证验证码
        if (!service.verCode(param.getPhone(),param.getVerCode())){
            return Response.VERCODE_ERROR;
        }

        User user = userMapper.findByPhone(param.getPhone());
        if (user == null) {
            return Response.NO_USER;
        }
        user.setPsw(param.getPsw());
        userMapper.updatePsw(param.getPhone(), param.getPsw());
        return Response.SUCCESS;
    }

    /**
     * 得到注册验证码
     *
     * @param phone 用户手机号
     * @return 返回信息
     */
    @RequestMapping(value = "getSignUpVerCode", method = RequestMethod.POST)
    public Response getSignUpVerCode(String phone) {
        User user = userMapper.findByPhone(phone);
        if (user != null) {
            return Response.USER_REGISTERED;
        }
        return new Response<String>().SUCCESS(service.getCode(phone).getCode());
    }

    /**
     * 得到找回密码验证码
     *
     * @param phone 用户手机号
     * @return 返回信息
     */
    @RequestMapping(value = "getFindPswVerCode", method = RequestMethod.POST)
    public Response getFindPswVerCode(String phone) {
        User user = userMapper.findByPhone(phone);
        if (user == null) {
            return Response.NO_USER;
        }
        return new Response<String>().SUCCESS(service.getCode(phone).getCode());
    }

    /**
     * 验证token
     *
     * @param token token
     * @return 验证结果
     */
    @RequestMapping(value = "verToken", method = RequestMethod.POST)
    public Response verToken(String token) {
        User user = userMapper.findByToken(token);
        if (user == null) {
            return Response.INVALIED_TOKEN_ERROR;
        }
        UserInfo info = new UserInfo();
        info.setName(user.getName());
        info.setPhone(user.getPhone());
        info.setAvatar(user.getAvatar());
        return new Response<UserInfo>().SUCCESS(info);
    }

    @RequestMapping(value = "/updateQues", method = RequestMethod.POST)
    public Response updateQues(@RequestParam String key,
                               @RequestParam String phone,
                               @RequestParam String value) throws NoSuchFieldException, IllegalAccessException, UnsupportedEncodingException {
        value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
        Question question = questionMapper.get(phone);
//        System.out.println(value + s2);
        boolean flag = false;
        if (question == null) {
            question = new Question();
            question.setPhone(phone);
            flag = true;
        }
        Class<?> cls = Question.class;
        Field f = null;
        f = cls.getDeclaredField(key);
        f.setAccessible(true);//为 true 则表示反射的对象在使用时取消 Java 语言访问检查
        f.set(question,value);
        if (flag)
            questionMapper.insert(question);
        else
            questionMapper.update(question);
        return Response.SUCCESS;
    }
}
