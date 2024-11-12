package pilot.instagram.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // USER
    DUPLICATED_ID("중복된 아이디입니다"),
    USER_NOT_FOUND("아이디를 찾을 수 없습니다");

    // POST

    private final String message;
}
