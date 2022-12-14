package com.tvd12.ezyfoxserver.testing;

import com.tvd12.ezyfoxserver.EzySimpleZone;
import com.tvd12.ezyfoxserver.setting.EzySimpleZoneSetting;
import com.tvd12.test.base.BaseTest;
import org.testng.annotations.Test;

public class EzySimpleZoneTest extends BaseTest {

    @Test
    public void test() {
        EzySimpleZone zone = new EzySimpleZone();
        EzySimpleZoneSetting setting = new EzySimpleZoneSetting();
        zone.setSetting(setting);
        //noinspection EqualsWithItself
        assert zone.equals(zone);
        assert !zone.equals(new EzySimpleZone());
    }
}
