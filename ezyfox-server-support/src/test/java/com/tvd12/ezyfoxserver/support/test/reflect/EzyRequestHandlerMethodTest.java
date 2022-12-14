package com.tvd12.ezyfoxserver.support.test.reflect;

import com.tvd12.ezyfox.reflect.EzyMethod;
import com.tvd12.ezyfoxserver.context.EzyContext;
import com.tvd12.ezyfoxserver.support.reflect.EzyRequestHandlerMethod;
import com.tvd12.ezyfoxserver.support.test.controller.app.AppClientHelloRequestController;
import org.testng.annotations.Test;

public class EzyRequestHandlerMethodTest {

    @Test
    public void test() throws Exception {
        EzyMethod method = new EzyMethod(AppClientHelloRequestController.class
            .getDeclaredMethod("handleHello5", EzyContext.class));
        EzyRequestHandlerMethod handlerMethod = new EzyRequestHandlerMethod("c_hello5", method);
        assert handlerMethod.getMethod() == method;
        System.out.println(handlerMethod);
        //noinspection ConstantConditions
        assert handlerMethod.getParameterTypes().length >= 0;
    }
}
