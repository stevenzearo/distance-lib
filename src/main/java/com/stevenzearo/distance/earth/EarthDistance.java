package com.stevenzearo.distance.earth;

import com.stevenzearo.distance.Distance;
import com.stevenzearo.distance.Location;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author Steve Zou
 * ref1: https://en.wikipedia.org/wiki/Geographical_distance
 * ref2: https://en.wikipedia.org/wiki/Haversine_formula
 */
public final class EarthDistance implements Distance {
    private static final MathContext MATH_CONTEXT = MathContext.DECIMAL128;
    private static final double EARTH_RADIUS = 6371.230d * 1000;
    private static final double EQUATORIAL_RADIUS = 6378.1370d * 1000;
    private static final double POLAR_RADIUS = 6356.7523d * 1000;
    private static final double FLATTENING = 1 / 298.257223563;

    @Override
    public double calculate(Location first, Location second) {
        return calculateShortDistance(first, second);
    }

    private double calculateShortDistance(Location first, Location second) {
        var latitude1 = degreesToRadians(first.getLatitude());
        var longitude1 = degreesToRadians(first.getLongitude());
        var latitude2 = degreesToRadians(second.getLatitude());
        var longitude2 = degreesToRadians(second.getLongitude());
        return calculateDistance(latitude1, latitude2, latitude1, longitude1, latitude2, longitude2);
    }

    private double calculateDistance(double b1, double b2, double latitude1, double longitude1, double latitude2, double longitude2) {
        var absDiffLatitude = latitude1 - latitude2;
        var absDiffLongitude = longitude1 - longitude2;

        var h = Math.cos(latitude1) * Math.cos(latitude2) * doSin(absDiffLongitude) + doSin(absDiffLatitude);
        double o = 2.0 * Math.asin(Math.sqrt(h));

        var p = (b1 + b2) / 2;
        var q = (b2 - b1) / 2;

        var m = (1 - Math.cos(2 * p)) / 2;
        var n = (1 + Math.cos(2 * q)) / 2;
        var l = (1 + Math.cos(o)) / 2;
        var x = (o - Math.sin(o)) * m * n / l;

        var m2 = (1 + Math.cos(2 * p)) / 2;
        var n2 = (1 - Math.cos(2 * q)) / 2;
        var l2 = (1 - Math.cos(o)) / 2;
        var y = (o + Math.sin(o)) * m2 * n2 / l2;
        return EQUATORIAL_RADIUS * (o - FLATTENING / 2 * (x + y));
    }

    private static double degreesToRadians(double degrees) {
        return new BigDecimal(String.valueOf(degrees)).multiply(new BigDecimal(String.valueOf(Math.PI)), MATH_CONTEXT).divide(new BigDecimal("180"), MATH_CONTEXT).doubleValue();
    }

    private static double doSin(double theta) {
        return (1 - Math.cos(theta)) / 2;
    }
}

