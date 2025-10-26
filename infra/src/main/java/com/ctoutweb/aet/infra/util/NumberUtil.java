package com.ctoutweb.aet.infra.util;

import java.util.Random;

public class NumberUtil {

    /**
     * Génération d'un chiffre aléatoire borné
     *
     * @param min La borne minimum inclus
     * @param max La borne maximum inclus
     *
     * @return Le chiffre généré de maniere aléatoire
     *
     * @param <T> Le type numeric du chiffre (Double, Integrer....)
     */
    @SuppressWarnings("unchecked")
    public static <T extends Number> T generateRandomNumberBetweenMinAndMax(T min, T max) {

        double minValue = min.doubleValue();
        double maxValue = max.doubleValue();

        Random rand = new Random();
        double randomResult = rand.nextDouble((maxValue - minValue) + 1) + minValue;


        if (min instanceof Integer) {
            return (T) Integer.valueOf((int) randomResult);
        } else if (min instanceof Double) {
            return (T) Double.valueOf(randomResult);
        } else if (min instanceof Long) {
            return (T) Long.valueOf((long) randomResult);
        } else if (min instanceof Float) {
            return (T) Float.valueOf((float) randomResult);
        } else if (min instanceof Short) {
            return (T) Short.valueOf((short) randomResult);
        } else if (min instanceof Byte) {
            return (T) Byte.valueOf((byte) randomResult);
        } else {
            throw new IllegalArgumentException("Unsupported numeric type");
        }

    }
}
