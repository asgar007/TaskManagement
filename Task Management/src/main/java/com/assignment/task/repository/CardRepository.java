package com.assignment.task.repository;

import com.assignment.task.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardRepository extends MongoRepository<Card, String> {
}
