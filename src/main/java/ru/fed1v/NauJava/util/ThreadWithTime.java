package ru.fed1v.NauJava.util;

public class ThreadWithTime extends Thread {

    private long startTime;
    private long finishTime;

    private final Runnable runnable;

    private boolean hasExceptions;
    private Throwable throwable;

    public ThreadWithTime(String threadName, Runnable runnable) {
        super(threadName);
        this.runnable = runnable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public boolean getHasExceptions() {
        return hasExceptions;
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        hasExceptions = false;
        throwable = null;

        try {
            runnable.run();
        } catch (Throwable e) {
            hasExceptions = true;
            throwable = e;
        }

        finishTime = System.currentTimeMillis();
    }

    public long getWorkingTime() {
        return finishTime - startTime;
    }
}
