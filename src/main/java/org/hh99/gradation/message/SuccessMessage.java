package org.hh99.gradation.message;

public enum SuccessMessage {
    JOIN_SUCCESS_MESSAGE("회원가입이 완료되었습니다."),
    LOGIN_SUCCESS_MESSAGE("로그인이 완료되었습니다."),
    ADD_CART_SUCCESS_MESSAGE("장바구니에 추가 되었습니다."),
    UPDATE_CART_SUCCESS_MESSAGE("장바구니의 상품 개수가 수정 되었습니다."),
    DELETE_CART_SUCCESS_MESSAGE("장바구니 상품 삭제되었습니다.");

    private final String successMessage;

    SuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getSuccessMessage() {
        return "[SUCCESS] " + successMessage;
    }
}