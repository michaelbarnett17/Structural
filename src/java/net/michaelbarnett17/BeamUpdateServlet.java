package net.michaelbarnett17;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Beam Servlet
 * @author mbarnett
 */
@WebServlet(
        urlPatterns = {"/beamUpdateServlet"}
)

public class BeamUpdateServlet extends HttpServlet {
    private BeamBuilder beamBuilder = new BeamBuilder();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        beamBuilder.buildBeam(req);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        beamBuilder.displaySolution(resp);
    }   
}
