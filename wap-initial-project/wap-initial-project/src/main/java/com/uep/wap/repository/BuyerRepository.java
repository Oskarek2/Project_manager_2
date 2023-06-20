package com.uep.wap.repository;

import com.uep.wap.model.Buyer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerRepository extends CrudRepository<Buyer, Long> {

    @EntityGraph(attributePaths = {"projects.users"})
    @Query("SELECT b FROM Buyer b")
    List<Buyer> findAllWithProjectsAndUsers();
}
