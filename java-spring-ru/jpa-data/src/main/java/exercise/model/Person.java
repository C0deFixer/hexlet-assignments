package exercise.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Entity
@Table(name = "Person")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private long id;


    @Column(name = "firstName", length = 200)
    private String firstName;


    private String LastName;
}

// END
