package com.fabiano.currency.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fabiano.currency.domain.Currency;

@Repository
public interface CurrencyRepository extends MongoRepository<Currency, String>{

}
