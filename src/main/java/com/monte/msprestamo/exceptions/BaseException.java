package com.monte.msprestamo.exceptions;

import org.springframework.http.HttpStatus;

public class BaseException extends Exception {

    private static final long serialVersionUID = -1304890812361195977L;

    public static final String LABEL_ERROR_ID = "Error";

    protected final String id;
    protected final String estado;
    protected final String descripcion;
    protected final HttpStatus status;

    public BaseException(String descripcion, String estado, HttpStatus status) {
        this.id = LABEL_ERROR_ID;
        this.estado = estado;
        this.descripcion = descripcion;
        this.status = status;
    }

    public String getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

}
