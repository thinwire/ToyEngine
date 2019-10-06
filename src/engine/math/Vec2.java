package engine.math;

/**
 * Immutable 2D vector class. While having mutable vectors results in more
 * efficient code in a professional setting, immutable vectors help eliminate
 * math mistakes for beginners.
 */
public class Vec2 {

    public final double x;
    public final double y;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculate the length of this vector
     */
    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Calculate the squared length of this vector. This is faster than calculating
     * the length, since it avoid a square root calculation. The squared length can
     * be used for e.g. comparisons with the squared length of another vector, to
     * find out which one is shorter/longer.
     */
    public double length2() {
        return x * x + y * y;
    }

    /**
     * Vector addition. Add the components of this vector and the other vector
     * together and return the result as a new vector.
     * 
     * @param v another Vec2 object
     * @return a new Vec2 object
     */
    public Vec2 add(final Vec2 v) {
        return new Vec2(x + v.x, y + v.y);
    }

    /**
     * Vector addition. Add X and Y components to this vector's X and Y components
     * and return the result as a new vector.
     *
     * @param x X component value
     * @param y Y component value
     * @return a new Vec2 object
     */
    public Vec2 add(double x, double y) {
    	return new Vec2(this.x + x, this.y + y);
    }

    /**
     * Vector subtraction. Subtract the components of another vector from the
     * components of this vector and return the result as a new vector.
     * 
     * @param v another Vec2 object
     * @return a new Vec2 object
     */
    public Vec2 sub(final Vec2 v) {
        return new Vec2(x - v.x, y - v.y);
    }
    
    /**
     * Vector subtraction. Subtract x and y from this vector's X and Y components
     * and return the result as a new vector.
     * 
     * @param x X component value
     * @param y Y component value
     * @return a new Vec2 object
     */
    public Vec2 sub(double x, double y) {
    	return new Vec2(this.x + x, this.y + y);
    }

    /**
     * Vector scalar multiplication. Multiply the component values of this vector
     * with a scalar and the return the result as a new vector.
     * 
     * @param s a scalar value
     * @return a new Vec2 object
     */
    public Vec2 mul(double s) {
        return new Vec2(x * s, y * s);
    }

    /**
     * Vector multiplication. Multiply the components of another vector by the
     * components of this vector and return the result as a new vector.
     * 
     * @param s another Vec2 object
     * @return a new Vec2 object
     */
    public Vec2 mul(final Vec2 v) {
        return new Vec2(x * v.x, y * v.y);
    }

    /**
     * Calculate the dot product between this vector and another vector.
     * 
     * @param v another Vec2 object
     * @return the dot product, or "the length of the shadow cast by this vector in
     *         the direction of the other vector"
     */
    public double dot(final Vec2 v) {
        return x * v.x + y * v.y;
    }

    /**
     * Calculate a normalized version of this vector and return the result as a new
     * vector. The new vector will retain the same direction as this, but will have
     * a length of 1.0. In the case that this vector has a length of zero, a vector
     * with the components set to 0 will be returned.
     * 
     * @return a new Vec2 object with the length of 1.0, or 0.0 if this vector has
     *         both components set to 0.
     */
    public Vec2 normalize() {
        double len = length();
        if (len == 0.0) {
            return new Vec2(0, 0);
        }
        return new Vec2(x / len, y / len);
    }

    /**
     * Calculate the reflection of this vector off a s surface indicated by a
     * surface normal. This surface normal is assumed to be normalized.
     * 
     * @param normal a Vec2 object indicating the "outward" direction of the surface
     *               this vector is to be reflected off
     * @return a new Vec2 object
     */
    public Vec2 reflect(final Vec2 normal) {
        return this.sub(normal.mul(normal.dot(this) * 2.0));
    }

    /**
     * Overridden toString method for convenience.
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
