package com.monte.msprestamo.models;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class PrestamoResponse implements Serializable {

    private static final long serialVersionUID = 2890051680308100441L;

    private float valorMaterial;

    private BigDecimal montoPrestamo;

    public PrestamoResponse(float valorMaterial, BigDecimal montoPrestamo) {
        this.valorMaterial = valorMaterial;
        this.montoPrestamo = montoPrestamo;
    }
}
