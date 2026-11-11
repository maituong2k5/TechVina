import "./../styles/Auth.scss";

/**
 * Layout dùng chung cho đăng nhập và đăng ký.
 */
const AuthLayout = ({ children }) => {
  return (
    <main className="auth-page">
      <section className="auth-container">
        {/* Bên trái: Banner TechVina */}
        <div className="auth-banner">
          <img
            src="/images/banners/auth-banner.png"
            alt="Banner giới thiệu TechVina"
          />
        </div>

        {/* Bên phải: Form đăng nhập hoặc đăng ký */}
        <div className="auth-form-wraper">{children}</div>
      </section>
    </main>
  );
};

export default AuthLayout;
