package co.edu.uco.nose.data.dao.factory;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.SQLConnectionHelper;
import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.IdentificationTypeDAO;
import co.edu.uco.nose.data.dao.entity.StateDAO;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.data.dao.factory.sqlserver.SqlServerDAOFactory;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory {

    protected Connection connection;
    protected static FactoryEnum factory = FactoryEnum.SQLSERVER;

    public static DAOFactory getFactory() {
        switch (factory){
            case SQLSERVER: {
                return new SqlServerDAOFactory();
            }
            default: {
                var userMessage = "Factoría no iniciada ";
                var technicalMessage = "Factoría no valida ";
                throw NoseException.create(userMessage, technicalMessage);
            }
        }
    }

    public abstract CityDAO getCityDAO();
    public abstract CountryDAO getCountryDAO();
    public abstract StateDAO getStateDAO();
    public abstract IdentificationTypeDAO getIdentificationTypeDAO();
    public abstract UserDAO getUserDAO();

    protected abstract void openConnection();

    public final void innitTransaction() {
        SQLConnectionHelper.ensureTransactionIsNotStarted(connection);

        try {
            connection.setAutoCommit(false);
        } catch (final SQLException exception) {
            var userMessage = " ";
            var technicalMessage = " ";
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = " ";
            var technicalMessage = " ";
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
    }

    public final void commitTransaction() {
        SQLConnectionHelper.ensureTransactionIsStarted(connection);

        try {
            connection.commit();
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

    public final void rollbackTransaction() {
        SQLConnectionHelper.ensureTransactionIsStarted(connection);

        try {
            connection.rollback();
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
    public final void closeConnection() {
        SQLConnectionHelper.ensureConnectionIsOpen(connection);

        try {
            connection.close();
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
}
