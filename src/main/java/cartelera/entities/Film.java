package cartelera.entities;

import cartelera.entities.enums.Classification;
import cartelera.entities.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    private Integer duration;

    private Integer year;

    private String director;

    private String country;

    private Classification classification;

    private Byte rating;

    private String music;

    private  String photography;

    private String script;

    private String company;

    private String actor;

    @ElementCollection
    private Set<Gender> genders = new HashSet<>();

    private String trailer;

    private String poster;

    private String synopsis;

    @OneToMany(mappedBy = "film", fetch = FetchType.EAGER)
    private Set<Room> rooms = new HashSet<>();

    @Override
    public String toString() {
        return "«" + title + "» (" + year + ')';
    }
}