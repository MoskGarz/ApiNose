package co.edu.uco.nose.data.dao.entity.sqlserver;

import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.StateDAO;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.StateEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Objects;

public final class StateSqlServerDAO extends SqlConnection implements StateDAO {
    public StateSqlServerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<StateEntity> findAll() {
        final var sql = new StringBuilder();
        sql.append("SELECT  d.id, d.nombre, ");
        sql.append("        p.id AS idPais, p.nombre AS nombrePais ");
        sql.append("FROM    Departamento AS d ");
        sql.append("INNER JOIN Pais AS p ON p.id = d.pais ");

        final List<StateEntity> results = new ArrayList<>();

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString());
             var resultSet = preparedStatement.executeQuery()){

            while(resultSet.next()) {
                results.add(mapResultSetToStateEntity(resultSet));
            }

        } catch (final Exception exception) {
            handlePersistenceException(exception, "consultar todos los departamentos");
        }

        return results;
    }

    @Override
    public List<StateEntity> findByFilter(StateEntity filterEntity) {
        final var sql = new StringBuilder();
        sql.append("SELECT  d.id, d.nombre, ");
        sql.append("        p.id AS idPais, p.nombre AS nombrePais ");
        sql.append("FROM    Departamento AS d ");
        sql.append("INNER JOIN Pais AS p ON p.id = d.pais ");

        final var where = new StringBuilder();
        final List<Object> parameters = new ArrayList<>();

        if(filterEntity.getId() != UUIDHelper.getUUIDHelper().getDefault()){
            where.append("d.id = ? ");
            parameters.add(filterEntity.getId());
        }

        if(!Objects.equals(filterEntity.getName(), TextHelper.getDefault())){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("d.nombre = ? ");
            parameters.add(filterEntity.getName());
        }

        if(filterEntity.getCountry().getId() != UUIDHelper.getUUIDHelper().getDefault()){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("p.id = ? ");
            parameters.add(filterEntity.getCountry().getId());
        }

        if(!Objects.equals(filterEntity.getCountry().getName(), TextHelper.getDefault())){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("p.nombre = ? ");
            parameters.add(filterEntity.getCountry().getName());
        }

        if(!where.isEmpty()){
            sql.append("WHERE ").append(where);
        }

        final List<StateEntity> results = new ArrayList<>();

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())){

            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            try (var resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    results.add(mapResultSetToStateEntity(resultSet));
                }
            }
        } catch (final Exception exception) {
            handlePersistenceException(exception, "consultar departamento por filtro");
        }

        return results;
    }

    @Override
    public StateEntity findById(UUID uuid) {
        final var sql = new StringBuilder();
        sql.append("SELECT  d.id, d.nombre, ");
        sql.append("        p.id AS idPais, p.nombre AS nombrePais ");
        sql.append("FROM    Departamento AS d ");
        sql.append("INNER JOIN Pais AS p ON p.id = d.pais ");
        sql.append("WHERE   d.id = ? ");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())){
            preparedStatement.setObject(1, uuid);

            try (var resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    return mapResultSetToStateEntity(resultSet);
                }
            }

        } catch (final Exception exception){
            handlePersistenceException(exception, "consultar un departamento por su identificador");
        }

        return new StateEntity();
    }

    private StateEntity mapResultSetToStateEntity(java.sql.ResultSet resultSet) throws SQLException {
        var country = new CountryEntity();
        country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPais")));
        country.setName(resultSet.getString("nombrePais"));

        var state = new StateEntity();
        state.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
        state.setName(resultSet.getString("nombre"));
        state.setCountry(country);
        return state;
    }

    private void handlePersistenceException(final Exception exception, final String operation){

        MessagesEnum userBaseMessage;
        MessagesEnum technicalBaseMessage;

        if (exception instanceof SQLException) {
            userBaseMessage = MessagesEnum.USER_ERROR_PERSISTENCE_SQL;
            technicalBaseMessage = MessagesEnum.TECHNICAL_ERROR_PERSISTENCE_SQL;
        }
        else {
            userBaseMessage = MessagesEnum.USER_ERROR_PERSISTENCE_UNEXPECTED;
            technicalBaseMessage = MessagesEnum.TECHNICAL_ERROR_PERSISTENCE_UNEXPECTED;
        }

        var userMessage = userBaseMessage.getContent();
        var technicalMessage = technicalBaseMessage.getContent().replace("la operaci��n", operation);

        throw NoseException.create(exception, userMessage, technicalMessage);
    }
}
