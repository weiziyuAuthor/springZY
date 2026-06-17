package cn.hello.demos.event;

import org.springframework.context.ApplicationEvent;

public class MsgEvent extends ApplicationEvent {
    private String msg;

    public MsgEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}