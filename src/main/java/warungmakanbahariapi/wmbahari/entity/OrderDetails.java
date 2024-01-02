package warungmakanbahariapi.wmbahari.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order Orders;

    @OneToMany(mappedBy = "orderDetail")
    @JsonManagedReference
    private List<Menu> menuList;

}
