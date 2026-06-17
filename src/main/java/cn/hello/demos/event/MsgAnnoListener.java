package cn.hello.demos.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MsgAnnoListener {

    // 同步监听
    @EventListener
    public void syncHandle(MsgEvent event) {
        log.info("【注解同步监听】线程:{}，消息:{}", Thread.currentThread().getName(), event.getMsg());
    }

    // 异步监听
    @Async
    @EventListener
    public void asyncHandle(MsgEvent event) {
        log.info("【注解异步监听】线程:{}，消息:{}", Thread.currentThread().getName(), event.getMsg());
    }

    // condition 条件过滤演示
    @EventListener(condition = "#event.msg.contains(\"vip\")")
    public void vipHandle(MsgEvent event) {
        log.info("【注解条件监听】只处理vip消息:{}, 线程:{}",
                event.getMsg(), Thread.currentThread().getName());
    }
}