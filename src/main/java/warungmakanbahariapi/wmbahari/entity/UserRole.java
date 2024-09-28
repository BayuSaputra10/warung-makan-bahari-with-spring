package warungmakanbahariapi.wmbahari.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "m_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;

    @Enumerated(EnumType.STRING)
    private ERole role;
}
