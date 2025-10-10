package co.edu.uco.nose.crosscuting.messagescatalog;

import co.edu.uco.nose.crosscuting.helpers.TextHelper;

public enum MessagesEnum {
    USER_ERROR_SQL_CONNECTION_IS_EMPTY("Conexion contra la fuente de informacion deseada vacia", "La conexion requerida para llevar a cabo la operacion contra la fuente de ingormacion deseada esta vacia. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),
    TECHNICAL_ERROR_SQL_CONNECTION_IS_EMPTY("Conexion contra la fuente de informacion deseada nula" , "La conexion requerida para llevar a cabo la operacion contra la base de datos llegó nula."),
    USER_ERROR_SQL_CONNECTION_IS_CLOSED("Conexion contra la fuente de informacion deseada cerrada", "La conexion requerida para llevar a cabo la operacion contra la fuente de ingormacion deseada esta cerrada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),
    TECHNICAL_ERROR_SQL_CONNECTION_IS_CLOSED("Conexion contra la fuente de informacion deseada cerrada" , "La conexion requerida para llevar a cabo la operacion contra la base de datos está cerrada."),
    USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONECTION_STATUS("problema inesperado validando el estado de la coneccion contra la fuente de datos deseada", "Se ha presentado un problema inesperado tratando de validar el estado de la coneccion requerida para llevar a cabo la operacion deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),
    TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONECTION_STATUS("Error inesperado validando si la conexion contra la base de datos estaba abierta" , "Se presento un error de tipo SQLException al validar si la coneccion con la base de datos estaba abierta. Por favor valide la consola de errores para revisar con detalle el problema presentado.");

    private String title;
    private String content;

    private MessagesEnum(String title, String content) {
        setTitle(title);
        setContent(content);
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(final String title) {
        this.title = TextHelper.getDefaultWithTrim(title);
    }

    public String getContent() {
        return content;
    }

    private void setContent(final String content) {
        this.content = TextHelper.getDefaultWithTrim(content);
    }
}
