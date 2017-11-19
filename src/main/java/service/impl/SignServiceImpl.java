package service.impl;

import dao.CodeMapper;
import dao.UserMapper;
import dto.SignUpParam;
import dto.response.UserInfo;
import entity.Code;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.SignService;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by p on 2017/11/15.
 */
@Service
public class SignServiceImpl implements SignService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CodeMapper codeMapper;

    @Override
    public boolean verCode(String phone, String code) {
        Code codeInfo = codeMapper.findByPhone(phone);
        if (codeInfo == null || codeInfo.getCode() == null
                || !codeInfo.getCode().equals(code) || isExpired(codeInfo.getDate())) {
            return false;
        }

        return true;
    }

    @Override
    public UserInfo signUp(SignUpParam param) {
        User tokenUser = userMapper.findByToken(param.getToken());
        if (tokenUser != null) {
            userMapper.updateToken(tokenUser.getPhone(), null);
        }
        User user = new User();
        user.setPhone(param.getPhone());
        user.setName("用户" + param.getPhone().substring(param.getPhone().length() - 4));
        user.setPsw(param.getPsw());
        user.setToken(param.getToken());
        String url = "http://104.236.132.15:8080/CitiCup/avatar/default.png";
        user.setAvatar(url);
        userMapper.insert(user);
        UserInfo info = new UserInfo();
        info.setName(user.getName());
        info.setPhone(user.getPhone());
        info.setAvatar(user.getAvatar());
        return info;
    }

    @Override
    public Code getCode(String phone) {
        Code oldCode = codeMapper.findByPhone(phone);
        Code newCode = new Code(phone);
        if (oldCode != null) {
            codeMapper.update(newCode);
        } else {
            codeMapper.insert(newCode);
        }
        return newCode;
    }

    public static boolean isExpired(Timestamp date) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date utilDate = calendar.getTime();
        //java.util.Date日期转换成转成java.sql.Date格式
        Timestamp nowDate = new Timestamp(utilDate.getTime());
        return nowDate.after(date);
    }
}
