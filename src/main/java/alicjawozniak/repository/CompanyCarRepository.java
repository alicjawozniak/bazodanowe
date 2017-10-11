package alicjawozniak.repository;

import alicjawozniak.model.CompanyCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyCarRepository extends JpaRepository<CompanyCar, Long> {
}
