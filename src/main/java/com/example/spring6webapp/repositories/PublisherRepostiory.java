package com.example.spring6webapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.spring6webapp.domain.Publisher;

public interface PublisherRepostiory extends CrudRepository<Publisher, Long> {
    
}
