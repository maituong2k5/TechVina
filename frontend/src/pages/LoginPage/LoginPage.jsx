import AuthLayout from "../../features/auth/components/AuthLayout";
import LoginForm from "../../features/auth/components/LoginForm";

/**
 * Trang đăng nhập TechVina.
 */
const LoginPage = () => {
  return (
    <AuthLayout>
      <LoginForm />
    </AuthLayout>
  );
};

export default LoginPage;
