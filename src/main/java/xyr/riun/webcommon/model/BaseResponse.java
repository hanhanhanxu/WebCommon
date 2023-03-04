package xyr.riun.webcommon.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: HanXu
 * on 2020/9/1
 * Class description: controller对外统一返回体
 */
@Data
@AllArgsConstructor
@Builder
public class BaseResponse<T>  {
    private int code;
    private String msg;
    private T data;

    public BaseResponse(T data) {
        code = HttpServletResponse.SC_OK;
        msg = "success";
        this.data = data;
    }

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
