package com.tvd12.ezyfoxserver.command.impl;

import com.tvd12.ezyfox.io.EzyStrings;
import com.tvd12.ezyfox.util.EzyExceptionHandlers;
import com.tvd12.ezyfox.util.EzyExceptionHandlersFetcher;
import com.tvd12.ezyfoxserver.EzyPlugin;
import com.tvd12.ezyfoxserver.command.EzyAbstractCommand;
import com.tvd12.ezyfoxserver.command.EzyHandleException;

public class EzyPluginHandleExceptionImpl
    extends EzyAbstractCommand
    implements EzyHandleException {

    private final EzyPlugin plugin;
    private final EzyExceptionHandlersFetcher fetcher;

    public EzyPluginHandleExceptionImpl(EzyPlugin plugin) {
        this.plugin = plugin;
        this.fetcher = (EzyExceptionHandlersFetcher) plugin;
    }

    @Override
    public void handle(Thread thread, Throwable throwable) {
        String pluginName = plugin.getSetting().getName();
        EzyExceptionHandlers handlers = fetcher.getExceptionHandlers();
        if (handlers.isEmpty()) {
            logger.info("plugin: {} has no handler for exception:", pluginName, throwable);
        } else {
            try {
                handlers.handleException(thread, throwable);
            } catch (Exception e) {
                logger.warn(
                    "handle exception: {} on plugin: {} error",
                    EzyStrings.exceptionToSimpleString(throwable),
                    pluginName,
                    e
                );
            }
        }
    }
}
