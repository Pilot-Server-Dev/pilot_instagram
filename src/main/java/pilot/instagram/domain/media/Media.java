package pilot.instagram.domain.media;

import jakarta.persistence.*;
import lombok.Getter;
import pilot.instagram.domain.post.Post;

@Entity @Getter
public class Media {

    @Column(name = "media_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] url;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
