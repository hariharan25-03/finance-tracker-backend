package org.example.dto.req;

import lombok.*;
import org.example.entity.User;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PortFolioRequestDTO {

    private UUID userId;
    private String name;
}
