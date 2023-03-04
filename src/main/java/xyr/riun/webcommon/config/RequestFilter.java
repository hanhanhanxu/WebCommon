package xyr.riun.webcommon.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import xyr.riun.webcommon.model.GlobalConstant;
import xyr.riun.webcommon.utils.SerialNumberUtil;
import xyr.riun.webcommon.utils.ThreadLocalUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: HanXu
 * on 2021/11/17
 * Class description: 对请求的一些处理
 */
@Slf4j
@Component
public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 请求线程添加traceId；slf4j日志框架中添加key为RequestId，value为traceId的变量；并将traceId放入响应头的RequestId字段里
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        //在当前线程内存中放入唯一变量
        ThreadLocalUtil.putCurrentThreadVal(SerialNumberUtil.getSerialNumberNew2());
        //将requestId响应给请求方
        httpResponse.setHeader(GlobalConstant.REQUEST_ID, ThreadLocalUtil.getCurrentThreadVal());
        //在slf4j日志框架中记录RequestId，值为线程中存放的唯一变量，用以标记一个请求在其生命流程中的行为
        MDC.put(GlobalConstant.REQUEST_ID, ThreadLocalUtil.getCurrentThreadVal());

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
