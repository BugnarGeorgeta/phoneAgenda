package org.example.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.Agenda;
import org.example.service.AgendaService;
import org.example.transfer.AgendaRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/agendas")
public class AgendaServlet extends HttpServlet {

    private AgendaService agendaService = new AgendaService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AgendaRequest request = new ObjectMapper().readValue(req.getReader(), AgendaRequest.class);

        try {
            agendaService.createAgenda(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error " + e.getMessage());
        }


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AgendaRequest agendaRequest = new ObjectMapper().readValue(req.getReader(), AgendaRequest.class);
        String id = req.getParameter("id");
        try {
            agendaService.updateAgenda(agendaRequest, Long.parseLong(id));
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal error server:" + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            agendaService.deleteAgenda(Long.parseLong(id));
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server error:" + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<Agenda> agendas = agendaService.getAgenda();
            String response = new ObjectMapper().writeValueAsString(agendas);
            resp.getWriter().print(response);

        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error :" + e.getMessage());
        }

    }


}
