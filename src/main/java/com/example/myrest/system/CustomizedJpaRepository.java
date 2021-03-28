package com.example.myrest.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * JpaRepository with additional methods
 *
 * @param <T> entity type
 * @param <ID> entity ID type
 */

@NoRepositoryBean
public interface CustomizedJpaRepository<T, ID> extends JpaRepository<T, ID> {
    /**
     * Returns one random entity from the repository
     * @return
     */
    Optional<T> findRandom();
}
