package xyr.riun.webcommon.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author HanXu
 * @Date 2023/3/3 9:35
 */
@Slf4j
@Service
public class TestService {

    @SneakyThrows
    @Async("asyncServiceExecutor")
    public void asyncDbLogDown() {
        log.info("耗时操作 start...");
        Thread.sleep(2000);
        log.info("耗时操作 end...");
    }

    public void test() {
        log.info("test service start...");
        log.info("test service end...");
    }
}
