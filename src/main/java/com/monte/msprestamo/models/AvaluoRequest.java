package com.monte.msprestamo.models;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AvaluoRequest implements Serializable {

    private static final long serialVersionUID = -1801598650010500771L;

    @NotNull(message = "El id del material no puede ser nulo o vacio.")
    private String idMaterial;

    @NotNull(message = "El peso del articulo no puede ser nulo o vacio.")
    private float pesoArticulo;

    public AvaluoRequest(String idMaterial, float pesoArticulo) {
        this.idMaterial = idMaterial;
        this.pesoArticulo = pesoArticulo;
    }

    public String getIdMaterial() {
        return idMaterial;
    }

    public float getPesoArticulo() {
        return pesoArticulo;
    }
}
