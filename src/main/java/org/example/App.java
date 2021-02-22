package org.example;

import persistance.AgendaRepository;
import transfer.AgendaNameRequest;
import transfer.AgendaRequest;

import java.io.IOException;
import java.sql.SQLException;


public class App {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        AgendaRepository agendaRepository = new AgendaRepository();

//        AgendaRequest request = new AgendaRequest();
//        request.setFirstName("Alina");
//        request.setLastName("Pop");
//        request.setPhone("0722568523");
//
//        agendaRepository.createAgenda(request);
      //  request.setPhone("0722568522");


       // agendaRepository.updateAgenda(request, 1);
      //  agendaRepository.deleteAgenda(1);
//        AgendaNameRequest request= new AgendaNameRequest();
//        agendaRepository.getAgendaofName(request);
    }
}
