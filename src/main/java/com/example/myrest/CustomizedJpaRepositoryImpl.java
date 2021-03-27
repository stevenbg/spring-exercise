package com.example.myrest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;


import javax.persistence.EntityManager;
import java.util.Optional;

public class CustomizedJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomizedJpaRepository<T, ID> {
    public CustomizedJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }
    public CustomizedJpaRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    public Optional<T> findRandom() {
        Optional<T> out = Optional.empty();
        long total = count();
        if (total > 0) {
            int index = (int) Math.round((Math.random() * (total - 1)));
            Page<T> result = findAll(PageRequest.of(index, 1));
            out = Optional.ofNullable(result.getContent().get(0));
        }

        return out;
    }
}
