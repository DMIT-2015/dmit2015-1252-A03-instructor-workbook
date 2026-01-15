package dmit2015.console;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    @Test
    void getArea_whenCircleIsCreatedWithSpecificRadius_shouldReturnsCorrectArea() {
        // Arrange and Act
        Circle circle1 = new Circle(5);
        // Assert
        assertEquals(78.54, circle1.getArea(), 0.005);
    }

    @Test
    void getDiameter_whenCircleIsCreatedWithSpecificRadius_shouldReturnsCorrectArea() {
        // Arrange and Act
        Circle circle1 = new Circle(5);
        // Assert
        assertEquals(10, circle1.getDiameter());
    }

    @Test
    void getPerimeter_whenCircleIsCreatedWithSpecificRadius_shouldReturnsCorrectArea() {
        // Arrange and Act
        Circle circle1 = new Circle(5);
        // Assert
        assertEquals(31.42, circle1.getPerimeter(), 0.005);
    }

}