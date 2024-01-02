package warungmakanbahariapi.wmbahari.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
