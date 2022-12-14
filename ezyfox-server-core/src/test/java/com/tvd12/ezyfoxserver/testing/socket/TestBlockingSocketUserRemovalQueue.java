package com.tvd12.ezyfoxserver.testing.socket;

import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyfoxserver.socket.EzySocketUserRemoval;
import com.tvd12.ezyfoxserver.socket.EzySocketUserRemovalQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestBlockingSocketUserRemovalQueue
    extends EzyLoggable
    implements EzySocketUserRemovalQueue {

    private final BlockingQueue<EzySocketUserRemoval> queue;

    public TestBlockingSocketUserRemovalQueue() {
        this.queue = new LinkedBlockingQueue<>();
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

