package physiodesk.physiodesk_backend.productBC.Records.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import physiodesk.physiodesk_backend.productBC.Records.domain.model.commands.CreateRecordCommand;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Records extends AbstractAggregateRoot<Records> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    @JsonProperty("date")
    private String date;

    @Column(nullable = false)
    @Getter
    @JsonProperty("name")
    private String name;

    @Column(nullable = false)
    @Getter
    @JsonProperty("duration")
    private String duration;

    @Column(nullable = false)
    @Getter
    @JsonProperty("access")
    private String access;

    public Records(){}

    public Records(Long id, String date, String name, String duration, String access) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.duration = duration;
        this.access = access;
    }

    public Records(CreateRecordCommand command){
        this.date = command.date();
        this.name = command.name();
        this.duration = command.duration();
        this.access = command.access();
    }


}


