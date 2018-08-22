package net.michaelbarnett17;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Beam Servlet
 * @author mbarnett
 */
@WebServlet(
        urlPatterns = {"/beamServlet"}
)

public class BeamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/beam.jsp");
        dispatcher.forward(req, resp);
    }        
}
