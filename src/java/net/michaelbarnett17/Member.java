package net.michaelbarnett17;

public class Member {

    private int memberId;

    private double x1;
    private double y1;
    private double x2;
    private double y2;

    private double length;

    private double i12;
    private double j12;

    private double i21;
    private double j21;

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getI12() {
        return i12;
    }

    public void setI12(double i12) {
        this.i12 = i12;
    }

    public double getJ12() {
        return j12;
    }

    public void setJ12(double j12) {
        this.j12 = j12;
    }

    public double getI21() {
        return i21;
    }

    public void setI21(double i21) {
        this.i21 = i21;
    }

    public double getJ21() {
        return j21;
    }

    public void setJ21(double j21) {
        this.j21 = j21;
    }



    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public double calculate_i12() {
        i12 = (x2-x1)/calculateLength();
        return i12;
    }

    public double calculate_j12() {
        j12 = (y2-y1)/calculateLength();
        return j12;
    }

    public double calculate_i21() {
        i21 = (x1-x2)/calculateLength();
        return i21;
    }

    public double calculate_j21() {
        j21 = (y1-y2)/calculateLength();
        return j21;
    }

    public double calculateLength() {
        length = Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
        return length;

    }
}
