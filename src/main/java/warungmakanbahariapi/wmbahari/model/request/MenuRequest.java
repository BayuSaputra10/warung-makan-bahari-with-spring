package warungmakanbahariapi.wmbahari.model.request;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuRequest {

    private String name;

    private Long price;

    private String id;
}
