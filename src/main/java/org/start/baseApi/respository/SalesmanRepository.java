package org.start.baseApi.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.start.baseApi.model.Salesman;

import java.util.UUID;

@Repository
public interface SalesmanRepository extends JpaRepository<Salesman, UUID> {

    @Query("SELECT u FROM Salesman u WHERE u.login LIKE :login")
    public Salesman findByLoginPassword(@Param("login") String login);

}
