package pilot.instagram.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // USER
    DUPLICATED_ID("중복된 아이디입니다"),
    USER_NOT_FOUND("아이디를 찾을 수 없습니다"),

    // POST
    POST_NOT_FOUND("게시글을 찾을 수 없습니다."),
    UNAUTHORIZED_POST_DELETE("게시글 삭제 권한이 없습니다.");

    private final String message;
}
