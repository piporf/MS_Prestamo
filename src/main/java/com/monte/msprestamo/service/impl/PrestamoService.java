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

        PrecioMaterial precioMaterial;
        BigDecimal montoPrestamo;
        PrestamoResponse prestamo;

        try {
            precioMaterial = precioMaterialRepository.findByClave(idMaterial).orElse(null);
            if(precioMaterial != null ){
                montoPrestamo = BigDecimal.valueOf((pesoArticulo * Float.valueOf(precioMaterial.getPrecioGramo())) * 0.80);
                prestamo = new PrestamoResponse(precioMaterial.getMaterial(),precioMaterial.getPrecioGramo(), montoPrestamo.toPlainString());
            } else {
                throw new BaseException("El o los parametros especificados son invalidos.","400", HttpStatus.BAD_REQUEST);
            }
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error: ", e);
            throw new BaseException("Error no esperado.", "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("PrestamoService: Termina cálculo.");
        return prestamo;
    }

}
