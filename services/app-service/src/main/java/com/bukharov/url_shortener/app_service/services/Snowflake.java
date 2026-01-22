package com.bukharov.url_shortener.app_service.services;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Utility class for generating unique Snowflake IDs.
 * Snowflake ID is a 64-bit unique identifier composed of:
 * - 41 bits for timestamp (milliseconds since epoch)
 * - 10 bits for node ID
 * - 12 bits for sequence number
 * This implementation is thread-safe and generates IDs based on the current time.
 */
public class Snowflake {

    // Constants for bit allocation
    private static final long EPOCH = 1288834974657L; // Twitter Snowflake epoch (2010-11-04)
    private static final int TIMESTAMP_BITS = 41;
    private static final int NODE_BITS = 10;
    private static final int SEQUENCE_BITS = 12;

    // Bit shifts
    private static final long MAX_NODE_ID = (1L << NODE_BITS) - 1;
    private static final long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1;

    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + NODE_BITS;
    private static final long NODE_SHIFT = SEQUENCE_BITS;

    // Instance variables
    private final long nodeId;
    private final AtomicLong sequence = new AtomicLong(0);
    private volatile long lastTimestamp = -1L;

    /**
     * Constructor for Snowflake ID generator.
     * @param nodeId The node ID (0 to 1023)
     * @throws IllegalArgumentException if ID is out of range
     */
    public Snowflake(long nodeId) {
        if (nodeId < 0 || nodeId > MAX_NODE_ID) {
            throw new IllegalArgumentException("Node ID must be between 0 and " + MAX_NODE_ID);
        }
        this.nodeId = nodeId;
    }

    /**
     * Generates the next unique Snowflake ID.
     * @return A 64-bit unique ID
     * @throws RuntimeException if clock moves backwards
     */
    public synchronized long nextId() {
        long timestamp = currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate ID for " + (lastTimestamp - timestamp) + " milliseconds");
        }

        if (timestamp == lastTimestamp) {
            // Increment sequence within the same millisecond
            long seq = sequence.incrementAndGet();
            if (seq > MAX_SEQUENCE) {
                // Wait for next millisecond
                timestamp = waitNextMillis(lastTimestamp);
                sequence.set(0);
            }
        } else {
            // Reset sequence for new timestamp
            sequence.set(0);
        }

        lastTimestamp = timestamp;

        // Build the ID
        return ((timestamp - EPOCH) << TIMESTAMP_SHIFT) |
               (nodeId << NODE_SHIFT) |
               sequence.get();
    }

    /**
     * Gets the current time in milliseconds.
     * @return Current time
     */
    private long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * Waits until the next millisecond if sequence overflows.
     * @param lastTimestamp The last timestamp
     * @return The next timestamp
     */
    private long waitNextMillis(long lastTimestamp) {
        long timestamp = currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTimeMillis();
        }
        return timestamp;
    }
}