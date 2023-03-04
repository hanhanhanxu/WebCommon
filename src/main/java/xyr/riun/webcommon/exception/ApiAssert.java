package xyr.riun.webcommon.exception;

import org.springframework.stereotype.Component;
import xyr.riun.webcommon.model.CustomerExceptionVO;
import xyr.riun.webcommon.model.ExceptionCodeEnum;
import xyr.riun.webcommon.model.ReturnCodeDict;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: HanXu
 * on 2021/5/18
 * Class description: 代码工具类：判断代码逻辑，不符合则抛出异常
 */
@Component
public class ApiAssert {

    public static void notNull(Object a) {
        if (a == null) {
            failure(ExceptionCodeEnum.Bad_Request);
        }
        if (a instanceof String) {
            if (((String) a).length() == 0) {
                failure(ExceptionCodeEnum.Bad_Request);
            }
        }
    }

    public static void notNull(Object a, String msg) {
        if (a == null) {
            badRequest(msg);
        }
        if (a instanceof String) {
            if (((String) a).length() == 0) {
                badRequest(msg);
            }
        }
    }

    //-------------------------400-----------------------

    public static void badRequest(String msg) {
        throw new CustomerExceptionVO(ReturnCodeDict.REQ_ERR, msg);
    }

    //--------------------------500-----------------------
    public static void failure(ExceptionCodeEnum exceptionCodeEnum) {
        throw new CustomerExceptionVO(exceptionCodeEnum);
    }

    public static void failure(String msg, Object data) {
        throw new CustomerExceptionVO(ReturnCodeDict.SER_ERR, msg, data);
    }

    public static void failure(int code, String msg, Object data) {
        throw new CustomerExceptionVO(code, msg, data);
    }

    public static void failure(Object data) {
        throw new CustomerExceptionVO(ReturnCodeDict.SER_ERR, "error", data);
    }

    //--------------------------业务工具----------------------

    public static void noChatbotId() {
        failure(ExceptionCodeEnum.NOCHATBOTID);
    }

    public static void depfail(Object data) {
        failure("运营商返回数据状态不为成功", data);
    }

    public static void forbidden() {
        throw new CustomerExceptionVO(ExceptionCodeEnum.Forbidden);
    }

    public static void internal() {
        throw new CustomerExceptionVO(ExceptionCodeEnum.Internal_Server_Error);
    }


    public static void serverError() {
        failure(ExceptionCodeEnum.Internal_Server_Error);
    }

    public static void serverError(String msg) {
        throw new CustomerExceptionVO(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, msg);
    }
}
