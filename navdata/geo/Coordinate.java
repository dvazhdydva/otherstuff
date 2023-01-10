package geo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;
import static geo.Coords.EARTH_RADIUS;

import static geo.Coords.of;

public final class Coordinate implements Serializable, GeoConstants {

    private static final long serialVersionUID = -2849796756373860001L;
//    private static final int EARTH_RADIUS = 6371000; // meters   <--- imported

    private static final BigDecimal BIG_180 = BigDecimal.valueOf(180);
    private static final BigDecimal BIG_2 = BigDecimal.valueOf(2);
    private static final BigDecimal BIG_PI = BigDecimal.valueOf(Math.PI);
    private static final BigDecimal BIG_1000 = BigDecimal.valueOf(1000);
    private static final BigDecimal BIG_1_852 = BigDecimal.valueOf(1.852);
    private static final RoundingMode BIG_ROUNDING = RoundingMode.DOWN;

    private final BigDecimal LAT;
    private final BigDecimal LON;

    public Coordinate(double lat, double lon) {
        if (Math.abs(lat) > 90) {
            throw new IllegalArgumentException("Latitude value " + Math.abs(lat) + " is greater than 90");
        } else if (Math.abs(lon) > 180) {
            throw new IllegalArgumentException("Longitude value " + Math.abs(lon) + " is greater than 180");
        }
        LAT = BigDecimal.valueOf(lat);
        LON = BigDecimal.valueOf(lon);
    }

    public Coordinate(BigDecimal lat, BigDecimal lon) {
        this(lat, lon, 15);
    }

    // thought might be usefull if we want to truncate the mantissa
    private Coordinate(BigDecimal lat, BigDecimal lon, int truncated) {
        if (lat.abs().doubleValue() > 90.0) {
            throw new IllegalArgumentException("Latitude value " + lat.abs().setScale(5, BIG_ROUNDING).doubleValue() + " is greater than 90");
        } else if (lon.abs().doubleValue() > 180.0) {
            throw new IllegalArgumentException("Longitude value " + lon.abs().setScale(5, BIG_ROUNDING).doubleValue() + " is greater than 180");
        }
        LAT = lat.setScale(truncated, BIG_ROUNDING);
        LON = lon.setScale(truncated, BIG_ROUNDING);
    }

    //just in case
    public Coordinate clone() {
        return new Coordinate(this.LAT, this.LON);
    }

    public BigDecimal[] getCoordinate() {
        return new BigDecimal[]{LAT, LON};
    }

    public BigDecimal getLat() {
        return LAT;
    }

    public BigDecimal getLon() {
        return LON;
    }

    public boolean isSouthOf(Coordinate coordinate2) {
        return coordinate2.getLat().subtract(LAT).doubleValue() > 0 ? true : false;
    }

    public boolean isWestOf(Coordinate coordinate2) {
        return (coordinate2.getLon().subtract(LON).doubleValue() < 0 ||
                Math.abs(coordinate2.getLon().subtract(LON).doubleValue()) >= 180) ? false : true;
    }

    public boolean isCollocatedWith(Coordinate coordinate2, double withinRadius) {
        if (this.equalsTruncated(coordinate2, 10)) {
            return true;
        } else if (withinRadius == 0.0) {
            return false;
        } else {
            return this.distanceTo(coordinate2) <= withinRadius ? true : false;
//            return Coords.distance(this, coordinate2) <= withinRadius ? true : false;
        }
    }

    public boolean isCollocatedWith(Coordinate coordinate2) {
        return this.isCollocatedWith(coordinate2, 0.0);
    }

    public DistanceHeadingMidPoint to(Coordinate coordinate2) { // redo to BigDecimals?

        // calculation using double values
//        double d = 6371000 * (2 * Math.atan2(
        double d = EARTH_RADIUS * (2 * Math.atan2(
                Math.sqrt(Math.sin(((this.LAT.doubleValue() - coordinate2.LAT.doubleValue()) * Math.PI / 180) / 2)
                        * Math.sin(((this.LAT.doubleValue() - coordinate2.LAT.doubleValue()) * Math.PI / 180) / 2)
                        + Math.cos(this.LAT.doubleValue() * Math.PI / 180) * Math.cos(coordinate2.LAT.doubleValue() * Math.PI / 180)
                        * Math.sin(((this.LON.doubleValue() - coordinate2.LON.doubleValue()) * Math.PI / 180) / 2)
                        * Math.sin(((this.LON.doubleValue() - coordinate2.LON.doubleValue()) * Math.PI / 180) / 2)),
                Math.sqrt(1 - (Math.sin(((this.LAT.doubleValue() - coordinate2.LAT.doubleValue()) * Math.PI / 180) / 2)
                        * Math.sin(((this.LAT.doubleValue() - coordinate2.LAT.doubleValue()) * Math.PI / 180) / 2)
                        + Math.cos(this.LAT.doubleValue() * Math.PI / 180) * Math.cos(coordinate2.LAT.doubleValue() * Math.PI / 180)
                        * Math.sin(((this.LON.doubleValue() - coordinate2.LON.doubleValue()) * Math.PI / 180) / 2)
                        * Math.sin(((this.LON.doubleValue() - coordinate2.LON.doubleValue()) * Math.PI / 180) / 2)))));

/*      BigDecimal calculation (also see return)
        double dBig = 6371000 * (2 * Math.atan2(
                Math.sqrt(Math.sin(
                        this.LAT.subtract(coordinate2.LAT).multiply(BIG_PI).divide(BIG_180,15,RoundingMode.DOWN).divide(BIG_2,15,RoundingMode.DOWN).doubleValue())
                        * Math.sin(
                        this.LAT.subtract(coordinate2.LAT).multiply(BIG_PI).divide(BIG_180,15,RoundingMode.DOWN).divide(BIG_2,15,RoundingMode.DOWN).doubleValue())
                        + Math.cos(this.LAT.multiply(BIG_PI).divide(BIG_180,15,RoundingMode.DOWN).doubleValue())
                        * Math.cos(coordinate2.LAT.multiply(BIG_PI).divide(BIG_180,15,RoundingMode.DOWN).doubleValue())
                        * Math.sin(
                        this.LON.subtract(coordinate2.LON).multiply(BIG_PI).divide(BIG_180,15,RoundingMode.DOWN).divide(BIG_2,15,RoundingMode.DOWN).doubleValue())
                        * Math.sin(
                        this.LON.subtract(coordinate2.LON).multiply(BIG_PI).divide(BIG_180,15,RoundingMode.DOWN).divide(BIG_2,15,RoundingMode.DOWN).doubleValue())),
                Math.sqrt(1 - (Math.sin(
                        this.LAT.subtract(coordinate2.LAT).multiply(BIG_PI).divide(BIG_180,15,RoundingMode.DOWN).divide(BIG_2,15,RoundingMode.DOWN).doubleValue())
                        * Math.sin(
                        this.LAT.subtract(coordinate2.LAT).multiply(BIG_PI).divide(BIG_180,15,RoundingMode.DOWN).divide(BIG_2,15,RoundingMode.DOWN).doubleValue())
                        + Math.cos(
                        this.LAT.multiply(BIG_PI).divide(BIG_180,15,RoundingMode.DOWN).doubleValue())
                        * Math.cos(
                                coordinate2.LAT.multiply(BIG_PI).divide(BIG_180,15,RoundingMode.DOWN).doubleValue())
                        * Math.sin(
                                this.LON.subtract(coordinate2.LON).multiply(BIG_PI).divide(BIG_180,15,RoundingMode.DOWN).divide(BIG_2,15,RoundingMode.DOWN).doubleValue())
                        * Math.sin(
                                this.LON.subtract(coordinate2.LON).multiply(BIG_PI).divide(BIG_180,15,RoundingMode.DOWN).divide(BIG_2,15,RoundingMode.DOWN).doubleValue())
                ))));

 */

        double t = Math.atan2(
                (Math.sin((coordinate2.LON.doubleValue() * Math.PI / 180) - (this.LON.doubleValue() * Math.PI / 180))
                        * Math.cos(coordinate2.LAT.doubleValue() * Math.PI / 180)),
                (Math.cos(this.LAT.doubleValue() * Math.PI / 180) * Math.sin(coordinate2.LAT.doubleValue() * Math.PI / 180)
                        - Math.sin(this.LAT.doubleValue() * Math.PI / 180) * Math.cos(coordinate2.LAT.doubleValue() * Math.PI / 180)
                        * Math.cos((coordinate2.LON.doubleValue() * Math.PI / 180) - (this.LON.doubleValue() * Math.PI / 180))));

        double f3 = Math
                .atan2(Math.sin(this.LAT.doubleValue() * Math.PI / 180) + Math.sin(coordinate2.LAT.doubleValue() * Math.PI / 180),
                        Math.sqrt((Math.cos(this.LAT.doubleValue() * Math.PI / 180) + (Math.cos(coordinate2.LAT.doubleValue() * Math.PI / 180)
                                * Math.cos((coordinate2.LON.doubleValue() * Math.PI / 180) - (this.LON.doubleValue() * Math.PI / 180))))
                                * (Math.cos(this.LAT.doubleValue() * Math.PI / 180) + (Math.cos(coordinate2.LAT.doubleValue() * Math.PI / 180)
                                * Math.cos((coordinate2.LON.doubleValue() * Math.PI / 180) - (this.LON.doubleValue() * Math.PI / 180))))
                                + Math.pow(
                                Math.cos(coordinate2.LAT.doubleValue() * Math.PI / 180)
                                        * Math.sin((coordinate2.LON.doubleValue() * Math.PI / 180) - (this.LON.doubleValue() * Math.PI / 180)),
                                2)));
        double l3 = (this.LON.doubleValue() * Math.PI / 180) + Math.atan2(
                Math.cos(coordinate2.LAT.doubleValue() * Math.PI / 180)
                        * Math.sin((coordinate2.LON.doubleValue() * Math.PI / 180) - (this.LON.doubleValue() * Math.PI / 180)),
                Math.cos(this.LAT.doubleValue() * Math.PI / 180) + (Math.cos(coordinate2.LAT.doubleValue() * Math.PI / 180)
                        * Math.cos((coordinate2.LON.doubleValue() * Math.PI / 180) - (this.LON.doubleValue() * Math.PI / 180))));

/*
                difference between the BigDecimal (dBig) and double (d) calculations is 0.0000000000004nm
*/

//        return new HeadingDistance(BigDecimal.valueOf(dBig).divide(BIG_1000,15,RoundingMode.DOWN).divide(BIG_1_852,15,RoundingMode.DOWN).doubleValue(),
//                (t * 180 / Math.PI + 360) % 360, new Coordinate(f3 * 180 / Math.PI, l3 * 180 / Math.PI)); // in nautical miles
        return new DistanceHeadingMidPoint(d / 1000 / 1.852, (t * 180 / Math.PI + 360) % 360, new Coordinate(f3 * 180 / Math.PI, l3 * 180 / Math.PI)); // in nautical miles

    }

    public double headingTo(Coordinate coordinate2){
        return this.to(coordinate2).getHeading();
    }

    public double distanceTo(Coordinate coordinate2){
        return this.to(coordinate2).getDistance();
    }

    public Coordinate midPointTo(Coordinate coordinate2){
        return this.to(coordinate2).getMidPoint();
    }

    static class DistanceHeadingMidPoint {
        private final Double heading;
        private final Double distance;
        private final Coordinate midPoint;

        private DistanceHeadingMidPoint(double distance, double heading, Coordinate midPoint) {
            this.distance = distance;
            this.heading = heading;
            this.midPoint = midPoint;
        }

        public Double getHeading() {
            return heading;
        }

        public Double getDistance() {
            return distance;
        }

        public Coordinate getMidPoint() {
            return midPoint;
        }

        @Override
        public String toString() {
            return distance.toString() + " " + heading.toString() + " " + midPoint.toString();
        }
    }


    // CHECK, RESULTS ARE WRONG!!!!!!!!!!!
//    Coordinate umlat = of("N  51 40  333  W 000 41  650"); from umlat to wobun brng: 356, dist 20.9
//    Coordinate wobun = of("N  52 01  167  W 000 44  000");
    public Coordinate bearingAndDistance(double bearing, double distance) {
        // true bearing!

        distance *= M_IN_NAUTICAL_MILE; // to meteres

        /*
         * const φ2 = Math.asin( Math.sin(φ1)*Math.cos(d/R) +
         * Math.cos(φ1)*Math.sin(d/R)*Math.cos(brng) ); const λ2 = λ1 +
         * Math.atan2(Math.sin(brng)*Math.sin(d/R)*Math.cos(φ1),
         * Math.cos(d/R)-Math.sin(φ1)*Math.sin(φ2));
         */
        double l1 = this.LON.doubleValue() * Math.PI / 180.0; //lon
        double f1 = this.LAT.doubleValue() * Math.PI / 180.0; //lat

        double f2 = Math.asin(Math.sin(f1) * Math.cos(distance / EARTH_RADIUS)
                + Math.cos(f1) * Math.sin(distance / EARTH_RADIUS) * Math.cos(bearing));
        double l2 = l1 + Math.atan2(Math.sin(bearing) * Math.sin(distance / EARTH_RADIUS) * Math.cos(f1),
                Math.cos(distance / EARTH_RADIUS) - Math.sin(f1) * Math.sin(f2));

        return new Coordinate(f2 * 180.0/ Math.PI,l2 * 180.0 / Math.PI);
    }

    public Coordinate grid() {
        return grid(GridSpacing.TEN);
    }
    public Coordinate grid(GridSpacing spacing) { // gridSpacing might need to be rephrased
        BigDecimal latMinutes = this.LAT.subtract(BigDecimal.valueOf(this.LAT.intValue())).multiply(BigDecimal.valueOf(60));//lat min
        BigDecimal lonMinutes = this.LON.subtract(BigDecimal.valueOf(this.LON.intValue())).multiply(BigDecimal.valueOf(60));//lon min

        if (spacing == GridSpacing.WHOLE) {
            return new Coordinate(this.LAT.intValue(), this.LON.intValue());
        } else if (spacing == GridSpacing.HALF) {

            double latM = 0.0;
            double lonM = 0.0;

            if (latMinutes.intValue() > 30) latM = 0.5;
            if (lonMinutes.intValue() > 30) lonM = 0.5;

            double lat = this.LAT.abs().intValue() + latM;
            double lon = this.LON.abs().intValue() + lonM;

            if (this.LAT.compareTo(BigDecimal.ZERO) < 0) {
                lat *= -1;
            }
            if (this.LON.compareTo(BigDecimal.ZERO) < 0) {
                lon *= -1;
            }
            return new Coordinate(lat, lon);
        } else {
            double latM = BigDecimal.valueOf((int) latMinutes.intValue() / 10).multiply(BigDecimal.TEN).divide(BigDecimal.valueOf(60), 15, RoundingMode.HALF_EVEN).doubleValue();
            double lonM = ((int) lonMinutes.intValue() / 10) * 10 / 60.0;

            double lat = this.LAT.abs().intValue() + latM;
            double lon = this.LON.abs().intValue() + lonM;

            if (this.LAT.compareTo(BigDecimal.ZERO) < 0) {
                lat *= -1;
            }
            if (this.LON.compareTo(BigDecimal.ZERO) < 0) {
                lon *= -1;
            }
            return new Coordinate(lat, lon);
        }
    }

    public enum GridSpacing {
        WHOLE, // whole degree or every degree
        TEN, // every ten minutes
        HALF; // half of a degree (30 min)
    }

    @Override
    public String toString() {
        return "[LAT: " +
                (LAT.compareTo(BigDecimal.valueOf(0.0)) >= 0 ? "+" : "") +
                new DecimalFormat("00.000000000000000").format(LAT) +
                " LON: " +
                (LON.compareTo(BigDecimal.valueOf(0.0)) >= 0 ? "+" : "") +
                new DecimalFormat("000.000000000000000").format(LON) +
                "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (this == null || obj == null || !(obj instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) obj;
        return that.LAT.compareTo(this.LAT) == 0 && that.LON.compareTo(this.LON) == 0 && this.hashCode() == that.hashCode();
    }

    public boolean equalsTruncated(Coordinate coordinate2, int truncScale) {
        if (!(coordinate2 instanceof Coordinate)) return false;
        if (truncScale > 15 || truncScale < 1)
            throw new IllegalArgumentException("Scale must be positive or NOT greater than 15");
        return this.LAT.round(new MathContext(truncScale)).compareTo(coordinate2.LAT.round(new MathContext(truncScale))) == 0 &&
                this.LON.round(new MathContext(truncScale)).compareTo(coordinate2.LON.round(new MathContext(truncScale))) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(LAT, LON);
    }
}
