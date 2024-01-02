package warungmakanbahariapi.wmbahari.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@jakarta.persistence.Table(name = "table")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    @Column(unique = true)
    private String type;
}
