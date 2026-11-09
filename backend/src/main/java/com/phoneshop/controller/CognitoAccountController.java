
package com.phoneshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.phoneshop.service.CognitoAccountService;
import com.phoneshop.service.CognitoAccountService.AccountLinkResult;
import com.phoneshop.service.CognitoUserInfoService;
import com.phoneshop.service.CognitoUserInfoService.CognitoUserInfo;

/**
 * Controller liên kết tài khoản Amazon Cognito với dữ liệu MySQL của TechVina.
 */
@RestController
@RequestMapping("/api/account-link")
public class CognitoAccountController {

	// Service xử lý liên kết tài khoản MySQL.
	private final CognitoAccountService cognitoAccountService;

	// Service lấy thông tin đã xác minh từ Cognito.
	private final CognitoUserInfoService cognitoUserInfoService;

	/**
	 * Khởi tạo Controller bằng Dependency Injection.
	 */
	public CognitoAccountController(CognitoAccountService cognitoAccountService,
			CognitoUserInfoService cognitoUserInfoService) {

		this.cognitoAccountService = cognitoAccountService;
		this.cognitoUserInfoService = cognitoUserInfoService;
	}

	/**
	 * API kiểm tra tài khoản MySQL đang liên kết.
	 *
	 * GET /api/account-link/me
	 */
	@GetMapping("/me")
	public AccountLinkResult getMyAccount(@AuthenticationPrincipal Jwt jwt) {

		// Kiểm tra người dùng đã xác thực chưa.
		if (jwt == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Yêu cầu đăng nhập bằng Amazon Cognito.");
		}

		// Lấy CognitoSub từ Access Token đã xác thực.
		String cognitoSub = jwt.getSubject();

		// Truy vấn tài khoản tương ứng trong MySQL.
		return cognitoAccountService.findAccount(cognitoSub);
	}

	/**
	 * API tạo khách hàng MySQL từ tài khoản Cognito.
	 *
	 * POST /api/account-link/customer
	 *
	 * Chỉ cho phép người dùng thuộc nhóm CUSTOMER. Không cho phép ADMIN hoặc STAFF
	 * sử dụng API này.
	 */
	@PostMapping("/customer")
	public AccountLinkResult createCustomer(@AuthenticationPrincipal Jwt jwt) {

		// Bước 1: Kiểm tra Access Token.
		if (jwt == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Yêu cầu đăng nhập bằng Amazon Cognito.");
		}

		// Bước 2: Đọc danh sách nhóm từ JWT đã xác thực.
		List<String> groups = jwt.getClaimAsStringList("cognito:groups");

		// Tài khoản không thuộc nhóm nào thì từ chối.
		if (groups == null || groups.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Tài khoản chưa được cấp quyền CUSTOMER.");
		}

		// Bước 3: Kiểm tra quyền CUSTOMER.
		boolean isCustomer = groups.contains("CUSTOMER");

		// Không cho phép ADMIN hoặc STAFF tự tạo khách hàng.
		boolean isAdminOrStaff = groups.contains("ADMIN") || groups.contains("STAFF");

		if (!isCustomer || isAdminOrStaff) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Chỉ tài khoản CUSTOMER được sử dụng API này.");
		}

		// Bước 4: Lấy CognitoSub từ JWT đã xác thực.
		String cognitoSub = jwt.getSubject();

		// Bước 5: Lấy email từ Cognito UserInfo.
		// Service sẽ kiểm tra:
		// - Email đã được xác minh.
		// - CognitoSub khớp với Access Token.
		CognitoUserInfo userInfo = cognitoUserInfoService.getUserInfo(jwt.getTokenValue(), cognitoSub);

		// Bước 6: Tạo khách hàng nếu chưa tồn tại.
		// Không liên kết tài khoản MySQL cũ chỉ bằng email.
		return cognitoAccountService.createCustomerIfMissing(userInfo.sub(), userInfo.email());
	}
}
