package co.edu.uco.nose.data.dao.entity.sqlserver;

import co.edu.uco.nose.data.dao.entity.IdentificationTypeDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.entity.IdentificationTypeEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Objects;

public final class IdentificationTypeSqlServerDAO extends SqlConnection implements IdentificationTypeDAO {
    public IdentificationTypeSqlServerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<IdentificationTypeEntity> findAll() {
        final var sql = new StringBuilder();
        sql.append("SELECT ti.id, ti.nombre ");
        sql.append("FROM TipoIdentificacion AS ti ");

        final List<IdentificationTypeEntity> results = new ArrayList<>();

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString());
             var resultSet = preparedStatement.executeQuery()){

            while(resultSet.next()) {
                results.add(mapResultSetToIdentificationTypeEntity(resultSet));
            }

        } catch (final Exception exception) {
            handlePersistenceException(exception, "consultar todos los tipos de identificacion");
        }

        return results;
    }

    @Override
    public List<IdentificationTypeEntity> findByFilter(IdentificationTypeEntity filterEntity) {
        final var sql = new StringBuilder();
        sql.append("SELECT ti.id, ti.nombre ");
        sql.append("FROM TipoIdentificacion AS ti ");

        final var where = new StringBuilder();
        final List<Object> parameters = new ArrayList<>();

        if(filterEntity.getId() != UUIDHelper.getUUIDHelper().getDefault()){
            where.append("ti.id = ? ");
            parameters.add(filterEntity.getId());
        }

        if(!Objects.equals(filterEntity.getName(), TextHelper.getDefault())){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("ti.nombre = ? ");
            parameters.add(filterEntity.getName());
        }

        if(!where.isEmpty()){
            sql.append("WHERE ").append(where);
        }

        final List<IdentificationTypeEntity> results = new ArrayList<>();

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())){

            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            try (var resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    results.add(mapResultSetToIdentificationTypeEntity(resultSet));
                }
            }
        } catch (final Exception exception) {
            handlePersistenceException(exception, "consultar tipo de identificacion por filtro");
        }

        return results;
    }

    @Override
    public IdentificationTypeEntity findById(UUID uuid) {
        final var sql = new StringBuilder();
        sql.append("SELECT ti.id, ti.nombre ");
        sql.append("FROM TipoIdentificacion AS ti ");
        sql.append("WHERE ti.id = ? ");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())){
            preparedStatement.setObject(1, uuid);

            try (var resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    return mapResultSetToIdentificationTypeEntity(resultSet);
                }
            }

        } catch (final Exception exception){
            handlePersistenceException(exception, "consultar un tipo de identificacion por su identificador");
        }

        return new IdentificationTypeEntity();
    }

    private IdentificationTypeEntity mapResultSetToIdentificationTypeEntity(java.sql.ResultSet resultSet) throws SQLException {
        var type = new IdentificationTypeEntity();
        type.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
        type.setName(resultSet.getString("nombre"));
        return type;
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
