package net.michaelbarnett17;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/trussUpdateServlet"}
)

public class TrussUpdateServlet extends HttpServlet {
    TrussBuilder trussBuilder = new TrussBuilder();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        trussBuilder.buildTruss(req);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        trussBuilder.displaySolution(resp);
    }    
}



