package net.michaelbarnett17;

import java.util.*;
import org.apache.commons.math3.linear.*;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Beam {
    
    public Beam() {
        forceDistances = new ArrayList<Double>();
        forces         = new ArrayList<Double>();
        forceAngles    = new ArrayList<Double>();
    }

    private int id;

    private double length;
    private double pinDistance;
    private double rollerDistance;
   
    private double force;
    private ArrayList<Double> forces;
    
    private double forceDistance;
    private ArrayList<Double> forceDistances;
    
    private double forceAngle;
    private ArrayList<Double> forceAngles;
    
    //Moments not yet implemented
    private double moment;
    private ArrayList<Double> moments;

    private double pinReactionX;
    private double pinReactionY;
    private double rollerReactionY;
    
    //////////////GETTERS AND SETTERS///////////////////////////////////////////
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getPinDistance() {
        return pinDistance;
    }

    public void setPinDistance(double pinDistance) {
        this.pinDistance = pinDistance;
    }

    public double getRollerDistance() {
        return rollerDistance;
    }

    public void setRollerDistance(double rollerDistance) {
        this.rollerDistance = rollerDistance;
    }

    public double getForce() {
        return force;
    }

    public void setForce(double force) {
        this.force = force;
    }

    public ArrayList<Double> getForces() {
        return forces;
    }

    public void setForces(ArrayList<Double> forces) {
        this.forces = forces;
    }

    public double getForceDistance() {
        return forceDistance;
    }

    public void setForceDistance(double forceDistance) {
        this.forceDistance = forceDistance;
    }

    public ArrayList<Double> getForceDistances() {
        return forceDistances;
    }

    public void setForceDistances(ArrayList<Double> forceDistances) {
        this.forceDistances = forceDistances;
    }

    public double getForceAngle() {
        return forceAngle;
    }

    public void setForceAngle(double forceAngle) {
        this.forceAngle = forceAngle;
    }

    public ArrayList<Double> getForceAngles() {
        return forceAngles;
    }

    public void setForceAngles(ArrayList<Double> forceAngles) {
        this.forceAngles = forceAngles;
    }

    public double getMoment() {
        return moment;
    }

    public void setMoment(double moment) {
        this.moment = moment;
    }

    public ArrayList<Double> getMoments() {
        return moments;
    }

    public void setMoments(ArrayList<Double> moments) {
        this.moments = moments;
    }

    public double getPinReactionX() {
        return pinReactionX;
    }

    public void setPinReactionX(double pinReactionX) {
        this.pinReactionX = pinReactionX;
    }

    public double getPinReactionY() {
        return pinReactionY;
    }

    public void setPinReactionY(double pinReactionY) {
        this.pinReactionY = pinReactionY;
    }

    public double getRollerReactionY() {
        return rollerReactionY;
    }

    public void setRollerReactionY(double rollerReactionY) {
        this.rollerReactionY = rollerReactionY;
    }
    
    //////////////END OF GETTERS AND SETTERS////////////////////////////////////
     
    public void appendForceDistance(double forceDistance){
        forceDistances.add(forceDistance);
    }
    
    public void appendForce(double force){
        forces.add(force);
    }
     
     public void appendForceAngle(double forceAngle){
        forceAngles.add(forceAngle*PI/180);
    }       
        
    public double sumVerticalForces(){
        double sumOfVerticalForces = 0;
         
        for (int i = 0; i < forces.size(); i++) {
            sumOfVerticalForces += forces.get(i)*cos(forceAngles.get(i));             
        }
        return sumOfVerticalForces;
    }
     
    public double sumHorizontalForces(){
        double sumOfHorizontalForces = 0;
         
        for (int i = 0; i < forces.size(); i++) {
             sumOfHorizontalForces += forces.get(i)*sin(forceAngles.get(i));             
        }
        return sumOfHorizontalForces;         
    }
     
     public double sumMomentsFromForces() {
         double sumOfMomentsFromForces = 0;
         
        for (int i = 0; i < forces.size(); i++) {
             sumOfMomentsFromForces += forces.get(i)*cos(forceAngles.get(i))*forceDistances.get(i);             
        }
        return sumOfMomentsFromForces;                  
     }
     
    public RealVector solve() {

        // Remove this when Moments are implemented
        moment = 0;

        ////////////ATTENTION//////////////////////
        forceAngle = forceAngles.get(0)*PI/180;

        double[] myArray = new double[] {0, 1, 1};

        RealMatrix coefficients =
                new Array2DRowRealMatrix(new double[][] { { 1, 0, 0 }, myArray, { 0, pinDistance, rollerDistance } },
                        false);
        DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();

        RealVector constants = new ArrayRealVector(new double[] { -sumHorizontalForces(), -sumVerticalForces(), -sumMomentsFromForces() - moment}, false);
        RealVector solution = solver.solve(constants);
        
        return solution;
    }
}