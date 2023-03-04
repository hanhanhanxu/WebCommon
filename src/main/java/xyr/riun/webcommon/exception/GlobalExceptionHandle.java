package xyr.riun.webcommon.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.retry.RetryException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyr.riun.webcommon.model.BaseResponse;
import xyr.riun.webcommon.model.CallExceptionVO;
import xyr.riun.webcommon.model.CustomerExceptionVO;
import xyr.riun.webcommon.utils.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: HanXu
 * on 2021/5/18
 * Class description: 统一异常处理
 * 业务异常 抛出CustomerExceptionVO
 * service层重试异常 抛出RetryException
 * 回调处理异常 抛出 CallExceptionVO
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandle {


    /**
     * 跟方法的上下顺序无关，系统优先给到捕获最小异常的方法，然后是大异常的方法
     * 只能捕获代码中手动抛出的 CustomerException 或 RuntimeException
     * @param e
     * @return
     */
    @ExceptionHandler(CustomerExceptionVO.class)
    public BaseResponse handleCustomerException(CustomerExceptionVO e, HttpServletRequest request) {
        log.error("异常request uri: {}", request.getRequestURI());
        log.error(e.getMessage(), e);
        return new BaseResponse((Integer) CommonUtil.getNotNull(e.getCode(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR), (String) CommonUtil.getOrDefault(e.getMsg(), e.getMessage()), e.getData());
    }


    /**
     * 处理重试之后仍异常的service
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(RetryException.class)
    public BaseResponse handleRetryException(RetryException e, HttpServletRequest request) {
        log.error("异常request uri: {}", request.getRequestURI());
        log.error(e.getMessage(), e);
        return new BaseResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, (String) CommonUtil.getOrDefault(e.getMessage(), "服务器异常（重试）"));
    }


    /**
     * 捕获回调异常：回调不能返回http status 200，所以手动响应异常http status
     * @param e
     * @param request
     */
    @ExceptionHandler(CallExceptionVO.class)
    public void handleCallException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.error("回调处理异常request uri: {}", request.getRequestURI());
        log.error(e.getMessage(), e);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
    }


    /**
     * 捕获剩余所有异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public BaseResponse handleLeftException(Exception e, HttpServletRequest request) {
        log.error("异常request uri: {}", request.getRequestURI());
        log.error(e.getMessage(), e);
        return new BaseResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器异常");
    }
}
