import "./../styles/Auth.scss";

/**
 * Component hiển thị biểu mẫu đăng nhập.
 */
const LoginForm = () => {
  /**
   * Tạm ngăn hành vi GET mặc định của trình duyệt.
   * Bước tiếp theo sẽ thay bằng xử lý đăng nhập qua API POST.
   */
  const handleSubmit = (event) => {
    event.preventDefault();
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
            placeholder="Nhập email của bạn"
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
            placeholder="Nhập mật khẩu"
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

        <button type="submit" className="auth-form__submit">
          Đăng nhập
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
