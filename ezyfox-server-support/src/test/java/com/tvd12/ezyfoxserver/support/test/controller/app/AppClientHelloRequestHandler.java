package com.tvd12.ezyfoxserver.support.test.controller.app;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.core.annotation.EzyRequestListener;
import com.tvd12.ezyfoxserver.context.EzyAppContext;
import com.tvd12.ezyfoxserver.event.EzyUserSessionEvent;
import com.tvd12.ezyfoxserver.support.handler.EzyUserRequestHandler;
import com.tvd12.ezyfoxserver.support.test.controller.Hello;

@EzySingleton
@EzyRequestListener("hello")
public class AppClientHelloRequestHandler
    implements EzyUserRequestHandler<EzyAppContext, Hello> {

    @Override
    public void handle(EzyAppContext context, EzyUserSessionEvent event, Hello data) {
        System.out.println("hello: " + data.getWho());
    }
}
