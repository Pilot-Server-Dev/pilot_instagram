package pilot.instagram.domain.tag;

import jakarta.persistence.*;
import lombok.Getter;
import pilot.instagram.domain.association.PostTag;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter
public class Tag {

    @Column(name = "tag_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @OneToMany(mappedBy = "tag")
    private List<PostTag> postTags = new ArrayList<>();
}
