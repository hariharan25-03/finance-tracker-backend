package org.example.dto.response;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString

public class JWTResponse implements Serializable {

    private String jwtToken;

    private String username;
    private UUID userId;

}