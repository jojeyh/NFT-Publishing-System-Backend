package com.revature.main.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "nps_images")
@NoArgsConstructor @Getter @Setter @ToString
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nps_images_image_url")
    private String imageUrl;

    @ManyToOne
    private User author;

    @Column(name = "nps_images_contract_address")
    private String contractAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;
        Image image = (Image) o;
        return id.equals(image.id) && imageUrl.equals(image.imageUrl) && author.equals(image.author) && Objects.equals(contractAddress, image.contractAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageUrl, author, contractAddress);
    }
}
