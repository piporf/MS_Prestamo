package com.monte.msprestamo.models;

import com.mongodb.lang.NonNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;


@Getter
@Setter
@Document(collection = "tc_precio_material")
public class PrecioMaterial implements Serializable  {

    @Id
    private String id;
    private String clave;
    private String material;
    private String precioGramo;

    public PrecioMaterial(String id, String clave, String material, String precioGramo) {
        this.id = id;
        this.clave = clave;
        this.material = material;
        this.precioGramo = precioGramo;
    }

}
