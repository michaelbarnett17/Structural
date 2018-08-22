package net.michaelbarnett17;

/**
 *
 * @author mike
 */
public class Force {
    private int forceId;
    
    private double forceMagnitude;
    private double forceAngle;
    private double forceXLocation;
    private double forceYLocation;  
    
    public Force() {}

    public int getForceId() {
        return forceId;
    }

    public void setForceId(int forceId) {
        this.forceId = forceId;
    }

    public double getForceMagnitude() {
        return forceMagnitude;
    }

    public void setForceMagnitude(double forceMagnitude) {
        this.forceMagnitude = forceMagnitude;
    }

    public double getForceAngle() {
        return forceAngle;
    }

    public void setForceAngle(double forceAngle) {
        this.forceAngle = forceAngle;
    }

    public double getForceXLocation() {
        return forceXLocation;
    }

    public void setForceXLocation(double forceXLocation) {
        this.forceXLocation = forceXLocation;
    }

    public double getForceYLocation() {
        return forceYLocation;
    }

    public void setForceYLocation(double forceYLocation) {
        this.forceYLocation = forceYLocation;
    }

    @Override
    public String toString() {
        return "force" + this.getForceId() + " " + this.getForceMagnitude() + " " + this.getForceAngle() +
                " " + this.getForceXLocation() + " " + this.getForceXLocation();
    }
}
