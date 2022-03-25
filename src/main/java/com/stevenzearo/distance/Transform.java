package com.stevenzearo.distance;

/**
 * @author Steve Zou
 */
public final class Transform {
    public static final double METERS_PER_MILE = 1.609344;

    public static double meterToMile(double meter) {
        return meter / METERS_PER_MILE;
    }
}
