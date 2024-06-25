package physiodesk.physiodesk_backend.Users.infraestructure.persistance.jpa.physio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import physiodesk.physiodesk_backend.Users.domain.model.aggregates.physio;

import java.util.List;
import java.util.Optional;

public interface physioRepository extends JpaRepository<physio, Long> {
    @Query("SELECT p FROM physio p WHERE p.id = :id")
    Optional<physio> GetPhysioById(@Param("id") Long id);
    @Query("SELECT p FROM physio p")
    List<physio> GetAllPhysios();

}
