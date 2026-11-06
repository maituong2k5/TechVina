package com.phoneshop.config;

/**
 * Khai báo các vai trò người dùng trong hệ thống.
 *
 * Sử dụng hằng số để tránh viết sai tên vai trò khi cấu hình phân quyền.
 */
public final class RoleConfig {

	// Vai trò khách hàng.
	public static final String CUSTOMER = "CUSTOMER";

	// Vai trò nhân viên.
	public static final String STAFF = "STAFF";

	// Vai trò quản trị viên.
	public static final String ADMIN = "ADMIN";

	// Ngăn khởi tạo đối tượng từ class này.
	private RoleConfig() {
	}
}