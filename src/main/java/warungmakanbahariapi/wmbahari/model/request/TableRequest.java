package warungmakanbahariapi.wmbahari.model.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableRequest {
    private String name;
    private Long minPrice;
    private Long maxPrice;

}
