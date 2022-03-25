package com.stevenzearo.distance.earth;

import com.stevenzearo.distance.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Steve Zou
 */
class EarthDistanceTest {

    @Test
    void calculate() {
        calculate(24.680736545793717, 118.21702298717167, 24.68060374068191, 118.21797300542514, 99.57);
        calculate(24.68076974704958, 118.21694990884451, 24.680006115928204, 118.22699817883273, 1.01 * 1000);
        calculate(24.67194181632565, 118.19875833252237, 24.72661475042807, 118.27690955068898, 10.01 * 1000);
        calculate(26.953567437699018, 109.66963859192329, 27.51589904363768, 110.45668612383282, 100.52 * 1000);
        calculate(26.93400000060781, 109.68030185049767, 34.105635716456206, 116.87616499938481, 1049.65 * 1000);
    }

    void calculate(double latitude1, double longitude1, double latitude2, double longitude2, double googleDistance) {
        Location location = new Location(longitude1, latitude1);
        Location location1 = new Location(longitude2, latitude2);

        EarthDistance earthDistance = new EarthDistance();
        double calculateV2 = earthDistance.calculate(location, location1);
        Assertions.assertTrue(Math.abs(googleDistance - calculateV2) < googleDistance * 0.05); // accuracy should higher than 95%
    }
}