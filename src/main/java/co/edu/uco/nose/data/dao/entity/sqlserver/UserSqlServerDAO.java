package co.edu.uco.nose.data.dao.entity.sqlserver;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.SQLConnectionHelper;
import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.IdentificationTypeEntity;
import co.edu.uco.nose.entity.StateEntity;
import co.edu.uco.nose.entity.UserEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class UserSqlServerDAO extends SqlConnection implements UserDAO {

    public UserSqlServerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(final UserEntity entity) {

        SQLConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("INSERT INTO Usuario(id, tipoIdentificacion, numeroIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, ciudadResidencia, correoElectronico, numeroTelefonoMovil, correoElectronicoConfirmado, numeroTelefonoMovilConfirmado) ");
        sql.append("SELECT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())){
            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setObject(2, entity.getIdentificationType().getId());
            preparedStatement.setString(3, entity.getIdentificationNumber());
            preparedStatement.setString(4, entity.getFirstName());
            preparedStatement.setString(5, entity.getSecondName());
            preparedStatement.setString(6, entity.getFirstLastname());
            preparedStatement.setString(7, entity.getSecondLastname());
            preparedStatement.setObject(8, entity.getCity().getId());
            preparedStatement.setString(9, entity.getEmail());
            preparedStatement.setString(10, entity.getPhoneNumber());
            preparedStatement.setBoolean(11, entity.getEmailVerified());
            preparedStatement.setBoolean(12, entity.getPhoneNumberVerified());

            preparedStatement.executeUpdate();

        } catch (final Exception exception) {
            handlePersistenceException(exception, "'crear un nuevo usuario'");
        }
    }

    @Override
    public void delete(UUID uuid) {

        SQLConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("DELETE FROM Usuario ");
        sql.append("WHERE id = ?");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())){
            preparedStatement.setObject(1, uuid);
            preparedStatement.executeUpdate();

        } catch (final Exception exception) {
            handlePersistenceException(exception, "'eliminar un usuario'");
        }

    }

    @Override
    public List<UserEntity> findAll() {

        final var sql = new StringBuilder();
        sql.append("SELECT  u.id,");
        sql.append("        ti.id AS idTipoIdentificacion,");
        sql.append("        ti.nombre AS nombreTipoIdentificacion,");
        sql.append("        u.numeroIdentificacion,");
        sql.append("        u.primerNombre,");
        sql.append("        u.segundoNombre,");
        sql.append("        u.primerApellido,");
        sql.append("        u.segundoApellido,");
        sql.append("        c.id AS idCiudadResidencia,");
        sql.append("        c.nombre AS nombreCiudadResidencia,");
        sql.append("        d.id AS idDepartamentoCiudadResidencia,");
        sql.append("        d.nombre AS nombreDepartamentoCiudadResidencia,");
        sql.append("        p.id AS idPaisDepartamentoCiudadResidencia,");
        sql.append("        p.nombre AS nombrePaisDepartamentoCiudadResidencia,");
        sql.append("        u.correoElectronico,");
        sql.append("        u.numeroTelefonoMovil,");
        sql.append("        u.correoElectronicoConfirmado,");
        sql.append("        u.numeroTelefonoMovilConfirmado ");
        sql.append("FROM    Usuario AS u ");
        sql.append("LEFT JOIN    TipoIdentificacion AS ti ON ti.id = u.tipoIdentificacion ");
        sql.append("LEFT JOIN    Ciudad AS c ON c.id = u.ciudadResidencia ");
        sql.append("LEFT JOIN    Departamento AS d ON d.id = c.departamento ");
        sql.append("LEFT JOIN    Pais AS p ON p.id = d.pais ");

        final List<UserEntity> results = new ArrayList<>();

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString());
             var resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    results.add(mapResultSetToUserEntity(resultSet));
                }

            } catch (final Exception exception){
                handlePersistenceException(exception, "consultar todos los usuarios");
            }

            return results;
    }

    @Override
    public List<UserEntity> findByFilter(UserEntity filterEntity) {

        final var sql = new StringBuilder();
        
        sql.append("SELECT  u.id,");
        sql.append("        ti.id AS idTipoIdentificacion,");
        sql.append("        ti.nombre AS nombreTipoIdentificacion,");
        sql.append("        u.numeroIdentificacion,");
        sql.append("        u.primerNombre,");
        sql.append("        u.segundoNombre,");
        sql.append("        u.primerApellido,");
        sql.append("        u.segundoApellido,");
        sql.append("        c.id AS idCiudadResidencia,");
        sql.append("        c.nombre AS nombreCiudadResidencia,");
        sql.append("        d.id AS idDepartamentoCiudadResidencia,");
        sql.append("        d.nombre AS nombreDepartamentoCiudadResidencia,");
        sql.append("        p.id AS idPaisDepartamentoCiudadResidencia,");
        sql.append("        p.nombre AS nombrePaisDepartamentoCiudadResidencia,");
        sql.append("        u.correoElectronico,");
        sql.append("        u.numeroTelefonoMovil,");
        sql.append("        u.correoElectronicoConfirmado,");
        sql.append("        u.numeroTelefonoMovilConfirmado ");
        sql.append("FROM    Usuario AS u ");
        sql.append("LEFT JOIN    TipoIdentificacion AS ti ON ti.id = u.tipoIdentificacion ");
        sql.append("LEFT JOIN    Ciudad AS c ON c.id = u.ciudadResidencia ");
        sql.append("LEFT JOIN    Departamento AS d ON d.id = c.departamento ");
        sql.append("LEFT JOIN    Pais AS p ON p.id = d.pais ");

        final var where = new StringBuilder();
        final List<Object> parameters = new ArrayList<>();

        if(!filterEntity.getId().toString().isEmpty() && filterEntity.getId() != UUIDHelper.getUUIDHelper().getDefault()){
            where.append("u.id = ? ");
            parameters.add(filterEntity.getId());
        }

        if(!TextHelper.getDefaultWithTrim(filterEntity.getFirstName()).isEmpty()){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("u.primerNombre = ? ");
            parameters.add(filterEntity.getFirstName());
        }

        // Tipo de identificación (id)
        if(filterEntity.getIdentificationType() != null &&
                filterEntity.getIdentificationType().getId() != UUIDHelper.getUUIDHelper().getDefault()){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("ti.id = ? ");
            parameters.add(filterEntity.getIdentificationType().getId());
        }

        // Número de identificación
        if(!TextHelper.getDefaultWithTrim(filterEntity.getIdentificationNumber()).isEmpty()){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("u.numeroIdentificacion = ? ");
            parameters.add(filterEntity.getIdentificationNumber());
        }

        // Segundo nombre
        if(!TextHelper.getDefaultWithTrim(filterEntity.getSecondName()).isEmpty()){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("u.segundoNombre = ? ");
            parameters.add(filterEntity.getSecondName());
        }

        // Apellidos
        if(!TextHelper.getDefaultWithTrim(filterEntity.getFirstLastname()).isEmpty()){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("u.primerApellido = ? ");
            parameters.add(filterEntity.getFirstLastname());
        }

        if(!TextHelper.getDefaultWithTrim(filterEntity.getSecondLastname()).isEmpty()){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("u.segundoApellido = ? ");
            parameters.add(filterEntity.getSecondLastname());
        }

        // Ciudad (id)
        if(filterEntity.getCity() != null &&
                filterEntity.getCity().getId() != UUIDHelper.getUUIDHelper().getDefault()){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("c.id = ? ");
            parameters.add(filterEntity.getCity().getId());
        }

        // Correo electrónico
        if(!TextHelper.getDefaultWithTrim(filterEntity.getEmail()).isEmpty()){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("u.correoElectronico = ? ");
            parameters.add(filterEntity.getEmail());
        }

        // Número de teléfono móvil
        if(!TextHelper.getDefaultWithTrim(filterEntity.getPhoneNumber()).isEmpty()){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("u.numeroTelefonoMovil = ? ");
            parameters.add(filterEntity.getPhoneNumber());
        }

        // Confirmaciones (solo si el valor fue establecido explícitamente)
        if(filterEntity.getIsEmailVerifiedDefaultValue() != null && !filterEntity.getIsEmailVerifiedDefaultValue()){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("u.correoElectronicoConfirmado = ? ");
            parameters.add(filterEntity.getEmailVerified());
        }

        if(filterEntity.getIsPhoneNumberVerifiedDefaultValue() != null && !filterEntity.getIsPhoneNumberVerifiedDefaultValue()){
            if(where.length() > 0){
                where.append("AND ");
            }
            where.append("u.numeroTelefonoMovilConfirmado = ? ");
            parameters.add(filterEntity.getPhoneNumberVerified());
        }

        if (where.length() > 0) {
            sql.append("WHERE ").append(where);
        }

        final List<UserEntity> results = new ArrayList<>();

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())){

            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            try (var resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    results.add(mapResultSetToUserEntity(resultSet));
                }
            }
        } catch (final Exception exception) {
            handlePersistenceException(exception, "consultar usuario por filtro");
        }

        return results;

    }

    @Override
    public UserEntity findById(UUID uuid) {

        final var sql = new StringBuilder();
        sql.append("SELECT  u.id,");
        sql.append("        ti.id AS idTipoIdentificacion,");
        sql.append("        ti.nombre AS nombreTipoIdentificacion,");
        sql.append("        u.numeroIdentificacion,");
        sql.append("        u.primerNombre,");
        sql.append("        u.segundoNombre,");
        sql.append("        u.primerApellido,");
        sql.append("        u.segundoApellido,");
        sql.append("        c.id AS idCiudadResidencia,");
        sql.append("        c.nombre AS nombreCiudadResidencia,");
        sql.append("        d.id AS idDepartamentoCiudadResidencia,");
        sql.append("        d.nombre AS nombreDepartamentoCiudadResidencia,");
        sql.append("        p.id AS idPaisDepartamentoCiudadResidencia,");
        sql.append("        p.nombre AS nombrePaisDepartamentoCiudadResidencia,");
        sql.append("        u.correoElectronico,");
        sql.append("        u.numeroTelefonoMovil,");
        sql.append("        u.correoElectronicoConfirmado,");
        sql.append("        u.numeroTelefonoMovilConfirmado ");
        sql.append("FROM    Usuario AS u ");
        sql.append("LEFT JOIN    TipoIdentificacion AS ti ON ti.id = u.tipoIdentificacion ");
        sql.append("LEFT JOIN    Ciudad AS c ON c.id = u.ciudadResidencia ");
        sql.append("LEFT JOIN    Departamento AS d ON d.id = c.departamento ");
        sql.append("LEFT JOIN    Pais AS p ON p.id = d.pais ");
        sql.append("WHERE   u.id = ? ");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())){
            preparedStatement.setObject(1, uuid);
            try (var resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    return mapResultSetToUserEntity(resultSet);
                }
            }
        } catch (final Exception exception){
            handlePersistenceException(exception, "consultar un usuario por su identificador");
        }

        return UserEntity.getDefaultObject();
    }

    @Override
    public void update(UserEntity entity) {

        SQLConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE Usuario SET tipoIdentificacion = ?, numeroIdentificacion = ?, primerNombre = ?, segundoNombre = ?, primerApellido = ?, segundoApellido = ?, ciudadResidencia = ?, correoElectronico = ?, numeroTelefonoMovil = ?, correoElectronicoConfirmado = ?, numeroTelefonoMovilConfirmado = ? ");
        sql.append("WHERE id = ?");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())){
            preparedStatement.setObject(1, entity.getIdentificationType().getId());
            preparedStatement.setString(2, entity.getIdentificationNumber());
            preparedStatement.setString(3, entity.getFirstName());
            preparedStatement.setString(4, entity.getSecondName());
            preparedStatement.setString(5, entity.getFirstLastname());
            preparedStatement.setString(6, entity.getSecondLastname());
            preparedStatement.setObject(7, entity.getCity().getId());
            preparedStatement.setString(8, entity.getEmail());
            preparedStatement.setString(9, entity.getPhoneNumber());
            preparedStatement.setBoolean(10, entity.getEmailVerified());
            preparedStatement.setBoolean(11, entity.getPhoneNumberVerified());
            preparedStatement.setObject(12, entity.getId());

            preparedStatement.executeUpdate();

        } catch (final Exception exception) {
            handlePersistenceException(exception, "'actualizar un usuario'");
        }

    }

    private UserEntity mapResultSetToUserEntity(java.sql.ResultSet resultSet) throws SQLException {

        var country = new CountryEntity();
        country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPaisDepartamentoCiudadResidencia")));
        country.setName(resultSet.getString("nombrePaisDepartamentoCiudadResidencia"));

        var state = new StateEntity();
        state.setCountry(country);
        state.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idDepartamentoCiudadResidencia")));
        state.setName(resultSet.getString("nombreDepartamentoCiudadResidencia"));

        var city = new CityEntity();
        city.setState(state);
        city.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idCiudadResidencia")));
        city.setName(resultSet.getString("nombreCiudadResidencia"));

        var identificationType = new IdentificationTypeEntity();
        identificationType.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idTipoIdentificacion")));
        identificationType.setName(resultSet.getString("nombreTipoIdentificacion"));

        var user = new UserEntity();
        user.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
        user.setIdentificationType(identificationType);
        user.setIdentificationNumber(resultSet.getString("numeroIdentificacion"));
        user.setFirstName(resultSet.getString("primerNombre"));
        user.setSecondName(resultSet.getString("segundoNombre"));
        user.setFirstLastname(resultSet.getString("primerApellido"));
        user.setSecondLastname(resultSet.getString("segundoApellido"));
        user.setCity(city);
        user.setEmail(resultSet.getString("correoElectronico"));
        user.setPhoneNumber(resultSet.getString("numeroTelefonoMovil"));
        user.setEmailVerified(resultSet.getBoolean("correoElectronicoConfirmado"));
        user.setPhoneNumberVerified(resultSet.getBoolean("numeroTelefonoMovilConfirmado"));


        return user;

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
        var technicalMessage = technicalBaseMessage.getContent().replace("la operación", operation);

        throw NoseException.create(exception, userMessage, technicalMessage);
    }
}



