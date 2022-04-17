package com.revature.main.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "nps_images")
@NoArgsConstructor @Getter @Setter @ToString @EqualsAndHashCode @AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nps_image_url")
    private String imageURL;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User author;

}
