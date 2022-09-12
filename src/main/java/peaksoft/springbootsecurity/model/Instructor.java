package peaksoft.springbootsecurity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructors")
@Getter @Setter
@NoArgsConstructor
public class Instructor {

    @Id
    @SequenceGenerator(
            name = "instructors_sequence",
            allocationSize = 1,
            sequenceName = "instructors_id_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "instructors_sequence"
    )
    private int id;
    @Column(name = "first_name")

    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column
    private String email;

    @Column
    private String specialization;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    private Company company;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    private List<Course> courses;

    public void addCourse(Course course) {
        if (courses == null){
            courses = new ArrayList<>();
        }
        courses.add(course);
    }
}
