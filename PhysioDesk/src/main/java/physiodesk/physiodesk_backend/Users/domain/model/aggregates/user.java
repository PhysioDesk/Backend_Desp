package physiodesk.physiodesk_backend.Users.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import physiodesk.physiodesk_backend.Users.domain.model.commands.user.CreateUserCommand;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class user extends AbstractAggregateRoot<user> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(nullable = false)
    @Getter
    @Setter
    private String lastname;

    @Column(nullable = false)
    @Getter
    @Setter
    private long phone;

    @Column(nullable = false)
    @Getter
    @Setter
    private String email;

    @Column(nullable = false)
    @Getter
    @Setter
    private String address;

    @Column(nullable = false)
    @Getter
    @Setter
    private String city;

    @Column(nullable = false)
    @Getter
    @Setter
    private String password;

    public user() {
    }

    public user(Long id, String name, String lastname, Long phone,String email, String address, String city, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastname = lastname;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.password = password;
    }

    public user(CreateUserCommand command){
        this.name = command.name();
        this.lastname = command.lastname();
        this.phone = command.phone();
        this.email = command.email();

        this.address = command.address();
        this.city = command.city();
        this.password = command.password();
    }

    public void setId(Long id) {

    }
}
