package warungmakanbahariapi.wmbahari.model.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuResponse {

    private String name;

    private Long price;
}
