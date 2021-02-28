package org.example.persistance;

import org.example.domain.Agenda;
import org.example.transfer.AgendaNameRequest;
import org.example.transfer.AgendaRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaRepository {

    public void createAgenda(AgendaRequest request) throws SQLException, IOException, ClassNotFoundException {
        String sql = "INSERT INTO phoneagenda(firstName, lastName, phone) VALUES (?,?,?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());
            preparedStatement.setString(3, request.getPhone());

            preparedStatement.executeUpdate();

        }
    }

    public void updateAgenda(AgendaRequest request, long id) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE phoneagenda SET phone=? WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, request.getPhone());
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
        }
    }

    public void deleteAgenda(long id) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM phoneagenda WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }

    }

    public List<Agenda> getAgenda() throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT id,firstName,lastName,phone FROM phoneagenda";

        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            return getAgenda(resultSet);
        }
    }

    public List<Agenda> getAgendaofName(AgendaNameRequest request) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT id,firstName,lastName,phone FROM phoneagenda WHERE firstName=? OR lastName=?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());

            ResultSet resultSet = preparedStatement.executeQuery();

            return getAgenda(resultSet);
        }
    }

    private List<Agenda> getAgenda(ResultSet resultSet) throws SQLException {
        List<Agenda> agendas = new ArrayList<>();
        while (resultSet.next()) {
            Agenda agenda = new Agenda();
            agenda.setId(resultSet.getLong("id"));
            agenda.setFirstName(resultSet.getString("firstName"));
            agenda.setLastName(resultSet.getString("lastName"));
            agenda.setPhone(resultSet.getString("phone"));

            agendas.add(agenda);


        }
        return agendas;
    }
}