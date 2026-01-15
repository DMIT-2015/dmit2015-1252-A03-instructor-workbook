package dmit2015.console;

/**
 *  This class models a Circle shape.
 *
 * @author Sam Wu
 * @version 2026.01.14
 */
public class Circle implements Shape {
    private double radius;  // The radius of the circle

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must positive");
        }
        this.radius = radius;
    }

    /**
     * Create a new circle with a radius of 1.0
     */
    public Circle() {
        setRadius(1.0);
    }

    /**
     * Create a new circle with a specific radius
     * @param radius radius of circle
     */
    public Circle(double radius) {
//        this.radius = radius;
        setRadius(radius);
    }

    /**
     * Calculate the area of this circle
     * @return area of this circle
     */
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    /**
     * Calculate the perimeter of this circle
     * @return perimeter of this circle
     */
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    /**
     * Calculate the diameter of this circle
     * @return diameter of this circle
     */
    public double getDiameter() {
        return 2 * radius;
    }
}
