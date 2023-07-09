package me.liycxc.utils;

/**
 * @author Liycxc
 */
public final class MSTimer {

    public long time = -1L;

    public boolean hasTimePassed(final long ms) {
        return System.currentTimeMillis() >= time + ms;
    }

    public long hasTimeLeft(final long ms) {
        return (ms + time) - System.currentTimeMillis();
    }

    public void reset() {
        time = System.currentTimeMillis();
    }
}

