import org.Delivery;
import org.Dimensions;
import org.Fragile;
import org.Traffic;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class DeliveryTest {

    @Test
    @DisplayName("Delivery price calculation - Dimensions.LARGE, Fragile.YES, Traffic.MEDIUM")
    @Tags({@Tag("Smoke"), @Tag("Regression")})
    void priceTest() {
        Delivery delivery = new Delivery(30);

        Assertions.assertEquals(840,Delivery.calculateDelivery(delivery, Dimensions.LARGE, Fragile.YES, Traffic.MEDIUM));
    }

    @Test
    @DisplayName("> 30 Distance when fragile package")
    @Tag("Negative")
    void fragile31kmTest() {

        Delivery delivery = new Delivery(31);

        Exception ex = assertThrows(ArithmeticException.class, () -> Delivery.calculateDelivery(delivery, Dimensions.SMALL, Fragile.YES, Traffic.HIGH),
                "It can't be negative value or 0");
        Assertions.assertEquals("Fragile package can't be delivered so far > 30 km",ex.getMessage());
    }

    @Test
    @DisplayName("Delivery price calculation - Dimensions.SMALL, Fragile.YES, Traffic.STANDARD")
    @Tag("Regression")
    void price2Test() {

        Delivery delivery = new Delivery(10);

        Assertions.assertEquals(500,Delivery.calculateDelivery(delivery, Dimensions.SMALL, Fragile.YES, Traffic.STANDARD));
    }

    @Test
    @DisplayName("Delivery price calculation - Dimensions.SMALL, Fragile.NO, Traffic.MEDIUM")
    @Tag("Regression")
    void price3Test() {

        Delivery delivery = new Delivery(2);

        Assertions.assertEquals(400,Delivery.calculateDelivery(delivery, Dimensions.SMALL, Fragile.NO, Traffic.MEDIUM));
    }

    @Test
    @DisplayName("Delivery price calculation - Dimensions.LARGE, Fragile.NO, Traffic.HIGHEST")
    @Tag("Regression")
    void price4Test() {

        Delivery delivery = new Delivery(10);

        Assertions.assertEquals(480,Delivery.calculateDelivery(delivery, Dimensions.LARGE, Fragile.NO, Traffic.HIGHEST));
    }

    @Test
    @DisplayName("Delivery price calculation - Dimensions.LARGE, Fragile.NO, Traffic.STANDARD")
    @Tag("Regression")
    void price5Test() {

        Delivery delivery = new Delivery(35);

        Assertions.assertEquals(500,Delivery.calculateDelivery(delivery, Dimensions.LARGE, Fragile.NO, Traffic.STANDARD));
    }

    @Test
    @DisplayName("Delivery price calculation - Dimensions.SMALL, Fragile.NO, Traffic.HIGHEST")
    @Tag("Regression")
    void price6Test() {

        Delivery delivery = new Delivery(11);

        Assertions.assertEquals(480,Delivery.calculateDelivery(delivery, Dimensions.SMALL, Fragile.NO, Traffic.HIGHEST));
    }

    @Test
    @DisplayName("Delivery price calculation - Dimensions.LARGE, Fragile.YES, Traffic.HIGH")
    @Tag("Regression")
    void price7Test() {

        Delivery delivery = new Delivery(25);

        double actual = Math.round(Delivery.calculateDelivery(delivery, Dimensions.LARGE, Fragile.YES, Traffic.HIGH));

        Assertions.assertEquals(980,actual);
    }

    @ParameterizedTest(name = "Delivery price calculation - Dimensions.LARGE, Fragile.NO, Traffic.HIGH, {index}, where distance =: {0}")
    @ValueSource(doubles = {0.1,1.9})
    @Tag("Regression")
    void price8Test(double d) {
        Delivery delivery = new Delivery(d);

        Assertions.assertEquals(400,Delivery.calculateDelivery(delivery, Dimensions.LARGE, Fragile.NO, Traffic.HIGH));
    }

    @Test
    @DisplayName("The negative Distance Test")
    @Tag("Negative")
    void negativeDistanceTest() {

        assertThrows(ArithmeticException.class, () -> new Delivery(-1),
                "It can't be negative value");
    }



}
