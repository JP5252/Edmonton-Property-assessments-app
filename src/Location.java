/**
 * This class holds variables for the location of a property.
 */
public class Location {
    private double latitude;
    private double longitude;
    private final String pointLocation;

    /**
     * this constructs the location object using the given latitude, longitude
     * and point location.
     *
     * @param latitude      latitude as a double.
     * @param longitude     longitude as a double.
     * @param pointLocation point location as a string.
     */
    public Location(String latitude, String longitude, String pointLocation) {
        // convert latitude from string to double
        try {
            this.latitude = Double.parseDouble(latitude);
        } catch (NumberFormatException ex) {
            this.latitude = 0;
        }
        // convert longitude from string to double
        try {
            this.longitude = Double.parseDouble(longitude);
        } catch (NumberFormatException ex) {
            this.longitude = 0;
        }
        this.pointLocation = pointLocation.replace("POINT", "");
    }

    /**
     * gets the latitude from the location.
     *
     * @return the latitude
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * gets the longitude from the location.
     *
     * @return the longitude
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * gets the point location.
     *
     * @return the point location
     */
    public String getPointLocation() {
        return this.pointLocation;
    }

    /**
     * override of toString method for Location
     *
     * @return the point location as a string
     */
    public String toString() {
        return this.pointLocation;
    }
}

