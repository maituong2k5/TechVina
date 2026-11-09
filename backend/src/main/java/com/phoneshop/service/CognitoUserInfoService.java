
package com.phoneshop.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

//Annotation dùng để đọc cấu hình từ application.properties.
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Service lấy thông tin người dùng từ Amazon Cognito.
 *
 * Chỉ sử dụng Access Token đã được Spring Security xác thực và kiểm tra thông
 * tin với Cognito.
 */
@Service
public class CognitoUserInfoService {

	// Địa chỉ API UserInfo được tạo từ cấu hình môi trường.
	private final URI userInfoUri;

	// HTTP Client gửi yêu cầu đến Amazon Cognito.
	private final HttpClient httpClient;

	// Chuyển dữ liệu JSON thành đối tượng Java.
	private final ObjectMapper objectMapper;

	/**
	 * Khởi tạo Service.
	 */
	public CognitoUserInfoService(ObjectMapper objectMapper, @Value("${cognito.domain}") String cognitoDomain) {

		this.objectMapper = objectMapper;

		// Khởi tạo HTTP Client.
		this.httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build();

		// Tạo endpoint UserInfo từ Domain Cognito.
		// Loại bỏ dấu / ở cuối Domain nếu có.
		String domain = cognitoDomain.replaceAll("/+$", "");

		this.userInfoUri = URI.create(domain + "/oauth2/userInfo");
	}

	/**
	 * Lấy thông tin người dùng từ Cognito.
	 *
	 * accessToken: Access Token đã xác thực. expectedSub: CognitoSub lấy từ JWT đã
	 * xác thực.
	 */
	public CognitoUserInfo getUserInfo(String accessToken, String expectedSub) {

		// Kiểm tra dữ liệu đầu vào.
		if (accessToken == null || accessToken.isBlank() || expectedSub == null || expectedSub.isBlank()) {
			throw new IllegalArgumentException("Thông tin xác thực Cognito không hợp lệ.");
		}

		// Tạo yêu cầu HTTP đến Cognito UserInfo.
		// Sử dụng endpoint UserInfo từ cấu hình môi trường.
		HttpRequest request = HttpRequest.newBuilder().uri(userInfoUri).timeout(Duration.ofSeconds(10))
				.header("Authorization", "Bearer " + accessToken).header("Accept", "application/json").GET().build();

		try {
			// Gửi yêu cầu và nhận dữ liệu.
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			// Kiểm tra Cognito có xử lý thành công không.
			if (response.statusCode() != 200) {
				throw new IllegalStateException("Không lấy được thông tin Cognito. HTTP: " + response.statusCode());
			}

			// Đọc thông tin JSON do Cognito trả về.
			JsonNode json = objectMapper.readTree(response.body());

			String sub = json.path("sub").asText("");
			String email = json.path("email").asText("").trim();

			// Cognito có thể trả email_verified dạng chuỗi.
			boolean emailVerified = Boolean.parseBoolean(json.path("email_verified").asText("false"));

			// Bảo đảm CognitoSub trùng với JWT đã xác thực.
			if (!expectedSub.equals(sub)) {
				throw new IllegalStateException("CognitoSub không khớp với Access Token.");
			}

			// Chỉ chấp nhận email đã xác minh.
			if (email.isBlank() || !emailVerified) {
				throw new IllegalStateException("Email Cognito chưa được xác minh.");
			}

			// Trả về thông tin đã kiểm tra.
			return new CognitoUserInfo(sub, email);

		} catch (InterruptedException e) {

			// Khôi phục trạng thái ngắt của Thread.
			Thread.currentThread().interrupt();

			throw new IllegalStateException("Yêu cầu Cognito bị gián đoạn.", e);

		} catch (IOException e) {

			throw new IllegalStateException("Không thể kết nối đến Cognito.", e);
		}
	}

	/**
	 * DTO lưu thông tin đã được xác minh từ Cognito.
	 */
	public record CognitoUserInfo(String sub, String email) {
	}
}
