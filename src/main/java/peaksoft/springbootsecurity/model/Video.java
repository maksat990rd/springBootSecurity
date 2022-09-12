package peaksoft.springbootsecurity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "videos")
@Getter@Setter
@NoArgsConstructor
public class Video {
    @Id
    @SequenceGenerator(
            name = "videos_sequence",
            allocationSize = 1,
            sequenceName = "videos_id_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "videos_sequence"
    )
    private int id;
    @Column(name = "video_name")
    private String videoName;

    @Column
    private String link;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    private Lesson lesson;

}
