package xyr.riun.webcommon.model;

import java.io.File;

/**
 * @author: HanXu
 * on 2021/5/12
 * Class description: 全局常量
 * 变大小写快捷键：ctrl + shift + u
 */
public interface GlobalConstant {

    /**
     * 请求唯一id
     * 和logback-spring.xml中的RequestId必须是一个名字
     * 用于：1、日志记录请求id
     *      2、返回请求id
     */
    String REQUEST_ID = "RequestId";

    String separator = File.separator;

}
