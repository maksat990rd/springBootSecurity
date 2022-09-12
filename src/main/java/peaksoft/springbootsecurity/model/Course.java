package peaksoft.springbootsecurity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor


    public class Course {
    @Id
    @SequenceGenerator(
            name = "courses_sequence",
            allocationSize = 1,
            sequenceName = "courses_id_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "courses_sequence"
    )
    private int id;
    @Column(name = "course_name")
    private String courseName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "date_of")
    private LocalDate dateOfStart;
    @Column
    private int duration;
    @Column
    private String image;
    @Column
    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(mappedBy = "courses",cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Instructor> instructors;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Student> students;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Lesson>lessons;

    public void addInstructor(Instructor instructor){
        if (instructors == null){
            instructors = new ArrayList<>();
        }
        instructors.add(instructor);
    }

    public void addStudent(Student student){
        if (students == null){
            students= new ArrayList<>();
        }
        students.add(student);
    }

    public void addLessons(Lesson lesson){
        if (lessons == null){
            lessons= new ArrayList<>();
        }
        lessons.add(lesson);
    }


}
