package xyr.riun.webcommon.thread;

import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;

/**
 * @author: HanXu
 * on 2021/1/5
 * Class description: 线程池配置
 */
@Slf4j
@Configuration
@EnableAsync
public class ExecutorConfig {

    /**
     * 核心线程数
     */
    @Value("${spring.executor.core-pool-size:4}")
    private int corePoolSize;
    /**
     * 最大线程数
     */
    @Value("${spring.executor.max-pool-size:8}")
    private int maxPoolSize;
    /**
     * 空闲线程存活时间,秒
     */
    @Value("${spring.executor.keep-alive-seconds:120}")
    private long waitTime;
    /**
     * 阻塞队列容量
     */
    @Value("${spring.executor.queue-capacity:99999}")
    private int queueCapacity;
    /**
     * 拒绝策略：1(丢弃)，2(中止)，3(调用者执行,默认)，4(丢弃最老)
     */
    @Value("${spring.executor.reject-handler:3}")
    private int rejectHandler;
    /**
     * 名称前缀
     */
    @Value("${spring.executor.name-prefix:webCommon}")
    private String namePrefix;


    @Bean(name = "asyncServiceExecutor")
    public Executor asyncServiceExecutor() {
        log.info("start asyncServiceExecutor------------------------");
        //拒绝策略
        RejectedExecutionHandler rejectHandler = dealRejectHandler();
        //线程池初始化
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, waitTime, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(queueCapacity), new NamedThreadFactory(namePrefix), rejectHandler);
        //使用阿里的ttl线程池，池内线程复用时内部RequestId变量自动传递，实现全流程RequestId记录
        ExecutorService ttlExecutorService = TtlExecutors.getTtlExecutorService(executor);
        return ttlExecutorService;
    }


    /**
     * 拒绝策略转换
     * @return
     */
    private RejectedExecutionHandler dealRejectHandler() {
        RejectedExecutionHandler rejectHandler = null;
        if (1 == this.rejectHandler) {
            rejectHandler = new ThreadPoolExecutor.DiscardPolicy();
        } else if (2 == this.rejectHandler) {
            rejectHandler = new ThreadPoolExecutor.AbortPolicy();
        } else if (3 == this.rejectHandler) {
            rejectHandler = new ThreadPoolExecutor.CallerRunsPolicy();
        } else if (4 == this.rejectHandler) {
            rejectHandler = new ThreadPoolExecutor.DiscardOldestPolicy();
        } else {
            //默认
            rejectHandler = new ThreadPoolExecutor.CallerRunsPolicy();
        }
        return rejectHandler;
    }
}
