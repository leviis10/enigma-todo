package enigma.todo.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {
    public static <T> ResponseEntity<SuccessResponse<T>> success(T data, String message, HttpStatus httpStatus) {
        SuccessResponse<T> response = SuccessResponse.<T>builder()
                .data(data)
                .status(httpStatus.value())
                .message(message)
                .build();

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    public static <T> ResponseEntity<SuccessResponse<T>> success(T data, String message) {
        SuccessResponse<T> response = SuccessResponse.<T>builder()
                .data(data)
                .status(HttpStatus.OK.value())
                .message(message)
                .build();

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    public static <T> ResponseEntity<SuccessResponse<T>> success(T data, HttpStatus httpStatus) {
        SuccessResponse<T> response = SuccessResponse.<T>builder()
                .data(data)
                .status(httpStatus.value())
                .message(httpStatus.getReasonPhrase())
                .build();

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    public static <T> ResponseEntity<SuccessResponse<T>> success(T data) {
        SuccessResponse<T> response = SuccessResponse.<T>builder()
                .data(data)
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .build();

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
