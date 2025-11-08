package com.example.studentplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentplatform.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
