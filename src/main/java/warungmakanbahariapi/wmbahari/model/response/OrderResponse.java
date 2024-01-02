package warungmakanbahariapi.wmbahari.model.response;

import lombok.*;

import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private String transType;

    private Long productName;

    private String day;

    private String month;

    private String year;

    private Date startdate;

    private Date endDate;

    private String customerId;
}
