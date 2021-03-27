package com.example.myrest;

import java.util.Optional;

/**
 * Can be added to a {@link org.springframework.data.repository.PagingAndSortingRepository} repository
 *
 * @param <T> an entity type
 */
public interface RandomableRepository<T> {
    Optional<T> findRandom();
}
