package com.monte.msprestamo.repository;

import com.monte.msprestamo.models.PrecioMaterial;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

@Repository
public interface PrecioMaterialRepository extends MongoRepository<PrecioMaterial, String> {

    @Query("{ 'clave' : ?0 }")
    Optional<PrecioMaterial> findByClave(String idMaterial);
}
