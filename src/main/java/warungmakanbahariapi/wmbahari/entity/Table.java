package warungmakanbahariapi.wmbahari.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@jakarta.persistence.Table(name = "table")
@Builder
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    @Column(unique = true)
    private String type;
}
