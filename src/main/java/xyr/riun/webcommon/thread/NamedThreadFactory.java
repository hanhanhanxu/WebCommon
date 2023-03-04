package xyr.riun.webcommon.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: HanXu
 * on 2021/1/5
 * Class description: 自定义线程工厂：命名工厂
 * 完全copy java.util.concurrent.Executors.DefaultThreadFactory, 只不过添加了自定义线程池名称的功能
 */
public class NamedThreadFactory implements ThreadFactory {

    /**
     * 池数量，一个池子即为1
     */
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    /**
     * 新线程实例化到的线程组
     */
    private final ThreadGroup group;
    /**
     * 线程数量，一个线程即为1
     */
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    /**
     * 名称前缀
     */
    public final String namePrefix;


    NamedThreadFactory(String name) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        if (null == name || "".equals(name.trim())) {
            name = "pool";
        }
        namePrefix = name + "-" +
                poolNumber.getAndIncrement() +
                "-thread-";
    }


    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
