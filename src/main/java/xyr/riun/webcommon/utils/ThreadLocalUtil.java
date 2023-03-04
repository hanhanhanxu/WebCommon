package xyr.riun.webcommon.utils;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @author: HanXu
 * on 2021/11/17
 * Class description: 线程变量工具类
 * 在每一个线程中储存一个变量，以记录当前线程生命流程中的动作
 */
public class ThreadLocalUtil {

//    private static final ThreadLocal<String> currentThreadLocal = ThreadLocal.withInitial(() -> new String());
    /**
     * 使用阿里的TransmittableThreadLocal
     */
    private static final TransmittableThreadLocal<String> currentThreadLocal = new TransmittableThreadLocal<>();


    /**
     * 获取值
     * @return 当前线程中存放的变量值
     */
    public static String getCurrentThreadVal() {
        return currentThreadLocal.get();
    }

    /**
     * set值
     * @param value 唯一
     */
    public static void putCurrentThreadVal(String value) {
        currentThreadLocal.set(value);
    }

    /**
     * 清空当前线程中的数据
     */
    public static void clear() {
        currentThreadLocal.remove();
    }
}
