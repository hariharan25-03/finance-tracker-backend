package org.example.dto.req;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class JWTRequest implements Serializable {

    private String email;
    private String password;
}
