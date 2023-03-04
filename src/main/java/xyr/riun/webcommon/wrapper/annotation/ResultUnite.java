package xyr.riun.webcommon.wrapper.annotation;

import java.lang.annotation.*;

/**
 * @author: HanXu
 * on 2021/6/11
 * Class description: 统一返回结果注解
 */
@Target({ ElementType.METHOD }) //作用范围
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResultUnite {
}
