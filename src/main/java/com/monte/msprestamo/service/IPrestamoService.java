package com.monte.msprestamo.service;

import com.monte.msprestamo.exceptions.BaseException;
import com.monte.msprestamo.models.PrestamoResponse;


public interface IPrestamoService {

    /**
     * Funcionalidad que permite calcular el préstamo de un artículo.
     * @param idMaterial Identificador del material.
     * @param pesoArticulo Peso en gramos del artículo.
     * @return {@link PrestamoResponse} Contiene la información del préstamo calculado.
     */
    PrestamoResponse calcularPrestamo(String idMaterial, float pesoArticulo) throws BaseException;


}
