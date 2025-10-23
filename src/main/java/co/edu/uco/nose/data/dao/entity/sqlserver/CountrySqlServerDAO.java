package co.edu.uco.nose.data.dao.entity.sqlserver;

import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.entity.CountryEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Objects;

public final class CountrySqlServerDAO extends SqlConnection implements CountryDAO {
    public CountrySqlServerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<CountryEntity> findAll() {
        final var sql = new StringBuilder();
        sql.append("SELECT p.id, p.nombre ");
        sql.append("FROM Pais AS p ");

        final List<CountryEntity> results = new ArrayList<>();

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString());
             var resultSet = preparedStatement.executeQuery()){

            while(resultSet.next()) {
                results.add(mapResultSetToCountryEntity(resultSet));
            }

        } catch (final Exception exception) {
            handlePersistenceException(exception, "consultar todos los paises");
        }

        return results;
    }

    @Override
    public List<CountryEntity> findByFilter(CountryEntity filterEntity) {
        final var sql = new StringBuilder();
        sql.append("SELECT p.id, p.nombre ");
        sql.append("FROM Pais AS p ");

        final var where = new StringBuilder();
        final List<Object> parameters = new ArrayList<>();

        if(filterEntity.getId() != UUIDHelper.getUUIDHelper().getDefault()){
            where.append("p.id = ? ");
            parameters.add(filterEntity.getId());
        }

        if(!Objects.equals(filterEntity.getName(), TextHelper.getDefault())){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("p.nombre = ? ");
            parameters.add(filterEntity.getName());
        }

        if(!where.isEmpty()){
            sql.append("WHERE ").append(where);
        }

        final List<CountryEntity> results = new ArrayList<>();

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())){

            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            try (var resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    results.add(mapResultSetToCountryEntity(resultSet));
                }
            }
        } catch (final Exception exception) {
            handlePersistenceException(exception, "consultar pais por filtro");
        }

        return results;
    }

    @Override
    public CountryEntity findById(UUID uuid) {
        final var sql = new StringBuilder();
        sql.append("SELECT p.id, p.nombre ");
        sql.append("FROM Pais AS p ");
        sql.append("WHERE p.id = ? ");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())){
            preparedStatement.setObject(1, uuid);

            try (var resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    return mapResultSetToCountryEntity(resultSet);
                }
            }

        } catch (final Exception exception){
            handlePersistenceException(exception, "consultar un pais por su identificador");
        }

        return CountryEntity.getDefaultObject();
    }

    private CountryEntity mapResultSetToCountryEntity(java.sql.ResultSet resultSet) throws SQLException {
        var country = new CountryEntity();
        country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
        country.setName(resultSet.getString("nombre"));
        return country;
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
