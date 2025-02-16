package converter;

public class TemperatureConverter {
    public static double convert(double value, String fromUnit, String toUnit) {
        double inCelsius;
        switch (fromUnit.toLowerCase()) {
            case "c": inCelsius = value; break;
            case "f": inCelsius = (value - 32) * 5 / 9; break;
            case "k": inCelsius = value - 273.15; break;
            default: throw new IllegalArgumentException("Invalid unit: " + fromUnit);
        }

        switch (toUnit.toLowerCase()) {
            case "c": return inCelsius;
            case "f": return (inCelsius * 9 / 5) + 32;
            case "k": return inCelsius + 273.15;
            default: throw new IllegalArgumentException("Invalid unit: " + toUnit);
        }
    }
}