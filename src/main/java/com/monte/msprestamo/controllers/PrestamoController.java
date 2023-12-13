package com.monte.msprestamo.controllers;

import com.monte.msprestamo.exceptions.BaseException;
import com.monte.msprestamo.models.AvaluoRequest;
import com.monte.msprestamo.models.PrestamoResponse;
import com.monte.msprestamo.service.IPrestamoService;
import com.monte.msprestamo.util.Response;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/monte")
@Api(value = "Servicio que permite calcular el préstamo de un artículo evaluando sus propiedades"
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
    @Operation(summary = "Calcula el préstamo de un artículo evaluando sus propiedades.", method = "POST", tags = { "Prestamo"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cálculo del préstamo exitoso.",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)) }),
            @ApiResponse(responseCode = "400", description = "El o los parámetros especificados son inválidos.",  content = @Content),
            @ApiResponse(responseCode = "500", description = "Error no esperado.", content = @Content)
    })
    public Response calcularPrestamo(@RequestBody @Valid AvaluoRequest avaluo) {

        logger.info(">>> PrestamoController: Calcular Préstamo Inicio POST /v1/prestamo REQUEST: {}" , avaluo.getIdMaterial() + " - " + avaluo.getPesoArticulo());

        PrestamoResponse prestamo;
        try {
            prestamo = prestamoService.calcularPrestamo(avaluo.getIdMaterial(), avaluo.getPesoArticulo());
        } catch (BaseException e) {
            return beanFactory.getBean(Response.class, e.getStatus().toString(), e.getDescripcion(),null);
        }

        logger.info(">>>PrestamoController: Termina POST /v1/prestamo  ");

        return beanFactory.getBean(Response.class, HttpStatus.OK.toString(), "Cálculo del préstamo exitoso.", prestamo);

    }


}
