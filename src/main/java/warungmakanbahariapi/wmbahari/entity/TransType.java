package warungmakanbahariapi.wmbahari.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trans_type")
public class TransType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    @Column(unique = true)
    private String type;

}
