package peaksoft.springbootsecurity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Getter @Setter
@NoArgsConstructor
public class Student {

    @Id
    @SequenceGenerator(
            name = "students_sequence",
            allocationSize = 1,
            sequenceName = "students_id_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "students_sequence"
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

    //@Enumerated
    @Column(name = "study_format")
    private StudyFormat studyFormat;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    private Course course;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinColumn(name = "company_id")
    private Company company;


}
