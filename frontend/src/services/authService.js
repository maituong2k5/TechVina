import api from "./api";

/**
 * Service xử lý các chức năng xác thực tài khoản.
 */
const authService = {
  /**
   * Đăng nhập bằng email và mật khẩu.
   */
  dangNhap: async (email, matKhau) => {
    return api("/api/auth/login", {
      method: "POST",
      body: JSON.stringify({
        email,
        matKhau,
      }),
    });
  },

  /**
   * Đăng ký tài khoản khách hàng.
   */
  dangKy: async (duLieu) => {
    return api("/api/auth/register", {
      method: "POST",
      body: JSON.stringify(duLieu),
    });
  },

  /**
   * Làm mới Access Token bằng Refresh Token trong cookie.
   */
  lamMoiToken: async () => {
    return api("/api/auth/refresh", {
      method: "POST",
    });
  },

  /**
   * Đăng xuất và thu hồi Refresh Token.
   */
  dangXuat: async () => {
    return api("/api/auth/logout", {
      method: "POST",
    });
  },
};

export default authService;
