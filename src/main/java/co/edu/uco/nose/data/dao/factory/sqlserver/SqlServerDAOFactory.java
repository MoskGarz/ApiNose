package co.edu.uco.nose.data.dao.factory.sqlserver;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.IdentificationTypeDAO;
import co.edu.uco.nose.data.dao.entity.StateDAO;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.CitySqlServerDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.CountrySqlServerDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.IdentificationTypeSqlServerDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.StateSqlServerDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.UserSqlServerDAO;
import co.edu.uco.nose.data.dao.factory.DAOFactory;

import java.sql.DriverManager;
import java.sql.SQLException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;

public class SqlServerDAOFactory extends DAOFactory {


    public SqlServerDAOFactory(){
        openConnection();
    }

    @Override
    protected void openConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=ApiNoseDB;encrypt=true;trustServerCertificate=true;integratedSecurity=true";
            this.connection = DriverManager.getConnection(url);
        } catch (final SQLException exception){
            var userMessage = MessagesEnum.USER_ERROR_PERSISTENCE_SQL.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_PERSISTENCE_SQL.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception){
            var userMessage = MessagesEnum.USER_ERROR_PERSISTENCE_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_PERSISTENCE_UNEXPECTED.getContent();
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
