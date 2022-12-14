package com.tvd12.ezyfoxserver.nio.testing;

import com.tvd12.ezyfoxserver.EzyStarter;
import com.tvd12.ezyfoxserver.config.EzyConfig;
import com.tvd12.ezyfoxserver.config.EzySimpleConfig;
import com.tvd12.ezyfoxserver.nio.EzyNioStarter;
import com.tvd12.ezyfoxserver.setting.EzySettings;
import com.tvd12.ezyfoxserver.setting.EzySimpleSettings;
import com.tvd12.test.assertion.Asserts;
import com.tvd12.test.base.BaseTest;
import com.tvd12.test.reflect.MethodInvoker;
import org.testng.annotations.Test;

public class EzyNioStarterTest extends BaseTest {

    @Test
    public void test() throws Exception {
        EzyNioStarter starter = (EzyNioStarter) new ExEzyNioStarter.Builder()
            .configFile("test.properties")
            .build();
        MethodInvoker.create()
            .object(starter)
            .method("newServerBootstrapBuilder")
            .invoke();
        MethodInvoker.create()
            .object(starter)
            .method("newSessionManagerBuilder")
            .param(EzySettings.class, new EzySimpleSettings())
            .invoke();
    }

    @Test
    public void buildTest() {
        // given
        // when
        EzyNioStarter starter = (EzyNioStarter) EzyNioStarter.builder()
            .build();

        // then
        Asserts.assertNull(starter.getServerContext());
    }

    public static class ExEzyNioStarter extends EzyNioStarter {

        protected ExEzyNioStarter(Builder builder) {
            super(builder);
        }

        @Override
        protected EzyConfig readConfig(String configFile) {
            return new EzySimpleConfig();
        }

        @Override
        protected void setSystemProperties(EzyConfig config) {
        }

        public static class Builder extends EzyNioStarter.Builder {
            @Override
            public EzyStarter build() {
                return new ExEzyNioStarter(this);
            }
        }
    }
}
