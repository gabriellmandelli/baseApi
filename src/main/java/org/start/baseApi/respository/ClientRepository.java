package org.start.baseApi.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.start.baseApi.model.Client;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    @Query("SELECT c FROM Client c where c.salesman.id  = :salesman_id")
    public List<Client> findBySalesman(@Param("salesman_id") UUID salesman_id);
}
