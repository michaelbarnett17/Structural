package net.michaelbarnett17;

import java.util.*;
import java.util.regex.Pattern;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.math3.linear.RealVector;

/**
 *
 * @author mike
 */
public class BeamBuilder {
    private Beam beam;
    
    public BeamBuilder() {
        
    }
    
    public BeamBuilder(HttpServletRequest req, HttpServletResponse resp) {
        
    }
    
    public void buildBeam(HttpServletRequest req) {
        beam = new Beam();
                
        beam.getForces().clear();
        beam.getForceDistances().clear();
        beam.getForceAngles().clear();
        
        String myString = req.getParameter("blah");
        myString = myString.replace("\"name\":", "");
        myString = myString.replace(",\"value\":\"", " : ");
        myString = myString.replace("\"}", "}");        
        //System.out.println(myString);
        
        Gson gson = new Gson();
        
        Type listType = new TypeToken<List<HashMap<String, Number>>>(){}.getType();
        List<HashMap<String, Number>> myList = gson.fromJson(myString, listType);
            
        for (Map<String, Number> entry : myList) {
            for (String key : entry.keySet()) {
                 Number value = entry.get(key);
                 assignLength(key, value, beam);
                 assignPinLocation(key, value, beam);
                 assignRollerLocation(key, value, beam);
                 assignLoadLocation(key, value, beam);
                 assignLoadMagnitude(key, value, beam);
                 assignLoadAngle(key,value,beam);
                 System.out.println("key = " + key);
                 System.out.println("value = " + value);              
            }
        }
                
        RealVector solution = beam.solve();

        beam.setPinReactionX   (( solution.getEntry(0)));
        beam.setPinReactionY   (( solution.getEntry(1)));
        beam.setRollerReactionY(( solution.getEntry(2)));
    }
    
    public void displaySolution(HttpServletResponse resp) throws IOException {
                ArrayList<Double> list = new ArrayList<Double>();
        list.add(beam.getPinReactionX());
        list.add(beam.getPinReactionY());
        list.add(beam.getRollerReactionY());
        
        String json = new Gson().toJson(list);
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
        
        System.out.println(beam.getForces());       
    }
    
    public void assignLength(String key, Number value, Beam beam) {
        if(key.equals("length")) {
            beam.setLength(value.doubleValue());
        }
    }
    
    public void assignPinLocation(String key, Number value, Beam beam) {
        if(key.equals("pinLocation")) {
            beam.setPinDistance(value.doubleValue());
        }
    }
    
    public void assignRollerLocation(String key, Number value, Beam beam) {
        if(key.equals("rollerLocation")) {
            beam.setRollerDistance(value.doubleValue());
        }
    }
    
    public void assignLoadLocation(String key, Number value, Beam beam) {
        if(Pattern.matches("loadLocation\\d+", key)) { 
            beam.appendForceDistance(value.doubleValue());
        }
    }
    
    public void assignLoadMagnitude(String key, Number value, Beam beam) {
        if(Pattern.matches("loadMagnitude\\d+", key)) {           
            beam.appendForce(value.doubleValue());
        }
    }
    
    public void assignLoadAngle(String key, Number value, Beam beam) {
        if(Pattern.matches("angle\\d+", key)) {         
            beam.appendForceAngle(value.doubleValue());
        }
    }    
}
