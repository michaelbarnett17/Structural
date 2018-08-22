package net.michaelbarnett17;

import java.util.Arrays;

public class Node {

    private double x;
    private double y;

    private double xForcesSum;
    private double yForcesSum;

    //reaction forces
    private double pinX;
    private double pinY;
    private double rollerY;

    //{member1 member2 member3... memberN RX PX PY }
    private double[] arrayX;
    private double[] arrayY;

    public Node(double x, double y) {
        xForcesSum = 0;
        yForcesSum = 0;
        pinX = 0;
        pinY = 0;
        rollerY = 0;
        this.x = x;
        this.y = y;
    }

    public Node(double x, double y, double xForcesSum, double yForcesSum) {
        this.xForcesSum = xForcesSum;
        this.yForcesSum = yForcesSum;
        pinX = 0;
        pinY = 0;
        rollerY = 0;
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getXForcesSum() {
        return xForcesSum;
    }

    public void setXForcesSum(double xForcesSum) {
        this.xForcesSum = xForcesSum;
    }

    public double getYForcesSum() {
        return yForcesSum;
    }

    public void setYForcesSum(double yForcesSum) {
        this.yForcesSum = yForcesSum;
    }

    public double[] getArrayX() {
        return arrayX;
    }

    public void setArrayX(double[] arrayX) {
        this.arrayX = arrayX;
    }

    public double[] getArrayY() {
        return arrayY;
    }

    public void setArrayY(double[] arrayY) {
        this.arrayY = arrayY;
    }

    @Override
    public String toString() {
        return "Node{" + this.getX() + " " + this.getY() + "\n" +
                "arrayX=" + Arrays.toString(arrayX) + "\", \n" +
                "arrayY=" + Arrays.toString(arrayY) +
                '}';
    }
}