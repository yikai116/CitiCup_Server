package helper;

import dto.response.Response;
import dto.response.Status;

/**
 * Created by p on 2017/7/31.
 */
public class ResponseHelper {
    public static final Response SYSTEM_ERROR = new Response(new Status(0,"系统错误"));
}
