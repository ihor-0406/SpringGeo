package ua.kiev.prog.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Page<Location> findByCity(String city, Pageable pageable);
    long countByCity(String city);
}
