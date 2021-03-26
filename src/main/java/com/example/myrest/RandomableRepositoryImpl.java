package com.example.myrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

class RandomableRepositoryImpl<T> implements RandomableRepository<T> {
    @Autowired
//    todo read up on this
    @Lazy
    PagingAndSortingRepository repository;

    public Optional<T> findRandom() {
        Optional<T> out = Optional.empty();
        long total = repository.count();
        if (total > 0) {
            int index = (int) Math.round((Math.random() * (total - 1)));
            Page<T> result = repository.findAll(PageRequest.of(index, 1));
            out = Optional.ofNullable(result.getContent().get(0));
        }

        return out;
    }
}
