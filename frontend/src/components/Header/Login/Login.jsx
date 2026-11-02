import { FiUser } from "react-icons/fi";
import { Link } from "react-router-dom";
import "./Login.scss";

const Login = () => {
  return (
    <div className="header__profile">
      <Link to="/dang-nhap" className="header__profile-link">
        <FiUser className="header__profile-icon" />

        <span className="header__profile-text">Đăng nhập</span>
      </Link>
    </div>
  );
};

export default Login;
