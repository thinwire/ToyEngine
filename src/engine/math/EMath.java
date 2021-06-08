package engine.math;

/**
 * Math routines. EMath stands for "Extended Math", the name is chosen so
 * that a clear difference can be drawn between this class and java.lang.Math
 */
public class EMath {

    /**
     * Make sure that a value is between a minimum and a maximum value.
     * 
     * @param value value to modify
     * @param min   the minimum limit - the result will be either this, or greater
     * @param max   the maximum limit - the result will be either this, or smaller
     * @return the modified value
     */
    public static final double clamp(double value, double min, double max) {
        return value < min ? min : value > max ? max : value;
    }

    /**
     * Move a value closer to zero by some amount.
     * 
     * @param value  value to modify
     * @param amount how much closer to 0 the value should be. If this is negative,
     *               the resulting value will be further away from 0 (i.e. greater
     *               if the value was positive, smaller if it was negative)
     * @return the modified value
     */
    public static final double reduce(final double value, final double amount) {
        double t = Math.max(Math.abs(value) - amount, 0);
        return Math.signum(value) * t;
    }

    /**
     * Get a random value between a minimum and a maximum limit
     * 
     * @param min the minimum limit - the value will not be smaller than this
     * @param max the maximum limit - the value will not be greater than this
     * @return a (pseudo) random value between min and max
     */
    public static final double rand(final double min, final double max) {
        final double range = max - min;
        return Math.random() * range + min;
    }

    /**
     * Wrap a value between a minimum and a maximum. If the value is decreased past
     * the minimum limit, it wraps back to the maximum; if it is increased past the
     * maximum limit, it wraps back to the minimum.
     * 
     * @param value any number value
     * @param min   the minimum limit - the value can not go lower than this without
     *              wrapping around
     * @param max   the maximum limit - the value can not go higher than this
     *              without wrapping around
     * @return the confined value
     */
    public static final double wrap(final double value, final double min, final double max) {
        if (min == max) {
            return min;
        }

        // ...you're not expected to understand this

        final double v0 = value - min;
        final double d = max - min;
        final double v1 = v0 - ((int) (v0 / d) * d);
        return min + v1 + (v1 < .0 ? d : .0);
    }

}
