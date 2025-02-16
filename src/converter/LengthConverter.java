package converter;

public class LengthConverter {
    public static double convert(double value, String fromUnit, String toUnit) {
        double inMeters;
        switch (fromUnit.toLowerCase()) {
            case "km": inMeters = value * 1000; break;
            case "m": inMeters = value; break;
            case "cm": inMeters = value / 100; break;
            case "mm": inMeters = value / 1000; break;
            case "inch": inMeters = value * 0.0254; break;
            case "foot": inMeters = value * 0.3048; break;
            default: throw new IllegalArgumentException("Invalid unit: " + fromUnit);
        }

        switch (toUnit.toLowerCase()) {
            case "km": return inMeters / 1000;
            case "m": return inMeters;
            case "cm": return inMeters * 100;
            case "mm": return inMeters * 1000;
            case "inch": return inMeters / 0.0254;
            case "foot": return inMeters / 0.3048;
            default: throw new IllegalArgumentException("Invalid unit: " + toUnit);
        }
    }
}