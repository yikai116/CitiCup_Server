package helper;

import dto.response.Response;
import dto.response.Status;

/**
 * Created by p on 2017/7/31.
 */
public class ResponseHelper {
    public static final Response SYSTEM_ERROR = new Response(new Status(-1,"系统错误"));
    /**
     * 获取成功
     */
    public static final int SUCCESS = 1;
    /**
     * 用户不存在
     */
    public static final int NO_USER = -2;
    /**
     * 密码错误
     */
    public static final int PSW_ERROR = -3;
    /**
     * 验证码错误
     */
    public static final int VERCODE_ERROR = -4;
    /**
     * 用户存在
     */
    public static final int USER_REGISTERED = -5;
}
