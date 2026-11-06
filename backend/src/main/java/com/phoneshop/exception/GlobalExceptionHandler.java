
package com.phoneshop.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Xử lý tập trung các ngoại lệ phát sinh từ REST API.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Xử lý lỗi Validation từ @Valid.
	 *
	 * @param exception ngoại lệ chứa các lỗi Validation
	 * @return danh sách thông báo lỗi và HTTP 400
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> xuLyLoiValidation(MethodArgumentNotValidException exception) {

		// Tạo Map chứa tên trường và thông báo lỗi tương ứng.
		Map<String, String> errors = new HashMap<>();

		// Lấy toàn bộ lỗi Validation.
		exception.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});

		// Trả về HTTP 400 Bad Request.
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	/**
	 * Xử lý lỗi khi email hoặc mật khẩu đăng nhập không chính xác.
	 *
	 * @param exception thông tin lỗi xác thực
	 * @return HTTP 401 Unauthorized
	 */
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<Map<String, String>> xuLyLoiDangNhap(InvalidCredentialsException exception) {

		Map<String, String> response = Map.of("message", exception.getMessage());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}

	/**
	 * Xử lý lỗi dữ liệu đăng ký đã tồn tại.
	 *
	 * @param exception ngoại lệ chứa thông báo lỗi
	 * @return thông báo lỗi và HTTP 409
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> xuLyLoiDuLieuTrungLap(IllegalArgumentException exception) {

		// Tạo đối tượng chứa thông báo lỗi.
		Map<String, String> error = new HashMap<>();

		error.put("message", exception.getMessage());

		// Trả về HTTP 409 Conflict.
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

	/**
	 * Xử lý lỗi vi phạm ràng buộc dữ liệu trong database.
	 *
	 * Ví dụ: Email hoặc số điện thoại vi phạm UNIQUE constraint.
	 *
	 * @param exception ngoại lệ từ Spring Data/JPA
	 * @return thông báo lỗi và HTTP 409
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Map<String, String>> xuLyLoiRangBuocDuLieu(DataIntegrityViolationException exception) {

		// Tạo đối tượng chứa thông báo lỗi.
		Map<String, String> error = new HashMap<>();

		error.put("message", "Email hoặc số điện thoại đã được sử dụng");

		// Trả về HTTP 409 Conflict.
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

	/**
	 * Xử lý các lỗi hệ thống chưa được xử lý cụ thể.
	 *
	 * @param exception ngoại lệ phát sinh
	 * @return thông báo lỗi và HTTP 500
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> xuLyLoiHeThong(Exception exception) {

		// Tạo thông báo lỗi chung, không tiết lộ chi tiết nội bộ.
		Map<String, String> error = new HashMap<>();

		error.put("message", "Đã xảy ra lỗi hệ thống. Vui lòng thử lại sau.");

		// Trả về HTTP 500 Internal Server Error.
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

}
