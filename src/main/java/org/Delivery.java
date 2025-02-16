package org;

public class Delivery {

    private double distance;
    private Dimensions dimensions;
    private Fragile fragile;
    private Traffic traffic;

    public Delivery(double distance, Dimensions dimensions, Fragile fragile, Traffic traffic) {

        if (distance <= 0) {
            throw new ArithmeticException("It can't be negative value or 0");
        }

        this.distance = distance;
        this.dimensions = dimensions;
        this.fragile = fragile;
        this.traffic = traffic;

    }

    public double calculateDelivery() {

        int basicPayment = 400;
        int distanceExtraTax;
        int dimensionsExtraTax = 0;
        int fragileExtraTax = 0;
        double trafficExtraTimer = 0;

        if (this.distance > 30) {
            distanceExtraTax = 300;
        } else if (this.distance > 10) {
            distanceExtraTax = 200;
        } else if (this.distance > 2) {
            distanceExtraTax = 100;
        } else {
            distanceExtraTax = 50;
        }

        if (this.dimensions == Dimensions.SMALL) {
            dimensionsExtraTax = 100;
        } else if (this.dimensions == Dimensions.LARGE) {
            dimensionsExtraTax = 200;
        }

        if (this.fragile == Fragile.YES && this.distance <= 30) {
            fragileExtraTax = 300;
        } else if (fragile == Fragile.YES && this.distance > 30) {
            throw new ArithmeticException("Fragile package can't be delivered so far > 30 km");
        } else if (fragile == Fragile.NO) {
            fragileExtraTax = 0;
        }

        if (this.traffic == Traffic.STANDARD) {
            trafficExtraTimer = 1;
        } else if (this.traffic == Traffic.MEDIUM) {
            trafficExtraTimer = 1.2;
        } else if (this.traffic == Traffic.HIGH) {
            trafficExtraTimer = 1.4;
        } else if (this.traffic == Traffic.HIGHEST) {
        trafficExtraTimer = 1.6;
        }

        double totalPayment = (distanceExtraTax + dimensionsExtraTax + fragileExtraTax) * trafficExtraTimer;

        if (totalPayment > 400) {
            return totalPayment;

        } else return 400;
    }
}