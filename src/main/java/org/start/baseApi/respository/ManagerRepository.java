package org.start.baseApi.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.start.baseApi.model.Manager;

import java.util.UUID;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, UUID> {

    @Query("SELECT m FROM Manager m WHERE m.login = :login and m.password = :password")
    Manager findByLoginPassword(@Param("login") String login, @Param("password") String password);
}
