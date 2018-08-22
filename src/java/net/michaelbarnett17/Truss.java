package net.michaelbarnett17;

import java.util.ArrayList;

import org.apache.commons.math3.linear.*;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Truss {

    private RealVector solution;
    private ArrayList<Member> members = new ArrayList<Member>();
    private ArrayList<Force> forces   = new ArrayList<Force>();
    private ArrayList<Node> nodes     = new ArrayList<Node>();
        
    private double pinX;
    private double pinY;
    private double rollerX;
    private double rollerY;
    
    private int memberIdCount = 1;
    private int totalMembers;

    public Truss() {
        solution = null;
    }

    public void assignValues() {
        double[][] leftHandSideOfEquation;
        double[]   rightHandSideOfEquation;
        
        processMembersAndCreateNodes();
                     
        putRollerYIntoNodeArray(rollerX, rollerY, nodes);
        putPinXIntoNodeArray(pinX, pinY, nodes);
        putPinYIntoNodeArray(pinX, pinY, nodes);
       
        System.out.println("Node Strings");
        for (Node node : nodes) {
            System.out.println("Nodes:" + node.toString());                   
        }
        
        leftHandSideOfEquation  = convertArrayListOfNodesToPrimitiveDoublesArray();
        rightHandSideOfEquation = matchForcesWithNodes();
        
        System.out.println("\n");
        System.out.println("Forces Array");
        for(double something: rightHandSideOfEquation){
            System.out.println(something);
        }
        
        solution = solveTruss(leftHandSideOfEquation, rightHandSideOfEquation);
        
            for(int i=0; i<solution.getDimension(); i++) {
                System.out.println("Solution index " + i + " " + solution.getEntry(i));
            } 
    }

    public void addNewNodesToEmptyNodeList(double x1, double x2, double y1, double y2, double I1, double I2, double J1, double J2, int memberLocationInArray, ArrayList<Node> nodes) {
        // Node 1
        Node node1 = new Node(x1, y1, I1, J1);
        
        node1.setArrayX(new double [totalMembers + 3]);
        node1.setArrayY(new double [totalMembers + 3]);
                
        double[] array1X = node1.getArrayX();
        array1X[memberLocationInArray] = I1;
        node1.setArrayX(array1X);

        double[] array1Y = node1.getArrayY();
        array1Y[memberLocationInArray] = J1;
        node1.setArrayY(array1Y);

        nodes.add(node1);

        // Node 2
        Node node2 = new Node(x2, y2, I2, J2);
        node2.setArrayX(new double [totalMembers + 3]);
        node2.setArrayY(new double [totalMembers + 3]);

        double[] array2X = node2.getArrayX();
        array2X[memberLocationInArray] = I2;
        node2.setArrayX(array2X);

        double[] array2Y = node2.getArrayY();
        array2Y[memberLocationInArray] = J2;
        node2.setArrayY(array2Y);

        nodes.add(node2);
    }

    public void addUniqueNode1ToList(double x1, double y1, double I1, double J1, int memberLocationInArray, ArrayList<Node> listOfNodes) {               
        Node node1 = new Node(x1, y1, I1, J1);
        
        node1.setArrayX(new double [totalMembers + 3]);
        node1.setArrayY(new double [totalMembers + 3]);

        double[] array1X = node1.getArrayX();
        array1X[memberLocationInArray] = I1;
        node1.setArrayX(array1X);

        double[] array1Y = node1.getArrayY();
        array1Y[memberLocationInArray] = J1;
        node1.setArrayY(array1Y);

        listOfNodes.add(node1);
    }

    public void addUniqueNode2ToList(double x2, double y2, double I2, double J2, int memberLocationInArray, ArrayList<Node> listOfNodes) {
        Node node2 = new Node(x2, y2, I2, J2);
        
        node2.setArrayX(new double [totalMembers + 3]);
        node2.setArrayY(new double [totalMembers + 3]);
        
        double[] array2X = node2.getArrayX();
        array2X[memberLocationInArray] = I2;
        node2.setArrayX(array2X);

        double[] array2Y = node2.getArrayY();
        array2Y[memberLocationInArray] = J2;
        node2.setArrayY(array2Y);

        listOfNodes.add(node2);
    }

    public boolean compareNode1ToListOfNodes(double x1, double y1, double I1, double J1, int memberLocationInArray, ArrayList<Node> listOfNodes, boolean match1) {

        for (Node element : listOfNodes) {
            if (element.getX() == x1 && element.getY() == y1) {
                match1 = true;

                double[] array1X = element.getArrayX();
                array1X[memberLocationInArray] = I1;
                element.setArrayX(array1X);

                double[] array1Y = element.getArrayY();
                array1Y[memberLocationInArray] = J1;
                element.setArrayY(array1Y);

                break;
            } else {
                match1 = false;
            }
        }
        return match1;
    }

    public boolean compareNode2ToListOfNodes(double x2, double y2, double I2, double J2, int memberLocationInArray, ArrayList<Node> listOfNodes, boolean match2) {

        for (Node element : listOfNodes) {
            if (element.getX() == x2 && element.getY() == y2) {
                match2 = true;

                double[] array2X = element.getArrayX();
                array2X[memberLocationInArray] = I2;
                element.setArrayX(array2X);

                double[] array2Y = element.getArrayY();
                array2Y[memberLocationInArray] = J2;
                element.setArrayY(array2Y);

                break;
            } else {
                match2 = false;
            }
        }
        return match2;
    }

    public void putRollerYIntoNodeArray(double rx, double ry, ArrayList<Node> nodes) {

        for (Node element : nodes) {
            if (element.getX() == rx && element.getY() == ry) {

                double[] arrayY = element.getArrayY();

                // Roller Y always 3rd from the end
                int locationInArray = arrayY.length - 3;
                arrayY[locationInArray] = 1;
                element.setArrayY(arrayY);
            }
        }
    }

    public void putPinXIntoNodeArray(double px, double py, ArrayList<Node> nodes) {

        for (Node element : nodes) {
            if (element.getX() == px && element.getY() == py) {

                double[] arrayX = element.getArrayX();

                // Pin X always 2nd from the end
                int locationInArray = arrayX.length - 2;
                arrayX[locationInArray] = 1;
                element.setArrayX(arrayX);
            }
        }
    }

    public void putPinYIntoNodeArray(double px, double py, ArrayList<Node> nodes) {

        for (Node element : nodes) {
            if (element.getX() == px && element.getY() == py) {

                double[] arrayY = element.getArrayY();

                // Pin Y always last in array
                int locationInArray = arrayY.length - 1;
                arrayY[locationInArray] = 1;
                element.setArrayY(arrayY);
            }
        }
    }
    
    //LEFT HAND SIDE OF EQUATION
    // It takes arrayX and arrayY from  each node (arrayX and arrayY already has member and support information) and puts into Primitive Double Array
    public double[][] convertArrayListOfNodesToPrimitiveDoublesArray() {
        // nodes.(size) = length number of nodes    nodes.get(1).getArrayX
        // Splitting Array of Nodes (with array X and array Y) in primitive array, will be twice as big + 1 more index
        double nodesPrimitive[][] = new double [nodes.size() * 2][nodes.get(1).getArrayX().length];
        
        for(int i=0; i < nodes.size(); i++) {
            
            double arrayX[] = nodes.get(i).getArrayX();
            double arrayY[] = nodes.get(i).getArrayY();
            
           // Splitting Array of Nodes (with array X and array Y) in primitive array, will be twice as big + one more index
            nodesPrimitive[i * 2] = arrayX;
            nodesPrimitive[i * 2 + 1] = arrayY;          
        }
        
        System.out.println("\n");
        System.out.println("Primitive Array Of Nodes");
        for(int i = 0; i < nodesPrimitive[0].length; i++)
        {
           for(int j = 0; j < members.size() + 3; j++)
           {
              System.out.print(nodesPrimitive[i][j] + "\t");
           }
           System.out.println();
        }
        
        return nodesPrimitive;
    }
    
    public void processMembersAndCreateNodes() {
        for (Member member : members) {
            int memberLocationInArray = memberIdCount - 1;

            member.setI12(member.calculate_i12());
            member.setJ12(member.calculate_j12());
            member.setI21(member.calculate_i21());
            member.setJ21(member.calculate_j21());

            double x1 = member.getX1();
            double y1 = member.getY1();

            double x2 = member.getX2();
            double y2 = member.getY2();

            double I1 = member.getI12();
            double J1 = member.getJ12();

            double I2 = member.getI21();
            double J2 = member.getJ21();

            boolean match1 = false;
            boolean match2 = false;

            if (nodes.isEmpty()) {

                addNewNodesToEmptyNodeList(x1, x2, y1, y2, I1, I2, J1, J2, memberLocationInArray, nodes);

            } else {

                match1 = compareNode1ToListOfNodes(x1, y1, I1, J1, memberLocationInArray, nodes, match1);
                match2 = compareNode2ToListOfNodes(x2, y2, I2, J2, memberLocationInArray, nodes, match2);

                if (!match1) {
                    addUniqueNode1ToList(x1, y1, I1, J1, memberLocationInArray, nodes);
                }

                if (!match2) {
                    addUniqueNode2ToList(x2, y2, I2, J2, memberLocationInArray, nodes);
                }
            }
            memberIdCount++;
        }
    }
    
    //Right Hand Side of Equation
    public double[] matchForcesWithNodes() {
        double forceArray[] = new double[nodes.size() * 2];
        
        for(Force force: forces) {
            double forceAngleRadians = force.getForceAngle()*PI/180;
            double forceMagnitude    = force.getForceMagnitude();
            double forceComponentX   = forceMagnitude*sin(forceAngleRadians);
            double forceComponentY   = forceMagnitude*cos(forceAngleRadians);
            for(int i=0; i < nodes.size(); i++) {
                double nodeLocationX = nodes.get(i).getX();
                double nodeLocationY = nodes.get(i).getY();

                // The truss's force X and Y locations
                double forceLocationX = force.getForceXLocation();
                double forceLocationY = force.getForceYLocation();

                forceArray = checkIfForceLocatedOnNodeAndAssignForceToForceArray(nodeLocationX, forceLocationX,
                        nodeLocationY, forceLocationY, forceArray, i, forceComponentX, forceComponentY);
            }
        }   
        return forceArray;
    }
    
    public RealVector solveTruss(double[][] leftHandSideOfEquation, double[] rightHandSideOfEquation) {

        RealMatrix coefficients =
                new Array2DRowRealMatrix(leftHandSideOfEquation, false);
        DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();

        /////////////////////////////ATTENTION///////////////////////////
        RealVector constants = new ArrayRealVector(rightHandSideOfEquation, false);
        RealVector solution = solver.solve(constants);
        
        return solution;
    }
    
    public int calculateNumberOfMembers(ArrayList<Member> members) {
        return members.size();        
    }
    
    public double[] checkIfForceLocatedOnNodeAndAssignForceToForceArray(double nodeLocationX, double forceLocationX,
            double nodeLocationY, double forceLocationY, double[] forceArray, int i, double forceComponentX, double forceComponentY) {
        if(nodeLocationX == forceLocationX && nodeLocationY == forceLocationY) {
            //System.out.println("Match at: " + nodeLocationX + " " + nodeLocationY);
            //Remember forceArray is twice as big so X index is 2*i, Y index is 2*i
            forceArray[2*i]     = forceArray[2*i]     - forceComponentX;
            forceArray[2*i + 1] = forceArray[2*i + 1] - forceComponentY;                 
        }
        return forceArray;
    }
    
    // GETTERS AND SETTERS BELOW THIS POINT
    public RealVector getSolution() {
        return solution;
    }

    public void setSolution(RealVector solution) {
        this.solution = solution;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public ArrayList<Force> getForces() {
        return forces;
    }

    public void setForces(ArrayList<Force> forces) {
        this.forces = forces;
    }
    
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public double getPinX() {
        return pinX;
    }

    public void setPinX(double pinX) {
        this.pinX = pinX;
    }

    public double getPinY() {
        return pinY;
    }

    public void setPinY(double pinY) {
        this.pinY = pinY;
    }

    public double getRollerX() {
        return rollerX;
    }

    public void setRollerX(double rollerX) {
        this.rollerX = rollerX;
    }

    public double getRollerY() {
        return rollerY;
    }

    public void setRollerY(double rollerY) {
        this.rollerY = rollerY;
    }

    public int getMemberIdCount() {
        return memberIdCount;
    }

    public void setMemberIdCount(int memberIdCount) {
        this.memberIdCount = memberIdCount;
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }
}