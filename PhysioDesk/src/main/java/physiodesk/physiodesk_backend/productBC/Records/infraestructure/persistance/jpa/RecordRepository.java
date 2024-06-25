package physiodesk.physiodesk_backend.productBC.Records.infraestructure.persistance.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import physiodesk.physiodesk_backend.productBC.Records.domain.model.aggregates.Records;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Records, Long>{
    @Query("SELECT r FROM Records r WHERE r.id = :id")
    Optional<Records> getRecordById(@Param("id") Long id);
    @Query("SELECT r FROM Records r")
    List<Records> getAllRecords();
    @Query("SELECT r FROM Records r WHERE r.date = :fecha")
    List<Records> getRecordByFecha(@Param("fecha") String fecha);
}
