package au.com.studentplatform.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.studentplatform.admin.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
