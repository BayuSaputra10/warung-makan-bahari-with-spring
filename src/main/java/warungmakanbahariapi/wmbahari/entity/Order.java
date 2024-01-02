package warungmakanbahariapi.wmbahari.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private String Id;

    @OneToOne
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "table",referencedColumnName = "Id")
    private Table tables;
    @ManyToOne
    @JoinColumn(name = "trans_type")
    private TransType transTypes;

    @Temporal(TemporalType.TIMESTAMP)
    private Date trans_dates;


}
