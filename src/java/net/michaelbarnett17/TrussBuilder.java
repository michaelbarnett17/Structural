package net.michaelbarnett17;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.math3.linear.RealVector;

/**
 *
 * @author mike
 */
public class TrussBuilder {
    private Truss truss;
    
    public TrussBuilder() {
        
    }
    
    public TrussBuilder(HttpServletRequest req, HttpServletResponse resp) {
        
    }
    
    public void buildTruss(HttpServletRequest req) {
        int memberIndex = 0;
        int forceIndex  = 0;
        
        try {           
            truss = new Truss();
            
            truss.getMembers().clear();
            truss.getForces().clear();
            truss.getNodes().clear();

            String myString = req.getParameter("trussData");
            myString = myString.replace("\"name\":", "");
            myString = myString.replace(",\"value\":\"", " : ");
            myString = myString.replace("\"}", "}");
            
            System.out.println(myString);
            
            Gson gson = new Gson();
        
            Type listType = new TypeToken<List<HashMap<String, Number>>>(){}.getType();
            List<HashMap<String, Number>> myList = gson.fromJson(myString, listType);
            
            //GET DATA BY LOOPING THROUGH LIST
            for (Map<String, Number> entry : myList) {
                for (String key : entry.keySet()) {
                    Number value = entry.get(key);
                    double valueDouble = value.doubleValue();
                    System.out.println(key + " " + value);
                    memberIndex = assignMemberInstanceVariables(truss, key, valueDouble, memberIndex);
                    forceIndex = assignForceInstanceVariables(truss, key, valueDouble, forceIndex);
                    assignSupportInstanceVariables(truss, key, valueDouble);
                }
            }
            truss.setTotalMembers(memberIndex);
            truss.assignValues();
            
        } catch (NullPointerException nullPointerException) {
            System.out.println("first time visting page null form data");
        }
    }
    
    public void displaySolution(HttpServletResponse resp) throws IOException {
        ArrayList<Double> list = new ArrayList<Double>();
        RealVector solution = truss.getSolution();
        
        for(int i=0; i<solution.getDimension(); i++) {
            list.add(solution.getEntry(i));
        }         
        
        String json = new Gson().toJson(list);
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);   
    }
    
    public int assignMemberInstanceVariables(Truss truss, String key, double valueDouble, int memberIndex) {
        if       (Pattern.matches("member\\d{1,2}x1", key)) {
            truss.getMembers().add(memberIndex, new Member());
            truss.getMembers().get(memberIndex).setMemberId(memberIndex + 1);
            truss.getMembers().get(memberIndex).setX1(valueDouble);

        } else if(Pattern.matches("member\\d{1,2}y1", key)) {
            truss.getMembers().get(memberIndex).setY1(valueDouble);

        } else if(Pattern.matches("member\\d{1,2}x2", key)) {
            truss.getMembers().get(memberIndex).setX2(valueDouble);

        } else if(Pattern.matches("member\\d{1,2}y2", key)) {
            truss.getMembers().get(memberIndex).setY2(valueDouble);
            memberIndex++;
        }
        return memberIndex;
    }
    
    public int assignForceInstanceVariables(Truss truss, String key, double valueDouble, int forceIndex) {
        if       (Pattern.matches("force\\d{1,2}fx", key)) {
            truss.getForces().add(forceIndex, new Force());
            truss.getForces().get(forceIndex).setForceId(forceIndex + 1);
            truss.getForces().get(forceIndex).setForceXLocation(valueDouble);
            
        } else if(Pattern.matches("force\\d{1,2}fy", key)) {
            truss.getForces().get(forceIndex).setForceYLocation(valueDouble);
            
        } else if(Pattern.matches("force\\d{1,2}fm", key)) {
            truss.getForces().get(forceIndex).setForceMagnitude(valueDouble);
            
        } else if(Pattern.matches("force\\d{1,2}fa", key)) {
            truss.getForces().get(forceIndex).setForceAngle(valueDouble);
            forceIndex++;
        }
        return forceIndex;
    }
    
    public void assignSupportInstanceVariables(Truss truss, String key, double valueDouble) {
        if       (Pattern.matches("rx", key)) {
            truss.setRollerX(valueDouble);
        } else if(Pattern.matches("ry", key)) {
            truss.setRollerY(valueDouble);
        } else if(Pattern.matches("px", key)) {
            truss.setPinX(valueDouble);
        } else if(Pattern.matches("py", key)) {
            truss.setPinY(valueDouble);
        }
    }
}