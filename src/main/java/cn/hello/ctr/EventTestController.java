package cn.hello.ctr;

import cn.hello.demos.event.MsgEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventTestController {
    private final ApplicationEventPublisher publisher;

    @GetMapping("/send")
    public String send() {
        System.out.println("====主线程开始发布事件====" + Thread.currentThread().getName());
        // 普通消息
        publisher.publishEvent(new MsgEvent(this, "普通用户消息"));
        // vip消息（触发condition）
        publisher.publishEvent(new MsgEvent(this, "vip专属活动消息"));
        System.out.println("====主线程发布完成，直接返回====");
        return "发布成功";
    }
}