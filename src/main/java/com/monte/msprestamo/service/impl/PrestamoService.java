package com.monte.msprestamo.service.impl;

import com.monte.msprestamo.exceptions.BaseException;
import com.monte.msprestamo.models.PrecioMaterial;
import com.monte.msprestamo.models.PrestamoResponse;
import com.monte.msprestamo.repository.PrecioMaterialRepository;
import com.monte.msprestamo.service.IPrestamoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;


@Service
public class PrestamoService implements IPrestamoService {

    private static final Logger logger = LoggerFactory.getLogger(PrestamoService.class);

    @Autowired
    private PrecioMaterialRepository precioMaterialRepository;


    @Override
    public PrestamoResponse calcularPrestamo(String idMaterial, float pesoArticulo) throws BaseException  {

        logger.info("PrestamoService: Inicia cálculo.");

        float valorMaterial;
        BigDecimal montoPrestamo;

        try {
            PrecioMaterial precioMaterial = precioMaterialRepository.findByClave(idMaterial).orElse(null);
            valorMaterial = precioMaterial != null ? precioMaterial.getPrecioGramo() : 0;
            montoPrestamo = BigDecimal.valueOf((pesoArticulo * valorMaterial) * 0.80);
        } catch (Exception e) {
            throw new BaseException(e.getMessage(), "00", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("PrestamoService: Termina cálculo.");
        return new PrestamoResponse(valorMaterial, montoPrestamo);
    }

}
