package com.monte.msprestamo.controllers;

import com.monte.msprestamo.exceptions.BaseException;
import com.monte.msprestamo.models.AvaluoRequest;
import com.monte.msprestamo.models.PrestamoResponse;
import com.monte.msprestamo.service.IPrestamoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/monte")
@Api(value = "Servicio que permite calcular el prestamo de un articulo evaluando sus propiedades"
        , produces = MediaType.APPLICATION_JSON_VALUE
        , protocols = "http"
        , tags = {"Prestamo"})
public class PrestamoController {

    private static final Logger logger = LoggerFactory.getLogger(PrestamoController.class);

    @Autowired
    private IPrestamoService prestamoService;


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/v1/prestamo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(httpMethod = "POST"
            , value = "Calcula el prestamo de un articulo evaluando sus propiedades"
            , tags = { "Prestamo"})
    public ResponseEntity calcularPrestamo(@RequestBody @Valid AvaluoRequest avaluo) throws BaseException {

        logger.info(">>> PrestamoController: Calcular Prestamo Inicio POST /v1/prestamo REQUEST: {}" , avaluo.getIdMaterial() + " - " + avaluo.getPesoArticulo());

        PrestamoResponse prestamo = prestamoService.calcularPrestamo(avaluo.getIdMaterial(), avaluo.getPesoArticulo());

        logger.info(">>>PrestamoController: Termina POST /v1/prestamo  ");
        return ResponseEntity.ok(prestamo);
    }


}
