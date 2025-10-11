package co.edu.uco.nose.data.dao.factory.sqlserver;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.IdentificationTypeDAO;
import co.edu.uco.nose.data.dao.entity.StateDAO;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.data.dao.entity.postgresql.CitySqlServerDAO;
import co.edu.uco.nose.data.dao.entity.postgresql.CountrySqlServerDAO;
import co.edu.uco.nose.data.dao.entity.postgresql.IdentificationTypeSqlServerDAO;
import co.edu.uco.nose.data.dao.entity.postgresql.StateSqlServerDAO;
import co.edu.uco.nose.data.dao.entity.postgresql.UserSqlServerDAO;
import co.edu.uco.nose.data.dao.factory.DAOFactory;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlServerDAOFactory extends DAOFactory {


    @Override
    protected void openConnection() {
        try {
            this.connection = DriverManager.getConnection(" ");
        } catch (final SQLException exception){
            var userMessage = " ";
            var technicalMessage = " ";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception){
            var userMessage = " ";
            var technicalMessage = " ";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
    }
    @Override
    public CityDAO getCityDAO() {
        return new CitySqlServerDAO(connection);
    }

    @Override
    public CountryDAO getCountryDAO() {
        return new CountrySqlServerDAO(connection);
    }

    @Override
    public StateDAO getStateDAO() {
        return new StateSqlServerDAO(connection);
    }

    @Override
    public IdentificationTypeDAO getIdentificationTypeDAO() {
        return new IdentificationTypeSqlServerDAO(connection);
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserSqlServerDAO(connection);
    }
}
