package com.w3engineers.jitpackbottomnav.fragment.history;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class SerialExecutorService extends AbstractExecutorService {
    private static final int RUNNING = 0;
    private static final int SHUTDOWN = 1;
    private static final int TERMINATED = 2;

    final Lock lock = new ReentrantLock();
    final Condition termination = lock.newCondition();

    final Executor underlyingExecutor;
    volatile ArrayDeque<Runnable> commands;

    volatile int state = RUNNING;
    Runnable currentCommand;

    /*
     * The runnable we submit into the underlyingExecutor, we avoid creating
     * unnecessary runnables since only one will be submitted at a time
     */

    private final Runnable innerRunnable = new Runnable() {

        public void run() {
            /*
             * If state is TERMINATED, skip execution
             */
            if (state == TERMINATED) {
                return;
            }else {
                System.out.println("Inner TERMINATED ="+commands.size());
            }

            try {
                currentCommand.run();
            } finally {
                System.out.println("Inner finally");
                lock.lock();
                try {
                    currentCommand = commands.pollFirst();
                    if (currentCommand != null && state < TERMINATED) {
                        try {
                            underlyingExecutor.execute(this);
                            System.out.println("innerRunnable execute");
                        } catch (Exception e) {
                            //The underlying executor may have been shutdown.
                            //We would need a kind of handler for this.
                            //Terminate this executor and clean pending command for now
                            currentCommand = null;
                            commands.clear();
                            transitionToTerminated();
                        }
                    } else {
                        if (state == SHUTDOWN) {
                            transitionToTerminated();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    };

    /**
     * Creates a new {@link SerialExecutorService}. <p>
     *
     * @param underlyingExecutor
     *            The underlying executor to use for executing the tasks
     *            submitted into this executor.
     */
    public SerialExecutorService(Executor underlyingExecutor) {
        this.underlyingExecutor = underlyingExecutor;
        this.commands = new ArrayDeque<Runnable>();
    }

    public void execute(Runnable command) {
        System.out.println("called execute()");
        lock.lock();
        try {
            if (state != RUNNING) {
                //throw new IllegalStateException("Executor has been shutdown");
                return;
            }
            if (currentCommand == null && commands.isEmpty()) {
                currentCommand = command;
                underlyingExecutor.execute(innerRunnable);
                System.out.println("Execute command");
            } else {
                commands.add(command);
                System.out.println("Add command ="+commands.size());
            }
        } finally {
            lock.unlock();
        }
    }

    public void shutdown() {
        System.out.println("called shutdown()");
        lock.lock();
        try {
            if (state == RUNNING) {
                if (currentCommand == null && commands.isEmpty()) {
                    transitionToTerminated();
                } else {
                    state = SHUTDOWN;
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public List<Runnable> shutdownNow() {
        System.out.println("called shutdownNow()");
        lock.lock();
        try {
            if (state < TERMINATED) {
                transitionToTerminated();
                ArrayList<Runnable> result = new ArrayList<Runnable>(commands);
                commands.clear();
                return result;
            }
            return Collections.<Runnable>emptyList();
        } finally {
            lock.unlock();
        }
    }

    public boolean isShutdown() {
        System.out.println("called isShutdown()");
        return state > RUNNING;
    }

    public boolean isTerminated() {
        System.out.println("called isTerminated()");
        return state == TERMINATED;
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        System.out.println("called awaitTermination()");
        long nanos = unit.toNanos(timeout);
        lock.lock();
        try {
            while (!isTerminated() && nanos > 0) {
                nanos = termination.awaitNanos(nanos);
            }
        } finally {
            lock.unlock();
        }
        return isTerminated();
    }

    /*
     * Lock must me held when calling this method
     */
    private void transitionToTerminated() {
        System.out.println("Execute transitionToTerminated()");
        state = TERMINATED;
        termination.signalAll();
    }
}
