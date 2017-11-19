package service;

import dto.SignUpParam;
import dto.response.UserInfo;
import entity.Code;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by p on 2017/11/15.
 */
@Service
@Transactional
public interface SignService {
    boolean verCode(String phone, String code);
    UserInfo signUp(SignUpParam param);
    Code getCode(String phone);
}
