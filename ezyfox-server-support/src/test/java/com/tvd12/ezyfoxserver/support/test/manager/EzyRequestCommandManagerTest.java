package com.tvd12.ezyfoxserver.support.test.manager;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tvd12.ezyfoxserver.support.manager.EzyRequestCommandManager;
import com.tvd12.test.assertion.Asserts;

public class EzyRequestCommandManagerTest {
    
    @Test
    public void test() {
        // given
        EzyRequestCommandManager sut = new EzyRequestCommandManager();
        sut.addCommand("a");
        sut.addPaymentCommand("d");
        sut.addManagementCommand("e");
        
        // when
        // then
        Asserts.assertTrue(sut.containsCommand("a"));
        Asserts.assertEquals(sut.getCommands(), Arrays.asList("a"), false);
        Assert.assertTrue(sut.isPaymentCommand("d"));
        Asserts.assertEquals(sut.getPaymentCommands(), Arrays.asList("d"), false);
        Assert.assertTrue(sut.isManagementCommand("e"));
        Asserts.assertEquals(sut.getManagementCommands(), Arrays.asList("e"), false);
        sut.destroy();
    }
}
