package com.monte.msprestamo.service;

import com.monte.msprestamo.exceptions.BaseException;
import com.monte.msprestamo.models.PrestamoResponse;


public interface IPrestamoService {

    /**
     * Funcionalidad que permite calcular el prestamo de un articulo.
     * @param idMaterial Identificador del material.
     * @param pesoArticulo Peso en gramos del articulo.
     * @return {@link PrestamoResponse} Contiene la informacion del prestamo calculado.
     */
    PrestamoResponse calcularPrestamo(String idMaterial, float pesoArticulo) throws BaseException;


}
