package org;

public class Delivery {

    private double distance;

    public Delivery(double distance) {

        if (distance <= 0) {
            throw new ArithmeticException("It can't be negative value or 0");
        }

        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public static double calculateDelivery(Delivery delivery, Dimensions dimensions, Fragile fragile, Traffic traffic) {

        int basicPayment = 400;
        int distanceExtraTax;
        int dimensionsExtraTax = 0;
        int fragileExtraTax = 0;
        double trafficExtraTimer = 0;

        if (delivery.getDistance() > 30) {
            distanceExtraTax = 300;
        } else if (delivery.getDistance() > 10) {
            distanceExtraTax = 200;
        } else if (delivery.getDistance() > 2) {
            distanceExtraTax = 100;
        } else {
            distanceExtraTax = 50;
        }

        if (dimensions == Dimensions.SMALL) {
            dimensionsExtraTax = 100;
        } else if (dimensions == Dimensions.LARGE) {
            dimensionsExtraTax = 200;
        }

        if (fragile == Fragile.YES && delivery.getDistance() <= 30) {
            fragileExtraTax = 300;
        } else if (fragile == Fragile.YES && delivery.getDistance() > 30) {
            throw new ArithmeticException("Fragile package can't be delivered so far > 30 km");
        } else if (fragile == Fragile.NO) {
            fragileExtraTax = 0;
        }

        if (traffic == Traffic.STANDARD) {
            trafficExtraTimer = 1;
        } else if (traffic == Traffic.MEDIUM) {
            trafficExtraTimer = 1.2;
        } else if (traffic == Traffic.HIGH) {
            trafficExtraTimer = 1.4;
        } else if (traffic == Traffic.HIGHEST) {
        trafficExtraTimer = 1.6;
        }

        double totalPayment = (distanceExtraTax + dimensionsExtraTax + fragileExtraTax) * trafficExtraTimer;

        if (totalPayment > 400) {
            return totalPayment;

        } else return 400;
    }
}