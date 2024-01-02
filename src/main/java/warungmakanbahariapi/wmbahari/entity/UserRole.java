package warungmakanbahariapi.wmbahari.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;

    @Enumerated(EnumType.STRING)
    private ERole role;
}
