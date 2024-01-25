package org.hh99.gradation.message;

public enum JwtErrorMessage {
	EXIST_TOKEN_ERROR_MESSAGE("토큰을 찾을 수 없습니다."),
	EXIST_USER_ERROR_MESSAGE("존재하지 않는 사용자입니다."),
	PASSWORD_MISMATCH_ERROR_MESSAGE("로그인에 실패하였습니다."),
	EMAIL_FORMAT_ERROR_MESSAGE("올바른 이메일 형식이 아닙니다."),
	PASSWORD_VALIDATION_ERROR_MESSAGE("비밀번호는 최소 8자 이상, 15자 이하이며 알파벳 대소문자, 숫자, 특수문자로 구성되어야 합니다."),
	AUTH_EXCEPTION_MESSAGE("권한이 없습니다."),
	INVALID_JWT_ERROR_MESSAGE("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다."),
	EXPIRED_JWT_ERROR_MESSAGE("로그인을 다시 해주세요."),
	UNSUPPORTED_JWT_ERROR_MESSAGE("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다."),
	NULL_JWT_ERROR_MESSAGE("로그인을 다시 해주세요."),
	EMPTY_JWT_ERROR_MESSAGE("JWT claims is empty, 잘못된 JWT 토큰 입니다.");

	private final String JwtErrorMessage;

	JwtErrorMessage(String errorMessage) {
		this.JwtErrorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return "<script> alert('" + JwtErrorMessage + "'); location.href = '/'; </script>)";
	}
}

