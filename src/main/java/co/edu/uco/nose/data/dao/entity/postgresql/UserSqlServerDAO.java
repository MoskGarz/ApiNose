package co.edu.uco.nose.data.dao.entity.postgresql;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.SQLConnectionHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.IdentificationTypeEntity;
import co.edu.uco.nose.entity.StateEntity;
import co.edu.uco.nose.entity.UserEntity;

import java.sql.Connection;
import java.sql.SQLException;
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
        sql.append("INSERT INTO User(id, tipoIdentificacion, numeroIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, ciudadResidencia, correoElectronico, numeroTelefonoMovil, correoElectronicoConfirmado, numeroTelefonoMovilConfirmado) ");
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

        } catch (final SQLException exception) {
           var userMessage = "Se ha presentado un problema tratando de registrar la información del nuevo usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema...";
           var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el procedimiento de creación de un nuevo usuario en la base de datos. Revise la traza del error para más detalles";
           throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception){
            var userMessage = "Se ha presentado un problema inesperado tratando de registrar la información del nuevo usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema...";
            var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el procedimiento de creación de un nuevo usuario en la base de datos. Revise la traza del error para más detalles";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public void delete(UUID uuid) {

        SQLConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("DELETE FROM User ");
        sql.append("WHERE id = ?");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())){
            preparedStatement.setObject(1, uuid);
            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = "Se ha presentado un problema tratando de eliminar la información del usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema...";
            var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el procedimiento de eliminación de un usuario en la base de datos. Revise la traza del error para más detalles";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception){
            var userMessage = "Se ha presentado un problema inesperado tratando de eliminar la información del usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema...";
            var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el procedimiento de eliminación de un usuario en la base de datos. Revise la traza del error para más detalles";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

    }

    @Override
    public List<UserEntity> findAll() {
        return null;
    }

    @Override
    public List<UserEntity> findByFilter(UserEntity filterEntity) {
        return null;
    }

    @Override
    public UserEntity findById(UUID uuid) {

        var user = new UserEntity();

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
        sql.append("INNER JOIN    TipoIdentificacion AS ti ON ti.id = u.tipoIdentificacion ");
        sql.append("INNER JOIN    Ciudad AS c ON c.id = u.ciudadResidencia ");
        sql.append("INNER JOIN    Departamento AS d ON d.id = c.departamento ");
        sql.append("INNER JOIN    Pais AS p ON p.id = d.pais ");
        sql.append("WHERE   u.id = ? ");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())){
            preparedStatement.setObject(1, uuid);
            try (var resultSet = preparedStatement.executeQuery()){

                if(resultSet.next()) {

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

                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Se ha presentado un problema tratando de consultar la información del usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema...";
            var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el procedimiento de consulta de un usuario en la base de datos. Revise la traza del error para más detalles";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception){
            var userMessage = "Se ha presentado un problema inesperado tratando de consultar la información del usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema...";
            var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el procedimiento de consulta de un usuario en la base de datos. Revise la traza del error para más detalles";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

        return user;
    }

    @Override
    public void update(UserEntity entity) {

        SQLConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE User SET tipoIdentificacion = ?, numeroIdentificacion = ?, primerNombre = ?, segundoNombre = ?, primerApellido = ?, segundoApellido = ?, ciudadResidencia = ?, correoElectronico = ?, numeroTelefonoMovil = ?, correoElectronicoConfirmado = ?, numeroTelefonoMovilConfirmado = ? ");
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

        } catch (final SQLException exception) {
           var userMessage = "Se ha presentado un problema tratando de actualizar la información del usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema...";
           var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el procedimiento de actualización de un usuario en la base de datos. Revise la traza del error para más detalles";
           throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception){
            var userMessage = "Se ha presentado un problema inesperado tratando de actualizar la información del usuario. Por favor intente de nuevo y si el problema persiste contacte al administrador del sistema...";
            var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el procedimiento de actualización de un usuario en la base de datos. Revise la traza del error para más detalles";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }

    }
}
