package warungmakanbahariapi.wmbahari.model.request;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenumixmaxRequest {

    private String name;

    private Long minPrice;
    private Long maxPrice;
}
