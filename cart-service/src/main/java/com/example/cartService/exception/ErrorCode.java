package com.example.cartService.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(1000, "You do not have permission", HttpStatus.FORBIDDEN),
    CATEGORY_NOT_FOUND(1001, "Category not found", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND(1002, "Product not found", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1003, "Unauthenticated" , HttpStatus.UNAUTHORIZED ),
    STOCK_NOT_ENOUGH(1004, "Stock not enough", HttpStatus.BAD_REQUEST),
    DEFECTIVE_PRODUCT(1005, "Product defective", HttpStatus.BAD_REQUEST)
    ;

    private final Integer code;
    private final String message;
    private final HttpStatusCode status;

    ErrorCode(Integer code, String message,HttpStatusCode status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
