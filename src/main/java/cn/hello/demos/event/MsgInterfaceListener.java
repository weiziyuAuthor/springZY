package cn.hello.demos.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MsgInterfaceListener implements ApplicationListener<MsgEvent> {

    @Async
    @Override
    public void onApplicationEvent(MsgEvent event) {
        log.info("【接口监听器】线程:{}，消息:{}", Thread.currentThread().getName(), event.getMsg());
    }
}