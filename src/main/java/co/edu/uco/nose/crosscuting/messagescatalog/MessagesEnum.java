package co.edu.uco.nose.crosscuting.messagescatalog;

import co.edu.uco.nose.crosscuting.helpers.TextHelper;

public enum MessagesEnum {
    USER_ERROR_SQL_CONNECTION_IS_EMPTY("Conexion contra la fuente de informacion deseada vacia", "La conexion requerida para llevar a cabo la operacion contra la fuente de ingormacion deseada esta vacia. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),
    TECHNICAL_ERROR_SQL_CONNECTION_IS_EMPTY("Conexion contra la fuente de informacion deseada nula" , "La conexion requerida para llevar a cabo la operacion contra la base de datos llegó nula."),
    USER_ERROR_SQL_CONNECTION_IS_CLOSED("Conexion contra la fuente de informacion deseada cerrada", "La conexion requerida para llevar a cabo la operacion contra la fuente de ingormacion deseada esta cerrada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),
    TECHNICAL_ERROR_SQL_CONNECTION_IS_CLOSED("Conexion contra la fuente de informacion deseada cerrada" , "La conexion requerida para llevar a cabo la operacion contra la base de datos está cerrada."),
    USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS("problema inesperado validando el estado de la coneccion contra la fuente de datos deseada", "Se ha presentado un problema inesperado tratando de validar el estado de la coneccion requerida para llevar a cabo la operacion deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),
    TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS("Error inesperado validando si la conexion contra la base de datos estaba abierta" , "Se presento un error de tipo SQLException al validar si la coneccion con la base de datos estaba abierta. Por favor valide la consola de errores para revisar con detalle el problema presentado."),
    USER_ERROR_TRANSACTION_IS_NOT_STARTED("Transacción no iniciada", "La operación no puede completarse porque la transacción requerida no ha sido iniciada. Por favor inicie la transacción e intente nuevamente. Si el problema persiste, contacte al administrador de la aplicación."),
    TECHNICAL_ERROR_TRANSACTION_IS_NOT_STARTED("Transacción no iniciada en la base de datos","La operación no puede completarse porque la transacción requerida no fue iniciada correctamente en la base de datos. Por favor revise la lógica de inicio de transacciones y si el problema persiste, contacte al administrador de la aplicación."),
    USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED("Error inesperado al validar el inicio de la transacción","Se presentó un problema inesperado al validar el estado de la transacción. Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación."),
    TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_STARTED("Error SQL al validar el inicio de la transacción","Se produjo una excepción SQL al intentar validar el estado de la transacción. Por favor revise la conexión con la base de datos y si el problema persiste, contacte al administrador de la aplicación."),
    TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED("Error técnico inesperado al validar el inicio de la transacción","Se presentó un error técnico inesperado al intentar validar el estado de la transacción. Por favor revise los registros del sistema y si el problema persiste, contacte al administrador de la aplicación.");


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
