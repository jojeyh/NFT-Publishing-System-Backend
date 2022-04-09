package com.revature.main.model;

import lombok.*;

@NoArgsConstructor @Getter @Setter @EqualsAndHashCode @ToString @AllArgsConstructor
public class UserJwtDTO {

    private Long userId;
    private String username;

}
