package co.edu.uco.nose.crosscuting.messagescatalog;

import co.edu.uco.nose.crosscuting.helpers.TextHelper;

public enum MessagesEnum {
    USER_ERROR_SQL_CONNECTION_IS_EMPTY("Conexion contra la fuente de informacion deseada vacia", "La conexion requerida para llevar a cabo la operacion contra la fuente de informacion deseada esta vacia. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),
    TECHNICAL_ERROR_SQL_CONNECTION_IS_EMPTY("Conexion contra la fuente de informacion deseada nula" , "La conexion requerida para llevar a cabo la operacion contra la base de datos llego nula."),
    USER_ERROR_SQL_CONNECTION_IS_CLOSED("Conexion contra la fuente de informacion deseada cerrada", "La conexion requerida para llevar a cabo la operacion contra la fuente de informacion deseada esta cerrada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),
    TECHNICAL_ERROR_SQL_CONNECTION_IS_CLOSED("Conexion contra la fuente de informacion deseada cerrada" , "La conexion requerida para llevar a cabo la operacion contra la base de datos esta cerrada."),
    USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS("Problema inesperado validando el estado de la conexion contra la fuente de datos deseada", "Se ha presentado un problema inesperado tratando de validar el estado de la conexion requerida para llevar a cabo la operacion deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),
    TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS("Error inesperado validando si la conexion contra la base de datos estaba abierta" , "Se presento un error de tipo SQLException al validar si la conexion con la base de datos estaba abierta. Por favor valide la consola de errores para revisar con detalle el problema presentado."),
    USER_ERROR_TRANSACTION_IS_NOT_STARTED("Transaccion no iniciada", "La operacion no puede completarse porque la transaccion requerida no ha sido iniciada. Por favor inicie la transaccion e intente nuevamente. Si el problema persiste, contacte al administrador de la aplicacion."),
    TECHNICAL_ERROR_TRANSACTION_IS_NOT_STARTED("Transaccion no iniciada en la base de datos","La operacion no puede completarse porque la transaccion requerida no fue iniciada correctamente en la base de datos. Por favor revise la logica de inicio de transacciones y si el problema persiste, contacte al administrador de la aplicacion."),
    USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED("Error inesperado al validar el inicio de la transaccion","Se presento un problema inesperado al validar el estado de la transaccion. Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicacion."),
    TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_STARTED("Error SQL al validar el inicio de la transaccion","Se produjo una excepcion SQL al intentar validar el estado de la transaccion. Por favor revise la conexion con la base de datos y si el problema persiste, contacte al administrador de la aplicacion."),
    TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED("Error tecnico inesperado al validar el inicio de la transaccion","Se presento un error tecnico inesperado al intentar validar el estado de la transaccion. Por favor revise los registros del sistema y si el problema persiste, contacte al administrador de la aplicacion."),
    USER_ERROR_PERSISTENCE_SQL("Problema con la base de datos", "Se ha presentado un problema al interactuar con la fuente de informacion. Por favor, intente de nuevo y si el problema persiste contacte al administrador."),
    TECHNICAL_ERROR_PERSISTENCE_SQL("Excepcion SQL inesperada en el DAO", "Se produjo un error de tipo SQLException en la capa DAO al ejecutar la operacion."),
    USER_ERROR_PERSISTENCE_UNEXPECTED("Problema inesperado de la aplicacion", "Se ha presentado un problema inesperado al interactuar con la fuente de informacion. Por favor, intente de nuevo y si el problema persiste contacte al administrador."),
    TECHNICAL_ERROR_PERSISTENCE_UNEXPECTED("Excepcion de logica no SQL en la capa DAO", "Se produjo una excepcion de logica o inesperada (NullPointer, etc.) en la capa DAO al tratar de ejecutar la operacion."),

    USER_ERROR_VALIDATION_INVALID_ID_NUMBER("Numero de identificacion invalido", "El numero de identificacion debe contener unicamente digitos y no puede estar vacio."),
    TECHNICAL_ERROR_VALIDATION_INVALID_ID_NUMBER("Formato de telefono invalido", "El valor de 'numeroIdentificacion' no cumple con el patron de solo digitos."),
    USER_ERROR_VALIDATION_INVALID_FIRST_NAME("Nombre invalido", "El primer nombre contiene caracteres no permitidos o esta vacio."),
    TECHNICAL_ERROR_VALIDATION_INVALID_FIRST_NAME("Formato de primer nombre invalido", "El valor de 'primerNombre' no cumple con el patron de solo letras."),
    USER_ERROR_VALIDATION_INVALID_SECOND_NAME("Segundo nombre invalido", "El segundo nombre contiene caracteres no permitidos."),
    TECHNICAL_ERROR_VALIDATION_INVALID_SECOND_NAME("Formato de segundo nombre invalido", "El valor de 'segundoNombre' no cumple con el patron de solo letras."),
    USER_ERROR_VALIDATION_INVALID_FIRST_LASTNAME("Primer apellido invalido", "El primer apellido contiene caracteres no permitidos o esta vacio."),
    TECHNICAL_ERROR_VALIDATION_INVALID_FIRST_LASTNAME("Formato de primer apellido invalido", "El valor de 'primerApellido' no cumple con el patron de solo letras."),
    USER_ERROR_VALIDATION_INVALID_SECOND_LASTNAME("Segundo apellido invalido", "El segundo apellido contiene caracteres no permitidos."),
    TECHNICAL_ERROR_VALIDATION_INVALID_SECOND_LASTNAME("Formato de segundo apellido invalido", "El valor de 'segundoApellido' no cumple con el patron de solo letras."),
    USER_ERROR_VALIDATION_INVALID_PHONE_NUMBER("Numero de telefono invalido", "El numero de telefono debe contener unicamente digitos y no puede estar vacio."),
    TECHNICAL_ERROR_VALIDATION_INVALID_PHONE_NUMBER("Formato de telefono invalido", "El valor de 'numeroTelefonoMovil' no cumple con el patron de solo digitos."),
    USER_ERROR_VALIDATION_INVALID_EMAIL("Correo electronico invalido", "El correo electronico no tiene un formato valido (usuario@dominio.ext)."),
    TECHNICAL_ERROR_VALIDATION_INVALID_EMAIL("Formato de correo invalido", "El valor de 'correoElectronico' no cumple con el patron basico usuario@dominio.ext."),

    USER_ERROR_VALIDATION_DUPLICATED_IDENTIFICATION("Identificacion duplicada", "Ya existe un usuario con el mismo tipo y numero de identificacion."),
    TECHNICAL_ERROR_VALIDATION_DUPLICATED_IDENTIFICATION("Duplicado de identificacion", "Violacion de unicidad: tipoIdentificacion + numeroIdentificacion existentes."),
    USER_ERROR_VALIDATION_DUPLICATED_EMAIL("Correo electronico duplicado", "El correo electronico ya esta registrado."),
    TECHNICAL_ERROR_VALIDATION_DUPLICATED_EMAIL("Duplicado de correo", "Violacion de unicidad: correoElectronico existente."),
    USER_ERROR_VALIDATION_DUPLICATED_PHONE("Telefono duplicado", "El numero de telefono ya esta registrado."),
    TECHNICAL_ERROR_VALIDATION_DUPLICATED_PHONE("Duplicado de telefono", "Violacion de unicidad: numeroTelefonoMovil existente.");

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

