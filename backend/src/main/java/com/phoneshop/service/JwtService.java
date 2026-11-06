package com.phoneshop.service;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * Service chuyên xử lý các thao tác liên quan đến JWT.
 */
@Service
public class JwtService {

	// Khóa bí mật dùng để ký và xác thực JWT.
	private final SecretKey secretKey;

	// Thời hạn Access Token (milliseconds).
	private final long expiration;

	/**
	 * Constructor Injection kết hợp đọc cấu hình từ application.properties.
	 */
	public JwtService(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long expiration) {

		// Khởi tạo khóa bí mật JWT thông qua phương thức kiểm tra riêng.
		this.secretKey = taoSecretKey(secret);

		// Lưu thời hạn Access Token.
		this.expiration = expiration;

		// Đảm bảo thời hạn Token phải lớn hơn 0.
		if (expiration <= 0) {
			throw new IllegalStateException("Thời hạn JWT phải lớn hơn 0");
		}
	}

	/**
	 * Tạo Access Token cho tài khoản sau khi đăng nhập thành công.
	 *
	 * @param maTaiKhoan mã khách hàng hoặc nhân viên
	 * @param email      email đăng nhập
	 * @param vaiTro     vai trò của tài khoản
	 * @return chuỗi JWT
	 */
	public String taoAccessToken(Integer maTaiKhoan, String email, String vaiTro) {

		// Lấy thời điểm hiện tại.
		long thoiGianHienTai = System.currentTimeMillis();

		// Tạo JWT chứa thông tin tài khoản.
		return io.jsonwebtoken.Jwts.builder()

				// Email được sử dụng làm Subject của Token.
				.subject(email)

				// Lưu mã tài khoản vào Claim.
				.claim("maTaiKhoan", maTaiKhoan)

				// Lưu vai trò để phục vụ phân quyền.
				.claim("vaiTro", vaiTro)

				// Thời điểm phát hành Token.
				.issuedAt(new java.util.Date(thoiGianHienTai))

				// Thời điểm Token hết hạn.
				.expiration(new java.util.Date(thoiGianHienTai + expiration))

				// Ký Token bằng khóa bí mật.
				.signWith(secretKey, Jwts.SIG.HS256)

				// Chuyển thành chuỗi JWT.
				.compact();
	}

	/**
	 * Trích xuất email (Subject) từ JWT.
	 *
	 * @param token chuỗi JWT cần đọc
	 * @return email của tài khoản
	 */
	public String trichXuatEmail(String token) {

		// Lấy Claims sau khi JWT được xác thực.
		Claims claims = layClaims(token);

		// Trả về email được lưu trong Subject.
		return claims.getSubject();
	}

	/**
	 * Kiểm tra JWT có hợp lệ và còn thời hạn hay không.
	 *
	 * @param token chuỗi JWT cần kiểm tra
	 * @return true nếu Token hợp lệ, false nếu không hợp lệ
	 */
	public boolean kiemTraTokenHopLe(String token) {

		try {
			// Thử phân tích và xác thực JWT.
			layClaims(token);

			return true;

		} catch (io.jsonwebtoken.JwtException | IllegalArgumentException exception) {

			// Token sai chữ ký, hết hạn hoặc không đúng định dạng.
			return false;
		}
	}

	/**
	 * Trích xuất mã tài khoản từ JWT.
	 *
	 * @param token chuỗi JWT cần đọc
	 * @return mã tài khoản
	 */
	public Integer trichXuatMaTaiKhoan(String token) {

		// Lấy Claims sau khi JWT được xác thực.
		Claims claims = layClaims(token);

		// Trả về mã tài khoản.
		return claims.get("maTaiKhoan", Integer.class);
	}

	/**
	 * Trích xuất vai trò tài khoản từ JWT.
	 *
	 * @param token chuỗi JWT cần đọc
	 * @return tên vai trò của tài khoản
	 */
	public String trichXuatVaiTro(String token) {

		// Lấy Claims sau khi JWT được xác thực.
		Claims claims = layClaims(token);

		// Trả về tên vai trò.
		return claims.get("vaiTro", String.class);
	}

	/**
	 * Phân tích JWT và lấy toàn bộ Claims sau khi xác thực chữ ký.
	 *
	 * @param token chuỗi JWT cần phân tích
	 * @return toàn bộ dữ liệu Claims trong JWT
	 */
	private Claims layClaims(String token) {

		// Xác thực chữ ký và phân tích nội dung JWT.
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
	}

	/**
	 * Chuyển chuỗi Base64 thành SecretKey dùng để ký JWT.
	 *
	 * @param secret khóa bí mật lấy từ biến môi trường
	 * @return SecretKey hợp lệ
	 */
	private SecretKey taoSecretKey(String secret) {

		// Kiểm tra khóa bí mật không được để trống.
		if (secret == null || secret.isBlank()) {
			throw new IllegalStateException("JWT_SECRET chưa được cấu hình");
		}

		try {
			// Giải mã chuỗi Base64 thành mảng byte.
			byte[] keyBytes = Base64.getDecoder().decode(secret);

			// Tạo SecretKey từ khóa bí mật.
			return Keys.hmacShaKeyFor(keyBytes);

		} catch (IllegalArgumentException exception) {

			throw new IllegalStateException("JWT_SECRET không đúng định dạng Base64 hoặc không đủ độ dài", exception);
		}
	}
}