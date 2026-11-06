package com.phoneshop.exception;

/**
 * Exception được sử dụng khi email hoặc mật khẩu không chính xác.
 */
public class InvalidCredentialsException extends RuntimeException {

	public InvalidCredentialsException(String message) {
		super(message);
	}
}