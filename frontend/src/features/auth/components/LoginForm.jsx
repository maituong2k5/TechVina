import { useState } from "react";
import { useAuth as useCognitoAuth } from "react-oidc-context";
import "./../styles/Auth.scss";

/**
 * Biểu mẫu đăng nhập TechVina sử dụng Amazon Cognito.
 *
 * Người dùng nhập email và mật khẩu trên trang Cognito,
 * không gửi mật khẩu đến Spring Boot như cơ chế JWT cũ.
 */
const LoginForm = () => {
  // Lấy trạng thái và chức năng đăng nhập Cognito.
  const cognitoAuth = useCognitoAuth();

  // Quản lý thông báo lỗi.
  const [loiDangNhap, setLoiDangNhap] = useState("");

  // Trạng thái xử lý đăng nhập.
  const dangXuLy =
    cognitoAuth.isLoading || cognitoAuth.activeNavigator === "signinRedirect";

  /**
   * Chuyển hướng người dùng đến trang đăng nhập Cognito.
   *
   * Sau khi đăng nhập thành công, AuthProvider trong
   * main.jsx xử lý Authorization Code và nhận token.
   */
  const handleLogin = async () => {
    try {
      setLoiDangNhap("");

      // Bắt đầu quá trình đăng nhập Cognito.
      await cognitoAuth.signinRedirect();
    } catch (error) {
      console.error("Lỗi đăng nhập Cognito:", error);

      setLoiDangNhap("Không thể mở trang đăng nhập. Vui lòng thử lại.");
    }
  };

  return (
    <div className="auth-form">
      <h2 className="auth-form__title">Đăng nhập</h2>

      <p className="auth-form__description">
        Chào mừng bạn quay trở lại TechVina!
      </p>

      {/* Hiển thị lỗi nếu đăng nhập không thành công. */}
      {(loiDangNhap || cognitoAuth.error) && (
        <p className="auth-form__error" role="alert">
          {loiDangNhap || "Xác thực không thành công. Vui lòng thử lại."}
        </p>
      )}

      {/* Nút đăng nhập chính sử dụng Amazon Cognito. */}
      <button
        type="button"
        className="auth-form__submit"
        onClick={handleLogin}
        disabled={dangXuLy}
      >
        {dangXuLy ? "Đang xử lý..." : "Đăng nhập với Amazon Cognito"}
      </button>

      {/* Người dùng có thể đăng ký trên trang Cognito. */}
      <p className="auth-form__footer">
        Chưa có tài khoản? Bạn có thể đăng ký trên trang đăng nhập Cognito.
      </p>
    </div>
  );
};

export default LoginForm;
