package warungmakanbahariapi.wmbahari.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;

    private String name;
    @Column(unique = true)
    private String phoneNumber;

    @OneToOne
    private User user;

}
