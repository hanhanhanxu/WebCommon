package xyr.riun.webcommon.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyr.riun.webcommon.model.BaseResponse;
import xyr.riun.webcommon.model.ExceptionCodeEnum;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: HanXu
 * on 2021/5/18
 * Class description: 拦截错误：GlobalExceptionHandle拦不到的异常（进入controller之前出现的异常），当出错的时候，这里处理
 */
@Slf4j
@Controller
@RequestMapping("error")
public class GlobalErrorController implements ErrorController {

    @RequestMapping
    @ResponseBody
    public BaseResponse error(HttpServletResponse response) {
        //已经没有traceId了，说明已经走过了
        //2023-03-03 16:58:04,315 [traceId:] [http-nio-8080-exec-2] ERROR com.example.webmaas.exception.GlobalErrorController- ErrorController.error...
        //2023-03-03 16:58:04,315 [traceId:] [http-nio-8080-exec-2] ERROR com.example.webmaas.exception.GlobalErrorController- error: 服务器异常
        log.error("ErrorController.error...");
        int statusCode = response.getStatus();
        ExceptionCodeEnum exceptionCodeEnum = ExceptionCodeEnum.getByCode(statusCode);
        if (exceptionCodeEnum == null) {
            exceptionCodeEnum = ExceptionCodeEnum.Internal_Server_Error;
        }
        log.error("error: {}", exceptionCodeEnum.getMsg());
        BaseResponse baseResult = new BaseResponse(exceptionCodeEnum.getCode(), exceptionCodeEnum.getMsg());;
        return baseResult;
    }
}
