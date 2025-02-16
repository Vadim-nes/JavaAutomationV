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
        Delivery delivery = new Delivery(30, Dimensions.LARGE, Fragile.YES, Traffic.MEDIUM);

        Assertions.assertEquals(840, delivery.calculateDelivery());
    }

    @Test
    @DisplayName("> 30 Distance when fragile package")
    @Tag("Negative")
    void fragile31kmTest() {

        Delivery delivery = new Delivery(31, Dimensions.SMALL, Fragile.YES, Traffic.HIGH);

        Exception ex = assertThrows(ArithmeticException.class, () -> delivery.calculateDelivery(),
                "It can't be negative value or 0");
        Assertions.assertEquals("Fragile package can't be delivered so far > 30 km", ex.getMessage());
    }

    @Test
    @DisplayName("Delivery price calculation - Dimensions.SMALL, Fragile.YES, Traffic.STANDARD")
    @Tag("Regression")
    void price2Test() {

        Delivery delivery = new Delivery(10, Dimensions.SMALL, Fragile.YES, Traffic.STANDARD);

        Assertions.assertEquals(500, delivery.calculateDelivery());
    }

    @Test
    @DisplayName("Delivery price calculation - Dimensions.SMALL, Fragile.NO, Traffic.MEDIUM")
    @Tag("Regression")
    void price3Test() {

        Delivery delivery = new Delivery(2, Dimensions.SMALL, Fragile.NO, Traffic.MEDIUM);

        Assertions.assertEquals(400, delivery.calculateDelivery());
    }

    @Test
    @DisplayName("Delivery price calculation - Dimensions.LARGE, Fragile.NO, Traffic.HIGHEST")
    @Tag("Regression")
    void price4Test() {

        Delivery delivery = new Delivery(10, Dimensions.LARGE, Fragile.NO, Traffic.HIGHEST);

        Assertions.assertEquals(480, delivery.calculateDelivery());
    }

    @Test
    @DisplayName("Delivery price calculation - Dimensions.LARGE, Fragile.NO, Traffic.STANDARD")
    @Tag("Regression")
    void price5Test() {

        Delivery delivery = new Delivery(35, Dimensions.LARGE, Fragile.NO, Traffic.STANDARD);

        Assertions.assertEquals(500, delivery.calculateDelivery());
    }

    @Test
    @DisplayName("Delivery price calculation - Dimensions.SMALL, Fragile.NO, Traffic.HIGHEST")
    @Tag("Regression")
    void price6Test() {

        Delivery delivery = new Delivery(11, Dimensions.SMALL, Fragile.NO, Traffic.HIGHEST);

        Assertions.assertEquals(480, delivery.calculateDelivery());
    }

    @Test
    @DisplayName("Delivery price calculation - Dimensions.LARGE, Fragile.YES, Traffic.HIGH")
    @Tag("Regression")
    void price7Test() {

        Delivery delivery = new Delivery(25, Dimensions.LARGE, Fragile.YES, Traffic.HIGH);

        double actual = Math.round(delivery.calculateDelivery());

        Assertions.assertEquals(980, actual);
    }

    @ParameterizedTest(name = "Delivery price calculation - Dimensions.LARGE, Fragile.NO, Traffic.HIGH, {index}, where distance =: {0}")
    @ValueSource(doubles = {0.1, 1.9})
    @Tag("Regression")
    void price8Test(double d) {
        Delivery delivery = new Delivery(d, Dimensions.LARGE, Fragile.NO, Traffic.HIGH);

        Assertions.assertEquals(400, delivery.calculateDelivery());
    }

    @Test
    @DisplayName("The negative Distance Test")
    @Tag("Negative")
    void negativeDistanceTest() {

        assertThrows(ArithmeticException.class, () -> new Delivery(-1, Dimensions.LARGE, Fragile.NO, Traffic.HIGH),
                "It can't be negative value");
    }
}
