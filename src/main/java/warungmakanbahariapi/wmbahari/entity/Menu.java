package warungmakanbahariapi.wmbahari.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;

    private String name;

    private Long price;

    @ManyToOne
    @JoinTable(name = "orderDetail_id")
    @JsonBackReference
    private OrderDetails orderDetail;
}
