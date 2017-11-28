package com.tvd12.ezyfoxserver.testing;

import java.util.concurrent.ConcurrentHashMap;

import com.tvd12.ezyfoxserver.EzySimpleServer;
import com.tvd12.ezyfoxserver.command.EzyCommand;
import com.tvd12.ezyfoxserver.command.EzyRunWorker;
import com.tvd12.ezyfoxserver.entity.EzyArray;
import com.tvd12.ezyfoxserver.entity.EzyObject;
import com.tvd12.ezyfoxserver.reflect.EzyClasses;
import com.tvd12.ezyfoxserver.setting.EzySimpleAppSetting;
import com.tvd12.ezyfoxserver.util.EzyLoggable;
import com.tvd12.test.base.BaseTest;

public class Performance3Test extends BaseTest {

	@SuppressWarnings("unused")
	public static void main(String args[]) {
		System.out.println("\n========= begin =========\n");
		long start = System.currentTimeMillis();
		ConcurrentHashMap<Object, Object> strs = new ConcurrentHashMap<>();
		strs.put(EzyCommand.class, EzyObject.class);
		strs.put(EzyRunWorker.class, EzyObject.class);
		strs.put(EzyObject.class, EzyObject.class);
		strs.put(EzyArray.class, EzyObject.class);
		strs.put(EzySimpleAppSetting.class, EzyObject.class);
		strs.put(EzySimpleServer.class, EzyObject.class);
		strs.put(EzyLoggable.class, EzyObject.class);
		strs.put(EzyClasses.class, EzyObject.class);
		for(int i = 0 ; i < 10000000 ; i++) {
			Object abc = strs.get(EzyLoggable.class);
		}
		long end = System.currentTimeMillis();
		System.out.println("time = " + (end - start));
		System.out.println("\n========= end =========\n");
	}
	
}
