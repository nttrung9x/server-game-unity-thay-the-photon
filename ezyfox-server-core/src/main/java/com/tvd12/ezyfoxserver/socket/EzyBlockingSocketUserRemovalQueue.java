package com.tvd12.ezyfoxserver.socket;

import com.tvd12.ezyfox.util.EzyLoggable;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EzyBlockingSocketUserRemovalQueue
    extends EzyLoggable
    implements EzySocketUserRemovalQueue {

    private final BlockingQueue<EzySocketUserRemoval> queue;

    public EzyBlockingSocketUserRemovalQueue() {
        this.queue = new LinkedBlockingQueue<EzySocketUserRemoval>();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean add(EzySocketUserRemoval removal) {
        return queue.offer(removal);
    }

    @Override
    public void remove(EzySocketUserRemoval removal) {
        //noinspection ResultOfMethodCallIgnored
        queue.remove(removal);
    }

    @Override
    public EzySocketUserRemoval take() throws InterruptedException {
        return queue.take();
    }
}
