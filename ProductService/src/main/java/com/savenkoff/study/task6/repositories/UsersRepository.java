package com.savenkoff.study.task6.repositories;

import com.savenkoff.study.task6.entities.User;
import com.savenkoff.study.task6.entities.projections.UserProductsProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    @EntityGraph(value = "User.withInProducts")
    @Query("select u from User u where u.id = :userId")
    UserProductsProjection findByIdWithInProducts(Long userId);
}
