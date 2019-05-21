package org.start.baseApi.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.start.baseApi.model.ProductStock;

import java.util.UUID;

public interface ProductStockRepository extends JpaRepository<ProductStock, UUID> {

}
