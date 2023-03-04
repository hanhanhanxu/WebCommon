package xyr.riun.webcommon.model;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: HanXu
 * on 2021/5/18
 * Class description: 通用异常枚举，供 CustomerException 使用
 */
public enum ExceptionCodeEnum {

    //通用异常
    /**
     * 400
     */
    Bad_Request(HttpServletResponse.SC_BAD_REQUEST, "请求信息错误,请检查参数"),
    /**
     * 401
     */
    UnAuth(HttpServletResponse.SC_UNAUTHORIZED, "请先登陆"),
    /**
     * 403
     */
    Forbidden(HttpServletResponse.SC_FORBIDDEN, "无权查看"),
    /**
     * 404
     */
    Not_Fount(HttpServletResponse.SC_NOT_FOUND, "未找到该路径或资源"),
    /**
     * 405
     */
    Method_Not_Allowed(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "请求方式不支持"),

    /**
     * 500
     */
    Internal_Server_Error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器异常"),
    /**
     * 503
     */
    Service_Unavailable(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "请求超时"),

    //自定义扩展其他业务异常
    /**
     * 400
     */
    Username_Or_PassWord_Error(HttpServletResponse.SC_BAD_REQUEST, "用户名密码错误"),
    /**
     * 400
     */
    Username_Exist(HttpServletResponse.SC_BAD_REQUEST, "用户名已存在"),
    /**
     * 400
     */
    ValidCode_Error(HttpServletResponse.SC_BAD_REQUEST, "验证码不正确"),
    /**
     * 400
     */
    NOCHATBOTID(HttpServletResponse.SC_BAD_REQUEST, "缺少chatbotId"),

    ;
    private int code;
    private String msg;

    ExceptionCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public static ExceptionCodeEnum getByCode(int code) {
        ExceptionCodeEnum[] enums = ExceptionCodeEnum.values();
        for (ExceptionCodeEnum exceptionCodeEnum : enums) {
            if (exceptionCodeEnum.code == code) {
                return exceptionCodeEnum;
            }
        }
        return null;
    }
}
