package co.edu.uco.nose.data.dao.entity.sqlserver;

import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.StateEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Objects;

public final class CitySqlServerDAO extends SqlConnection implements CityDAO {

    public CitySqlServerDAO(final Connection connection) {
        super(connection);
    }
    @Override
    public List<CityEntity> findAll() {
        final var sql = new StringBuilder();
        sql.append("SELECT  c.id, c.nombre, ");
        sql.append("        d.id AS idDepartamento, d.nombre AS nombreDepartamento, ");
        sql.append("        p.id AS idPais, p.nombre AS nombrePais ");
        sql.append("FROM    Ciudad AS c ");
        sql.append("INNER JOIN    Departamento AS d ON d.id = c.departamento ");
        sql.append("INNER JOIN    Pais AS p ON p.id = d.pais ");

        final List<CityEntity> results = new ArrayList<>();

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString());
             var resultSet = preparedStatement.executeQuery()){

            while(resultSet.next()) {
                results.add(mapResultSetToCityEntity(resultSet));
            }

        } catch (final Exception exception) {
            handlePersistenceException(exception, "consultar todas las ciudades");
        }

        return results;
    }

    @Override
    public List<CityEntity> findByFilter(CityEntity filterEntity) {
        final var sql = new StringBuilder();
        sql.append("SELECT  c.id, c.nombre, ");
        sql.append("        d.id AS idDepartamento, d.nombre AS nombreDepartamento, ");
        sql.append("        p.id AS idPais, p.nombre AS nombrePais ");
        sql.append("FROM    Ciudad AS c ");
        sql.append("INNER JOIN    Departamento AS d ON d.id = c.departamento ");
        sql.append("INNER JOIN    Pais AS p ON p.id = d.pais ");

        final var where = new StringBuilder();
        final List<Object> parameters = new ArrayList<>();

        if(filterEntity.getId() != UUIDHelper.getUUIDHelper().getDefault()){
            where.append("c.id = ? ");
            parameters.add(filterEntity.getId());
        }

        if(!Objects.equals(filterEntity.getName(), TextHelper.getDefault())){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("c.nombre = ? ");
            parameters.add(filterEntity.getName());
        }

        if(filterEntity.getState().getId() != UUIDHelper.getUUIDHelper().getDefault()){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("d.id = ? ");
            parameters.add(filterEntity.getState().getId());
        }

        if(!Objects.equals(filterEntity.getState().getName(), TextHelper.getDefault())){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("d.nombre = ? ");
            parameters.add(filterEntity.getState().getName());
        }

        if(filterEntity.getState().getCountry().getId() != UUIDHelper.getUUIDHelper().getDefault()){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("p.id = ? ");
            parameters.add(filterEntity.getState().getCountry().getId());
        }

        if(!Objects.equals(filterEntity.getState().getCountry().getName(), TextHelper.getDefault())){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("p.nombre = ? ");
            parameters.add(filterEntity.getState().getCountry().getName());
        }

        if(!where.isEmpty()){
            sql.append("WHERE ").append(where);
        }

        final List<CityEntity> results = new ArrayList<>();

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())){

            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            try (var resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    results.add(mapResultSetToCityEntity(resultSet));
                }
            }
        } catch (final Exception exception) {
            handlePersistenceException(exception, "consultar ciudad por filtro");
        }

        return results;
    }

    @Override
    public CityEntity findById(UUID uuid) {
        final var sql = new StringBuilder();
        sql.append("SELECT  c.id, c.nombre, ");
        sql.append("        d.id AS idDepartamento, d.nombre AS nombreDepartamento, ");
        sql.append("        p.id AS idPais, p.nombre AS nombrePais ");
        sql.append("FROM    Ciudad AS c ");
        sql.append("INNER JOIN    Departamento AS d ON d.id = c.departamento ");
        sql.append("INNER JOIN    Pais AS p ON p.id = d.pais ");
        sql.append("WHERE   c.id = ? ");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())){
            preparedStatement.setObject(1, uuid);

            try (var resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    return mapResultSetToCityEntity(resultSet);
                }
            }

        } catch (final Exception exception){
            handlePersistenceException(exception, "consultar una ciudad por su identificador");
        }

        return new CityEntity();
    }

    private CityEntity mapResultSetToCityEntity(java.sql.ResultSet resultSet) throws SQLException {
        var country = new CountryEntity();
        country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPais")));
        country.setName(resultSet.getString("nombrePais"));

        var state = new StateEntity();
        state.setCountry(country);
        state.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idDepartamento")));
        state.setName(resultSet.getString("nombreDepartamento"));

        var city = new CityEntity();
        city.setState(state);
        city.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
        city.setName(resultSet.getString("nombre"));

        return city;
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
