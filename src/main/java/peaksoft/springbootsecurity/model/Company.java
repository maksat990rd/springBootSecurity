package peaksoft.springbootsecurity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter @Setter
@NoArgsConstructor
public class Company {
    @Id
    @SequenceGenerator(
            name = "companies_sequence",
            allocationSize = 1,
            sequenceName = "companies_id_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "companies_sequence"
    )
    private int id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "located_country")
    private String locatedCountry;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "company",
            fetch = FetchType.EAGER)
    private List<Course> courses;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    private List<Instructor> instructors;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    private List<Student> students ;

    public void addCourse(Course course){
        if (courses == null){
            courses= new ArrayList<>();
        }
        courses.add(course);
    }


    public void addInstructor(Instructor instructor) {
        if(instructors == null) {
            instructors = new ArrayList<>();
        } else {
            this.instructors.add(instructor);
        }
    }

    public void addStudent(Student student){
        if (students == null){
            students= new ArrayList<>();
        }
        students.add(student);
    }

}
