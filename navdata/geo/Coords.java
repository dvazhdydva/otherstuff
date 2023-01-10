package geo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * class only consists of static methods and designed to perform various calculations with coordinates
 * and display tem in a readable format.
 * Also initializes a Coordinate with String params.
 */
public final class Coords implements GeoConstants{
//    static final int EARTH_RADIUS = 6371000; // meters
//    private static final double KM_IN_NAUTICAL_MILE = 1.852; //
//    private static final double FEET_IN_METER = 0.3048; //
//    private static final String DEGREE = "\u00B0"; // degree sign
//    private static final String MINUTE = "'";
//    private static final String SECOND = String.valueOf((char) 34);
//
//    private static final String COLON = String.valueOf((char) 58);
//    private static final String DECIMAL = String.valueOf((char) 46);
//    private static final String LAT = "Latitude";
//    private static final String LON = "Longitude";
//    private static final String KM = "km";
//    private static final String NM = "nm";
//    private static final String M = "m";
//    private static final String F = "feet";
    private static final RoundingMode BIG_ROUNDING = RoundingMode.HALF_EVEN;

    private Coords() {
    }

    public static Coordinate of(String stringLatLonCoordinate) {
        String[] strArr = stringLatLonCoordinate.split(" ");
        if(strArr.length==2 && isNumber(strArr[0].replace(",","")) && isNumber(strArr[1].replace(",",""))) {
            return new Coordinate(Double.parseDouble(strArr[0].replace(",","")),Double.parseDouble(strArr[1].replace(",","")));
        }
        stringLatLonCoordinate = stringLatLonCoordinate.toUpperCase();
        char[] charArr = stringLatLonCoordinate.toCharArray();
        return strCoordsConverter(charArr);
    }

    public static Coordinate of(String[] stringLatLonCoordinate) {
        if(stringLatLonCoordinate.length == 2 && isNumber(stringLatLonCoordinate[0]) && isNumber(stringLatLonCoordinate[1])) {
            return new Coordinate(Double.parseDouble(stringLatLonCoordinate[0]),Double.parseDouble(stringLatLonCoordinate[1]));
        }
        StringBuilder combo = new StringBuilder();
        for (int i = 0; i < stringLatLonCoordinate.length; i++) {
            combo.append(stringLatLonCoordinate[i]);
        }
        char[] charArr = combo.toString().toUpperCase().toCharArray();

        return strCoordsConverter(charArr);
    }

    public static Coordinate of(String stringLatitude, String stringLongitude) {
        if(isNumber(stringLatitude) && isNumber(stringLongitude)) {
            return new Coordinate(Double.parseDouble(stringLatitude),Double.parseDouble(stringLongitude));
        }
        char[] charArr = (stringLatitude.toUpperCase() + stringLongitude.toUpperCase()).toCharArray();
        return strCoordsConverter(charArr);
    }

    private static boolean isNumber(String str){
        try{
            Double.parseDouble(str);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private static Coordinate strCoordsConverter(char[] charArr) {  // only string coordinates (no double values) for the time being
        BigDecimal lat = BigDecimal.valueOf(0);
        BigDecimal lon = BigDecimal.valueOf(0);

        StringBuilder temp = new StringBuilder();

        for (char c : charArr) {
            if ((c >= 48 && c <= 57) || (c >= 44 && c <= 46) || c == 78 || c == 83 || c == 69 || c == 87) { //ascii nums 48-57; ,-. 44-46; E 69; N 78; S 83; 87 W
                temp.append(c);
            }
        }
        String rawCoords = temp.toString();

        if ((rawCoords.indexOf('N') == -1 && rawCoords.indexOf('S') == -1) ||
                (rawCoords.indexOf('E') == -1 && rawCoords.indexOf('W') == -1) ||
                rawCoords.chars().filter(ch -> ch == 'N').count() > 1 ||
                rawCoords.chars().filter(ch -> ch == 'S').count() > 1 ||
                rawCoords.chars().filter(ch -> ch == 'E').count() > 1 ||
                rawCoords.chars().filter(ch -> ch == 'W').count() > 1 ||
                rawCoords.chars().filter(ch -> ch == '.').count() > 2 ||
                rawCoords.chars().filter(ch -> ch == ',').count() > 2 ||
                (rawCoords.indexOf('N') != -1 && rawCoords.indexOf('S') != -1) ||
                (rawCoords.indexOf('E') != -1 && rawCoords.indexOf('W') != -1)) {
            throw new IllegalArgumentException("Invalid coordinate format");
        }
        String latStr;
        int latLetterPosition = -1;
        if (rawCoords.indexOf('N') > latLetterPosition) {
            latLetterPosition = rawCoords.indexOf('N');
        } else {
            latLetterPosition = rawCoords.indexOf('S');
        }
        String lonStr;
        int lonLetterPosition = -1;
        if (rawCoords.indexOf('E') > lonLetterPosition) {
            lonLetterPosition = rawCoords.indexOf('E');
        } else {
            lonLetterPosition = rawCoords.indexOf('W');
        }
        if (latLetterPosition == 0) {
            latStr = rawCoords.substring(1, lonLetterPosition);
            lonStr = rawCoords.substring(lonLetterPosition + 1);
        } else {
            latStr = rawCoords.substring(0, latLetterPosition);
            lonStr = rawCoords.substring(latLetterPosition + 1, lonLetterPosition);
        }

        int latSign = 1;
        int lonSign = 1;

        if (rawCoords.indexOf('S') != -1) {
            latSign = -1;
        }
        if (rawCoords.indexOf('W') != -1) {
            lonSign = -1;
        }

        latStr = latStr.replace(',','.');
        lonStr = lonStr.replace(',','.');

        if (latStr.chars().filter(ch -> ch == '.').count() > 1 ||
                lonStr.chars().filter(ch -> ch == '.').count() > 1) {
            throw new IllegalArgumentException("Invalid Latitude/Longitude representation");
        }

        if (latStr.indexOf('.')==-1){
            if(latStr.length()==4){
                latStr+=".000";
            }else if(latStr.length()>4){
                latStr = latStr.substring(0,4) + "." + latStr.substring(4);
            }else{
                throw new IllegalArgumentException("Invalid Latitude value " + latStr + ". Degrees must be 2 digits (eg N01), minutes - 2");
            }
        }else if(latStr.indexOf('.') < 4 && latStr.indexOf('.') > 7){
            throw new IllegalArgumentException("Invalid Latitude value " + latStr + ". Degrees must be 2 digits (eg S01), minutes - 2, decimal seconds (00.0000)");
        }
        if (lonStr.indexOf('.')==-1){
            if(lonStr.length()==5){
                lonStr+=".000";
            }else if(lonStr.length()>5){
                lonStr = lonStr.substring(0,5) + "." + lonStr.substring(5);
            }else{
                throw new IllegalArgumentException("Invalid Longitude value " + lonStr + ". Degrees must be 3 digits (eg W001), minutes - 2");
            }
        }else if(lonStr.indexOf('.') < 5 && lonStr.indexOf('.') > 8){
            throw new IllegalArgumentException("Invalid Longitude value " + lonStr + ". Degrees must be 3 digits (eg E001), minutes - 2, decimal seconds (00.0000)");
        }

        double latDeg = Double.parseDouble(latStr.substring(0,2));
        double lonDeg = Double.parseDouble(lonStr.substring(0,3));
        double latMin = 0.0;
        double lonMin = 0.0;
        double latSec = 0.0;
        double lonSec = 0.0;
        if(latStr.indexOf('.')==4){
            latMin = Double.parseDouble(latStr.substring(2));
        }else{
            latMin = Double.parseDouble(latStr.substring(2,4));
            latSec = Double.parseDouble(latStr.substring(4));
        }

        if(lonStr.indexOf('.')==5){
            lonMin = Double.parseDouble(lonStr.substring(3));
        }else{
            lonMin = Double.parseDouble(lonStr.substring(3,5));
            lonSec = Double.parseDouble(lonStr.substring(5));
        }

        if(Math.abs(latDeg)>90.0) throw new IllegalArgumentException("Latitude degrees are greater than 90");
        if(Math.abs(lonDeg)>180.0) throw new IllegalArgumentException("Longitude degrees are greater than 180");
        if(latMin>=60.0) throw new IllegalArgumentException("Latitude minutes are greater than or equal to 60");
        if(lonMin>=60.0) throw new IllegalArgumentException("Longitude minutes are greater than or equal to 60");
        if(latSec>=60.0) throw new IllegalArgumentException("Latitude seconds are greater than or equal to 60");
        if(lonSec>=60.0) throw new IllegalArgumentException("Longitude seconds are greater than or equal to 60");

        BigDecimal latResult;
        BigDecimal lonResult;

        //N01+(15/60)+(30.30/60) -> 1 + (0.25) + (0.505)
        if(latSec == 0.0){
            latResult = BigDecimal.valueOf(latMin).divide(BigDecimal.valueOf(60),15,BIG_ROUNDING).add(BigDecimal.valueOf(latDeg)).multiply(BigDecimal.valueOf(latSign));
        }else {
            latResult = ((BigDecimal.valueOf(latSec).divide(BigDecimal.valueOf(60),15,BIG_ROUNDING).
                    add(BigDecimal.valueOf(latMin))).divide(BigDecimal.valueOf(60),15,BIG_ROUNDING).
                    add(BigDecimal.valueOf(latDeg))).multiply(BigDecimal.valueOf(latSign));
        }

        if(lonSec == 0.0){
            lonResult = BigDecimal.valueOf(lonMin).divide(BigDecimal.valueOf(60),15,BIG_ROUNDING).add(BigDecimal.valueOf(lonDeg)).multiply(BigDecimal.valueOf(lonSign));
        }else {
            lonResult = BigDecimal.valueOf(lonSec).divide(BigDecimal.valueOf(60),15,BIG_ROUNDING).
                    add(BigDecimal.valueOf(lonMin)).divide(BigDecimal.valueOf(60),15,BIG_ROUNDING).
                    add(BigDecimal.valueOf(lonDeg)).multiply(BigDecimal.valueOf(lonSign));
        }

        return new Coordinate(latResult, lonResult);
    }

    //move to Coordinate
//    public static double distance(Coordinate coordinate1, Coordinate coordinate2) {
//        return 0;
//    }

    //move to Coordinate
//    public static double bearing(Coordinate coordinateFrom, Coordinate coordinateTo) {
//        return 0;
//    }


    // move to Coordinate
    public static Coordinate bearingAndDistance(Coordinate coordinateFrom, double bearing, double distance) {
        // true bearing!
        double[] result = new double[2];

        distance *= 1852; // to meteres

        /*
         * const φ2 = Math.asin( Math.sin(φ1)*Math.cos(d/R) +
         * Math.cos(φ1)*Math.sin(d/R)*Math.cos(brng) ); const λ2 = λ1 +
         * Math.atan2(Math.sin(brng)*Math.sin(d/R)*Math.cos(φ1),
         * Math.cos(d/R)-Math.sin(φ1)*Math.sin(φ2));
         */
        double l1 = coordinateFrom.getLon().doubleValue() * Math.PI / 180; //lon
        double f1 = coordinateFrom.getLat().doubleValue() * Math.PI / 180; //lat

        double f2 = Math.asin(Math.sin(f1) * Math.cos(distance / EARTH_RADIUS)
                + Math.cos(f1) * Math.sin(distance / EARTH_RADIUS) * Math.cos(bearing));
        double l2 = l1 + Math.atan2(Math.sin(bearing) * Math.sin(distance / EARTH_RADIUS) * Math.cos(f1),
                Math.cos(distance / EARTH_RADIUS) - Math.sin(f1) * Math.sin(f2));

//        result[0] = f2 * 180 / Math.PI; // degrees
//        result[1] = l2 * 180 / Math.PI; // degrees
        return new Coordinate(f2 * 180 / Math.PI,l2 * 180 / Math.PI);
    }

    public static String toDD(Coordinate coordinate) { //to decimal degree
        return new DecimalFormat("0.000000000000000").format(coordinate.getLat()) + " " +
                new DecimalFormat("0.000000000000000").format(coordinate.getLon());
    }

    public static String toDM(Coordinate coordinate) { // to decimal minute
        BigDecimal lat = coordinate.getLat();
        BigDecimal lon = coordinate.getLon();
        BigDecimal[] coordsBreaks = coordinateBreaks(lat, lon);

        String latLetter;
        if (lat.compareTo(BigDecimal.ZERO) >= 0) {
            latLetter = "N";
        } else {
            latLetter = "S";
            lat = lat.abs();
        }
        String lonLetter;
        if (lon.compareTo(BigDecimal.ZERO) >= 0) {
            lonLetter = "E";
        } else {
            lonLetter = "W";
            lon = lon.abs();
        }

        String latD = new DecimalFormat("00").format(lat.intValue());
        String lonD = new DecimalFormat("000").format(lon.intValue());

        if (lat.intValue() > 90) throw new NumberFormatException("Invalid " + LAT_STR + " degree value: " + latD);
        if (lon.intValue() > 180) throw new NumberFormatException("Invalid " + LON_STR + " degree value: " + lonD);

        String latM = new DecimalFormat("00.000").format(coordsBreaks[0].setScale(3, BIG_ROUNDING).doubleValue());
        String lonM = new DecimalFormat("00.000").format(coordsBreaks[2].setScale(3, BIG_ROUNDING).doubleValue());

        if (coordsBreaks[0].doubleValue() >= 60.0)
            throw new NumberFormatException("Invalid " + LAT_STR + " minute value: " + latM);
        if (coordsBreaks[2].doubleValue() >= 60.0)
            throw new NumberFormatException("Invalid " + LON_STR + " minute value: " + lonM);

        return latLetter + latD + COLON_STR + latM + " " +
                lonLetter + lonD + COLON_STR + lonM;
    }

    public static String toDS(Coordinate coordinate) { // to decimal seconds
        BigDecimal lat = coordinate.getLat();
        BigDecimal lon = coordinate.getLon();
        BigDecimal[] coordsBreaks = coordinateBreaks(lat, lon);

        String latLetter;
        if (lat.compareTo(BigDecimal.ZERO) >= 0) {
            latLetter = "N";
        } else {
            latLetter = "S";
            lat = lat.abs();
        }
        String lonLetter;
        if (lon.compareTo(BigDecimal.ZERO) >= 0) {
            lonLetter = "E";
        } else {
            lonLetter = "W";
            lon = lon.abs();
        }

        String latD = new DecimalFormat("00").format(lat.intValue());
        String lonD = new DecimalFormat("000").format(lon.intValue());

        if (lat.intValue() > 90) throw new NumberFormatException("Invalid " + LAT_STR + " degree value: " + latD);
        if (lon.intValue() > 180) throw new NumberFormatException("Invalid " + LON_STR + " degree value: " + lonD);

        String latM = new DecimalFormat("00").format(coordsBreaks[0].intValue());
        String lonM = new DecimalFormat("00").format(coordsBreaks[2].intValue());

        if (coordsBreaks[0].intValue() >= 60)
            throw new NumberFormatException("Invalid " + LAT_STR + " minute value: " + latM);
        if (coordsBreaks[2].intValue() >= 60)
            throw new NumberFormatException("Invalid " + LON_STR + " minute value: " + lonM);

        String latS = new DecimalFormat("00.0000").format(coordsBreaks[1].doubleValue());
        String lonS = new DecimalFormat("00.0000").format(coordsBreaks[3].doubleValue());

        if (coordsBreaks[1].doubleValue() >= 60.0)
            throw new NumberFormatException("Invalid " + LAT_STR + " second value: " + latS);
        if (coordsBreaks[3].doubleValue() >= 60.0)
            throw new NumberFormatException("Invalid " + LON_STR + " second value: " + lonS);

        return latLetter + latD + DEGREE_STR + latM + MINUTE_STR + latS + SECOND_STR + " " +
                lonLetter + lonD + DEGREE_STR + lonM + MINUTE_STR + lonS + SECOND_STR;
    }

    private static BigDecimal[] coordinateBreaks(BigDecimal latitude, BigDecimal longitude) {
        BigDecimal[] result = new BigDecimal[4]; //first 2 elements represent [decimal min][decimal sec] of LAT, remaining 2 - LON
        BigDecimal lat = latitude.abs();
        BigDecimal lon = longitude.abs();
        result[0] = lat.subtract(BigDecimal.valueOf(lat.intValue())).multiply(BigDecimal.valueOf(60));//lat min
        result[1] = result[0].subtract(BigDecimal.valueOf(result[0].intValue())).multiply(BigDecimal.valueOf(60)); // lat sec
        result[2] = lon.subtract(BigDecimal.valueOf(lon.intValue())).multiply(BigDecimal.valueOf(60));//lon min
        result[3] = result[2].subtract(BigDecimal.valueOf(result[2].intValue())).multiply(BigDecimal.valueOf(60));//lon sec

        return result;
    }


    public static boolean isARINC424(Coordinate coordinate) {
        return false;
    }

    //move to Coordinate
    public static Coordinate gridOf(Coordinate coordinate) {
        return gridOf(coordinate, GridSpacing.WHOLE);
    }

    //move to Coordinate
    public static Coordinate gridOf(Coordinate coordinate, GridSpacing spacing) { // gridSpacing might need to be rephrased
        if (spacing == GridSpacing.WHOLE) {
            return new Coordinate(coordinate.getLat().intValue(), coordinate.getLon().intValue());
        } else if (spacing == GridSpacing.HALF) {
            BigDecimal[] minutesSet = coordinateBreaks(coordinate.getLat(), coordinate.getLon());

            double latM = 0.0;
            double lonM = 0.0;

            if (minutesSet[0].intValue() > 30) latM = 0.5;
            if (minutesSet[2].intValue() > 30) lonM = 0.5;

            double lat = coordinate.getLat().abs().intValue() + latM;
            double lon = coordinate.getLon().abs().intValue() + lonM;

            if (coordinate.getLat().compareTo(BigDecimal.ZERO) < 0) {
                lat *= -1;
            }

            if (coordinate.getLon().compareTo(BigDecimal.ZERO) < 0) {
                lon *= -1;
            }

            return new Coordinate(lat, lon);

        } else {
            BigDecimal[] minutesSet = coordinateBreaks(coordinate.getLat(), coordinate.getLon());

//            double latM = ((int)minutesSet[0].intValue()10)*10/60.0;
            double latM = BigDecimal.valueOf((int) minutesSet[2].intValue() / 10).multiply(BigDecimal.TEN).divide(BigDecimal.valueOf(60), 15, RoundingMode.HALF_EVEN).doubleValue();


//                    ((BigDecimal.valueOf(
//                    BigDecimal.valueOf(minutesSet[0].intValue()).
//                    divide(BigDecimal.valueOf(10))).intValue()).multiply(BigDecimal.TEN).intValue()).divide(BigDecimal.valueOf(60),10,RoundingMode.HALF_EVEN).doubleValue();
            double lonM = ((int) minutesSet[2].intValue() / 10) * 10 / 60.0;
            int a;
            System.out.println("tenths: " + (a = minutesSet[0].intValue() / 10));
            System.out.println("ones: " + (a *= 10));
            System.out.println("result: " + (latM = coordinate.getLat().abs().intValue() + a / 60.0));

            System.out.println(minutesSet[0].toString());
            System.out.println("latM: " + latM);
            System.out.println("lonM: " + lonM);

//            if (minutesSet[0].intValue() > 30) latM = 0.5;
//            if (minutesSet[2].intValue() > 30) lonM = 0.5;

//            double lat = coordinate.getLat().abs().intValue() + latM;
            double lat = latM;
            double lon = coordinate.getLon().abs().intValue() + lonM;

            if (coordinate.getLat().compareTo(BigDecimal.ZERO) < 0) {
                lat *= -1;
            }

            if (coordinate.getLon().compareTo(BigDecimal.ZERO) < 0) {
                lon *= -1;
            }

            return new Coordinate(lat, lon);

        }
    }

    public static Coordinate opposite(Coordinate coordinate) {
//        return new Coordinate(coordinate.getLat().doubleValue()*-1,coordinate.getLon().doubleValue()*-1);
        return new Coordinate(coordinate.getLat().multiply(BigDecimal.valueOf(-1)), coordinate.getLon().multiply(BigDecimal.valueOf(-1)));
    }

    //move to Coordinate
    public enum GridSpacing {
        WHOLE, // whole degree or every degree
        TEN, // every ten minutes
        HALF; // half of a degree (30 min)
    }


}
