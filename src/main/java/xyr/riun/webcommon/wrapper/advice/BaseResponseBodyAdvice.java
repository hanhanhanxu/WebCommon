package xyr.riun.webcommon.wrapper.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import xyr.riun.webcommon.model.BaseResponse;
import xyr.riun.webcommon.utils.JacksonUtil;
import xyr.riun.webcommon.wrapper.annotation.ResultUnite;

import java.lang.reflect.Method;

/**
 * @author: HanXu
 * on 2021/6/11
 * Class description: 统一返回结果
 */
@ControllerAdvice
public class BaseResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final String CONVERT_NAME = "org.springframework.http.converter.StringHttpMessageConverter";


    private boolean isResultUnite(MethodParameter methodParameter, Class aClass) {
        Method method = methodParameter.getMethod();
        return aClass.isAnnotationPresent(ResultUnite.class) || method.isAnnotationPresent(ResultUnite.class);
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return isResultUnite(methodParameter, aClass);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //处理String类型的返回值
        if (CONVERT_NAME.equalsIgnoreCase(selectedConverterType.getName())) {
            return JacksonUtil.toJson(new BaseResponse(body));
        }
        return new BaseResponse(body);
    }
}
