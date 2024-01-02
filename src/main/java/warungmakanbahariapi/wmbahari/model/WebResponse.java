package warungmakanbahariapi.wmbahari.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebResponse<T> {

    private String status;
    private String message;
    private T data;
}
