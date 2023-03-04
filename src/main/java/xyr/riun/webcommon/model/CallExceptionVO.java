package xyr.riun.webcommon.model;

import lombok.Data;

/**
 * @author: HanXu
 * on 2021/12/10
 * Class description: 回调异常
 */
@Data
public class CallExceptionVO extends RuntimeException {
    private int code;
    private String msg;
    private Object data;

    public CallExceptionVO(String msg) {
        super(msg);
        this.msg = msg;
    }
}
