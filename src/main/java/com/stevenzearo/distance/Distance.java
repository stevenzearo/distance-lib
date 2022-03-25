package com.stevenzearo.distance;

/**
 * @author Steve Zou
 */
public interface Distance {
    /**
     *
     * @param first: first location
     * @param second: second location
     * @return distance in meter of two location
     */
    double calculate(Location first, Location second);
}
