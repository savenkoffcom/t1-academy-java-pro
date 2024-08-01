package com.savenkoff.study.task6.repositories;

import com.savenkoff.study.task6.entities.Product;
import com.savenkoff.study.task6.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "owner")
    @Query("select p from Product p")
    List<Product> findAllWithUser();

    Product getByIdAndOwner(Long id, User owner);

    @Query(value = "select p.* from products p join users u on p.user_id = u.id where p.id = :id and p.user_id = :ownerId", nativeQuery = true)
    Product getByIdAndOwnerId(Long id, Long ownerId);
}
