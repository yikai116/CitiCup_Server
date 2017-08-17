package dto.response;

/**
 * Created by p on 2017/7/31.
 */
public class Response<Obj> {
    private Status status;
    private Obj data;

    public Response(){

    }
    public Response(Status status, Obj data) {
        this.status = status;
        this.data = data;
    }

    public Response(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Obj getData() {
        return data;
    }

    public void setData(Obj data) {
        this.data = data;
    }

    public Response SUCCESS(Obj data){
        return new Response<Obj>(new Status(1,"成功"),data);
    }

    public static final Response SUCCESS = new Response(new Status(1,"成功"));
    /**
     * 系统错误
     * -1
     */
    public static final Response SYSTEM_ERROR = new Response(new Status(-1,"系统错误"));

    /**
     * 用户不存在
     * -2
     */
    public static final Response NO_USER = new Response(new Status(-2,"该用户不存在"));

    /**
     * 密码错误
     * -3
     */
    public static final Response PSW_ERROR = new Response(new Status(-3,"密码错误"));


    /**
     * 验证码错误
     * -4
     */
    public static final Response VERCODE_ERROR = new Response(new Status(-4,"验证码错误或已失效，请重新获取"));

    /**
     * 用户已存在
     * -5
     */
    public static final Response USER_REGISTERED = new Response(new Status(-5,"该用户已经注册过了"));


    /**
     * header中没有token信息
     * -6
     */
    public static final Response NO_TOKEN_ERROR = new Response(new Status(-6,"缺少验证信息，请检查或重新登录"));

    /**
     * token没有与用户匹配
     * -7
     */
    public static final Response INVALIED_TOKEN_ERROR = new Response(new Status(-7,"验证信息错误，请检查或重新登录"));

    /**
     * 文件不存在
     * -8
     */
    public static final Response NO_FILE = new Response(new Status(-8,"文件不存在"));

    /**
     * 文件不存在
     * -9
     */
    public static final Response UPLOAD_FAIL = new Response(new Status(-9,"文件上传失败"));

}
