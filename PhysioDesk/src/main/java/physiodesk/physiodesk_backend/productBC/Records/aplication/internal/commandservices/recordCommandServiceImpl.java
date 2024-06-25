package physiodesk.physiodesk_backend.productBC.Records.aplication.internal.commandservices;

import org.springframework.stereotype.Service;
import physiodesk.physiodesk_backend.productBC.Records.domain.model.commands.CreateRecordCommand;
import physiodesk.physiodesk_backend.productBC.Records.infraestructure.persistance.jpa.RecordRepository;
import physiodesk.physiodesk_backend.productBC.Records.domain.services.RecordCommandService;
import physiodesk.physiodesk_backend.productBC.Records.domain.model.aggregates.Records;
import java.util.Optional;

@Service
public class recordCommandServiceImpl implements RecordCommandService {

    private final RecordRepository recordRepository;

    public recordCommandServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public Optional<Records> handle(CreateRecordCommand command) {
        if (recordRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Record already exists");
        }
        var record = new Records(command);
        var createdRecord = recordRepository.save(record);

        return Optional.of(createdRecord);
    }

    @Override
    public boolean deleteRecordById(Long id) {
        if (!recordRepository.existsById(id)) {
            return false; // El registro con el ID dado no existe, por lo que no se puede eliminar
        }
        recordRepository.deleteById(id);
        return true; // Eliminación exitosa
    }

}
