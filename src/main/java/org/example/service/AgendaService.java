package org.example.service;

import org.example.domain.Agenda;
import org.example.persistance.AgendaRepository;
import org.example.transfer.AgendaNameRequest;
import org.example.transfer.AgendaRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AgendaService {

    private AgendaRepository agendaRepository = new AgendaRepository();


    public void createAgenda(AgendaRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating agenda:" + request);
        agendaRepository.createAgenda(request);
    }

    public void updateAgenda(AgendaRequest request, long id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Updating agenda: " + request + id);
        agendaRepository.updateAgenda(request, id);
    }

    public void deleteAgenda(long id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting agenda :" + id);
        agendaRepository.deleteAgenda(id);
    }

    public List<Agenda> getAgenda() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retrieving all agenda.");
        return agendaRepository.getAgenda();
    }

    public List<Agenda> getAgendaofName(AgendaNameRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retrieving  agenda: " + request);
        return agendaRepository.getAgendaofName(request);
    }


}
