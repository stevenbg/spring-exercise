package com.example.myrest;

import java.util.Optional;

public interface RandomableRepository<T> {
    Optional<T> findRandom();
}
