package enigma.todo.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

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

    public static ResponseEntity<ErrorResponse> error(List<String> errors, String message, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(ErrorResponse.builder()
                .message(message)
                .status(httpStatus.value())
                .errors(errors)
                .build()
        );
    }

    public static ResponseEntity<ErrorResponse> error(List<String> errors, String message) {
        return error(errors, message, HttpStatus.BAD_REQUEST);
    }

    public static  ResponseEntity<ErrorResponse> error(List<String> errors) {
        return error(errors, "Something went wrong");
    }
}
