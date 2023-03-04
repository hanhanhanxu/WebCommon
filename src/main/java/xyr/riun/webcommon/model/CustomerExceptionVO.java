package xyr.riun.webcommon.model;

import lombok.Data;

/**
 * @author: HanXu
 * on 2021/5/18
 * Class description: 自定义异常：方便进行异常信息扩展，且与RuntimeException区分开
 */
@Data
public class CustomerExceptionVO extends RuntimeException {

    private int code;
    private String msg;
    private Object data;

    public CustomerExceptionVO(ExceptionCodeEnum exceptionCodeEnum) {
        code = exceptionCodeEnum.getCode();
        msg = exceptionCodeEnum.getMsg();
    }


    public CustomerExceptionVO(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CustomerExceptionVO(int code, String msg, Object data) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
