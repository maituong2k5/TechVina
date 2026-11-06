import { useState } from "react";
import { useNavigate } from "react-router-dom";

import authService from "../../../services/authService";
import { useAuth } from "../../../context/AuthContext";
import "./../styles/Auth.scss";

/**
 * Component hiển thị biểu mẫu đăng nhập.
 */
const LoginForm = () => {
  const navigate = useNavigate();
  const { dangNhap } = useAuth();

  // Quản lý dữ liệu nhập từ người dùng.
  const [email, setEmail] = useState("");
  const [matKhau, setMatKhau] = useState("");

  // Quản lý trạng thái xử lý đăng nhập.
  const [dangXuLy, setDangXuLy] = useState(false);
  const [loiDangNhap, setLoiDangNhap] = useState("");

  /**
   * Gửi thông tin đăng nhập đến Backend.
   */
  const handleSubmit = async (event) => {
    event.preventDefault();

    setLoiDangNhap("");
    setDangXuLy(true);

    try {
      // Gọi API đăng nhập.
      const response = await authService.dangNhap(email, matKhau);

      // Lưu Access Token để sử dụng cho các API cần xác thực.
      if (!response.accessToken) {
        throw new Error("Backend không trả về Access Token.");
      }

      dangNhap(response);

      // Điều hướng theo vai trò tài khoản.
      if (response.vaiTro === "ADMIN") {
        navigate("/admin");
      } else if (response.vaiTro === "STAFF") {
        navigate("/staff");
      } else {
        navigate("/");
      }
    } catch (error) {
      console.error("Lỗi đăng nhập:", error);

      setLoiDangNhap(error.message || "Đăng nhập thất bại. Vui lòng thử lại.");
    } finally {
      setDangXuLy(false);
    }
  };

  return (
    <div className="auth-form">
      <h2 className="auth-form__title">Đăng nhập</h2>

      <p className="auth-form__description">
        Chào mừng bạn quay trở lại TechVina!
      </p>

      <form onSubmit={handleSubmit}>
        {/* Email đăng nhập */}
        <div className="auth-form__group">
          <label htmlFor="email">Email</label>

          <input
            type="email"
            id="email"
            name="email"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            placeholder="Nhập email của bạn"
            autoComplete="username"
            required
          />
        </div>

        {/* Mật khẩu đăng nhập */}
        <div className="auth-form__group">
          <label htmlFor="password">Mật khẩu</label>

          <input
            type="password"
            id="password"
            name="password"
            value={matKhau}
            onChange={(event) => setMatKhau(event.target.value)}
            placeholder="Nhập mật khẩu"
            autoComplete="current-password"
            required
          />
        </div>

        {/* Ghi nhớ đăng nhập */}
        <div className="auth-form__remember">
          <label>
            <input type="checkbox" />
            Ghi nhớ đăng nhập
          </label>

          <a href="/quen-mat-khau">Quên mật khẩu?</a>
        </div>

        {/* Hiển thị lỗi đăng nhập */}
        {loiDangNhap && (
          <p className="auth-form__error" role="alert">
            {loiDangNhap}
          </p>
        )}

        <button type="submit" className="auth-form__submit" disabled={dangXuLy}>
          {dangXuLy ? "Đang đăng nhập..." : "Đăng nhập"}
        </button>
      </form>

      <p className="auth-form__footer">
        Chưa có tài khoản?
        <a href="/dang-ky"> Đăng ký ngay</a>
      </p>
    </div>
  );
};

export default LoginForm;
