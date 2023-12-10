package com.monte.msprestamo.controllers;

import com.monte.msprestamo.exceptions.BaseException;
import com.monte.msprestamo.models.AvaluoRequest;
import com.monte.msprestamo.models.PrestamoResponse;
import com.monte.msprestamo.service.IPrestamoService;
import com.monte.msprestamo.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
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

    /**
     * Bean de la fabrica de instancias
     */
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private IPrestamoService prestamoService;


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/v1/prestamo", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(httpMethod = "POST"
            , value = "Calcula el prestamo de un articulo evaluando sus propiedades"
            , tags = { "Prestamo"})
    @ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Cálculo del préstamo exitoso."),
            @ApiResponse(code = 400, response = Response.class, message = "El o los parametros especificados son invalidos."),
            @ApiResponse(code = 403, response = Response.class, message = "No cuenta con permisos para acceder a el recurso"),
            @ApiResponse(code = 404, response = Response.class, message = "El recurso que desea no fue encontrado"),
            @ApiResponse(code = 500, response = Response.class, message = "Error no esperado") })
    public Response calcularPrestamo(@RequestBody @Valid AvaluoRequest avaluo) throws BaseException {

        logger.info(">>> PrestamoController: Calcular Prestamo Inicio POST /v1/prestamo REQUEST: {}" , avaluo.getIdMaterial() + " - " + avaluo.getPesoArticulo());

        PrestamoResponse prestamo = prestamoService.calcularPrestamo(avaluo.getIdMaterial(), avaluo.getPesoArticulo());

        logger.info(">>>PrestamoController: Termina POST /v1/prestamo  ");

        return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Cálculo del préstamo exitoso.", prestamo);

    }


}
