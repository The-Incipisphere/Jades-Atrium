package io.thedogofchaos.jadesatrium.util;

/**
 * A utility class that provides methods for color manipulation and conversion
 * between different color models such as RGB and HSB, as well as color interpolation.
 */
public final class ColorHelper {

    /**
     * Converts RGB values to an integer representation.
     *
     * @param r The red component (0-255).
     * @param g The green component (0-255).
     * @param b The blue component (0-255).
     * @return An integer representing the RGB color in 24-bit format (0xRRGGBB).
     */
    public static int intColor(int r, int g, int b) {
        return (r * 65536 + g * 256 + b);
    }

    /**
     * Converts a hexadecimal color (0xRRGGBB) to an RGB array.
     *
     * @param hex The hexadecimal color (0xRRGGBB).
     * @return An array containing the RGB components: [r, g, b].
     */
    public static int[] hexToRGB(int hex) {
        var colors = new int[3];
        colors[0] = hex >> 16 & 255;
        colors[1] = hex >> 8 & 255;
        colors[2] = hex & 255;
        return colors;
    }

    /**
     * Interpolates between two float values based on the given proportion.
     *
     * @param a          The starting value.
     * @param b          The ending value.
     * @param proportion The proportion to interpolate (0.0f to 1.0f).
     * @return The interpolated value.
     */
    private static float interpolate(float a, float b, float proportion) {
        return (a + ((b - a) * proportion));
    }

    /**
     * Interpolates between two colors in RGB format based on the given proportion.
     * This method interpolates colors in the HSB color model.
     *
     * @param a          The first color in RGB (0xRRGGBB).
     * @param b          The second color in RGB (0xRRGGBB).
     * @param proportion The proportion to interpolate (0.0f to 1.0f).
     * @return The interpolated color in RGB format (0xRRGGBB).
     */
    public static int interpolateColor(int a, int b, float proportion) {
        var hsva = new float[3];
        var hsvb = new float[3];

        rgbToHSB((a >> 16) & 0xFF, (a >> 8) & 0xFF, a & 0xFF, hsva);
        rgbToHSB((b >> 16) & 0xFF, (b >> 8) & 0xFF, b & 0xFF, hsvb);

        for (var i = 0; i < 3; i++) {
            hsvb[i] = interpolate(hsva[i], hsvb[i], proportion);
        }

        var alpha = interpolate((a >> 24) & 0xFF, (b >> 24) & 0xFF, proportion);
        return hsbToRGB(hsvb[0], hsvb[1], hsvb[2]) | ((int) (alpha * 255) & 0xFF);
    }

    /**
     * Adjusts the saturation of a color.
     *
     * @param color      The color in RGB (0xRRGGBB).
     * @param saturation The saturation factor (0.0 to 1.0, where 0.0 is no saturation).
     * @return The color with adjusted saturation in RGB format (0xRRGGBB).
     */
    public static int saturate(int color, float saturation) {
        var hsv = new float[3];
        rgbToHSB((color >> 16) & 255, (color >> 8) & 255, color & 255, hsv);
        hsv[1] *= saturation;
        return hsbToRGB(hsv[0], hsv[1], hsv[2]);
    }

    /**
     * Converts a hexadecimal color with an alpha value to an integer representation.
     *
     * @param hex   The hexadecimal color (0xRRGGBB).
     * @param alpha The alpha value (0-255).
     * @return The color with alpha in ARGB format (0xAARRGGBB).
     */
    public static int hexToIntWithAlpha(int hex, int alpha) {
        return alpha << 24 | hex & 0x00FFFFFF;
    }

    /**
     * Calculates the alpha value based on the current and maximum values.
     *
     * @param current The current value.
     * @param max     The maximum value.
     * @return The calculated alpha value (0-255).
     */
    public static int calcAlpha(double current, double max) {
        return (int) ((max - current) / max) * 255;
    }

    /**
     * Converts HSB values to RGB format.
     *
     * @param hue        The hue value (0.0f to 1.0f).
     * @param saturation The saturation value (0.0f to 1.0f).
     * @param brightness The brightness value (0.0f to 1.0f).
     * @return The color in RGB format (0xRRGGBB).
     */
    public static int hsbToRGB(float hue, float saturation, float brightness) {
        int r = 0, g = 0, b = 0;
        if (saturation == 0) {
            r = g = b = (int) (brightness * 255.0f + 0.5f);
        } else {
            float h = (hue - (float) Math.floor(hue)) * 6.0f;
            float f = h - (float) Math.floor(h);
            float p = brightness * (1.0f - saturation);
            float q = brightness * (1.0f - saturation * f);
            float t = brightness * (1.0f - (saturation * (1.0f - f)));
            switch ((int) h) {
                case 0 -> {
                    r = (int) (brightness * 255.0f + 0.5f);
                    g = (int) (t * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                }
                case 1 -> {
                    r = (int) (q * 255.0f + 0.5f);
                    g = (int) (brightness * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                }
                case 2 -> {
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (brightness * 255.0f + 0.5f);
                    b = (int) (t * 255.0f + 0.5f);
                }
                case 3 -> {
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (q * 255.0f + 0.5f);
                    b = (int) (brightness * 255.0f + 0.5f);
                }
                case 4 -> {
                    r = (int) (t * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (brightness * 255.0f + 0.5f);
                }
                case 5 -> {
                    r = (int) (brightness * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (q * 255.0f + 0.5f);
                }
            }
        }
        return 0xFF000000 | (r << 16) | (g << 8) | b;
    }

    /**
     * Converts RGB values to HSB (Hue, Saturation, Brightness).
     *
     * @param r       The red component (0-255).
     * @param g       The green component (0-255).
     * @param b       The blue component (0-255).
     * @param hsbvals An array to hold the HSB values. If null, a new array will be created.
     * @return An array containing the HSB values: [hue, saturation, brightness].
     */
    public static float[] rgbToHSB(int r, int g, int b, float[] hsbvals) {
        float hue, saturation, brightness;
        if (hsbvals == null) {
            hsbvals = new float[3];
        }

        var cmax = Math.max(r, g);
        if (b > cmax) cmax = b;

        var cmin = Math.min(r, g);
        if (b < cmin) cmin = b;

        brightness = ((float) cmax) / 255.0f;

        if (cmax != 0) {
            saturation = ((float) (cmax - cmin)) / ((float) cmax);
        } else {
            saturation = 0;
        }

        if (saturation == 0) {
            hue = 0;
        } else {
            var redc = ((float) (cmax - r)) / ((float) (cmax - cmin));
            var greenc = ((float) (cmax - g)) / ((float) (cmax - cmin));
            var bluec = ((float) (cmax - b)) / ((float) (cmax - cmin));

            if (r == cmax)
                hue = bluec - greenc;
            else if (g == cmax)
                hue = 2.0f + redc - bluec;
            else
                hue = 4.0f + greenc - redc;

            hue = hue / 6.0f;
            if (hue < 0)
                hue = hue + 1.0f;
        }

        hsbvals[0] = hue;
        hsbvals[1] = saturation;
        hsbvals[2] = brightness;

        return hsbvals;
    }
}

