package com.monte.msprestamo.models;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class PrestamoResponse implements Serializable {

    private static final long serialVersionUID = 2890051680308100471L;

    private String descripcionMaterial;

    private String valorMaterial;

    private String montoPrestamo;

    public PrestamoResponse(String descripcionMaterial, String valorMaterial, String montoPrestamo) {
        this.descripcionMaterial = descripcionMaterial;
        this.valorMaterial = valorMaterial;
        this.montoPrestamo = montoPrestamo;
    }
}
