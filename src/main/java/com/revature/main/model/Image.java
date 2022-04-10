package com.revature.main.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.InputStream;
import java.util.Objects;

@Entity
@Table(name = "nps_images")
@NoArgsConstructor @Getter @Setter @ToString @EqualsAndHashCode
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
/*
    // TODO if you have time use this instead and store images in cloud storage

    @Column(name = "nps_images_image_url")
    private String imageUrl;
 */

    @Column(name = "nps_images_image")
    private Byte[] image;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User author;

    @Column(name = "nps_images_contract_address")
    private String contractAddress;

}
