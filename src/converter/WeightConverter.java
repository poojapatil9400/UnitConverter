package converter;

public class WeightConverter {
    public static double convert(double value, String fromUnit, String toUnit) {
        double inKilograms;
        switch (fromUnit.toLowerCase()) {
            case "kg": inKilograms = value; break;
            case "g": inKilograms = value / 1000; break;
            case "mg": inKilograms = value / 1_000_000; break;
            case "pound": inKilograms = value * 0.453592; break;
            case "ounce": inKilograms = value * 0.0283495; break;
            default: throw new IllegalArgumentException("Invalid unit: " + fromUnit);
        }

        switch (toUnit.toLowerCase()) {
            case "kg": return inKilograms;
            case "g": return inKilograms * 1000;
            case "mg": return inKilograms * 1_000_000;
            case "pound": return inKilograms / 0.453592;
            case "ounce": return inKilograms / 0.0283495;
            default: throw new IllegalArgumentException("Invalid unit: " + toUnit);
        }
    }
}