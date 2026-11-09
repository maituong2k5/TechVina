import { Link } from "react-router-dom";
import { Container, Dropdown } from "react-bootstrap";

// Context quản lý giỏ hàng và tài khoản TechVina.
import { useCart } from "../../../../context/CartContext";
import { useAuth } from "../../../../context/AuthContext";

// Hook quản lý đăng nhập Amazon Cognito.
import { useAuth as useCognitoAuth } from "react-oidc-context";

// Cấu hình Amazon Cognito.
import { cognitoAuthConfig } from "../../../../services/authConfig.js";

import {
  FaSearch,
  FaUser,
  FaShoppingCart,
  FaChevronDown,
  FaMapMarkerAlt,
  FaSignOutAlt,
} from "react-icons/fa";

import "./HeaderMain.scss";

const HeaderMain = () => {
  // Lấy số lượng sản phẩm trong giỏ hàng.
  const { cartCount } = useCart();

  // Lấy thông tin tài khoản đã đồng bộ từ Cognito.
  const { user, isAuthenticated } = useAuth();

  // Lấy phương thức quản lý phiên đăng nhập Cognito.
  const cognitoAuth = useCognitoAuth();

  /**
   * Kiểm tra liên kết giữa tài khoản Cognito
   * và tài khoản trong MySQL.
   */

  const testAccountLink = async () => {
    // Lấy Access Token của tài khoản Cognito hiện tại.
    const token = cognitoAuth.user?.access_token;

    if (!token) {
      console.log("Chưa đăng nhập Cognito.");
      return;
    }

    try {
      // Truy vấn tài khoản MySQL bằng CognitoSub.
      const response = await fetch(
        `${import.meta.env.VITE_API_BASE_URL}/api/account-link/me`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
      );

      // Hiển thị trạng thái HTTP.
      console.log("Account Link Status:", response.status);

      // Hiển thị tài khoản MySQL được liên kết.
      const result = await response.json();
      console.log("Account Link Result:", result);
    } catch (error) {
      console.error("Lỗi kiểm tra liên kết MySQL:", error);
    }
  };

  /**
   * Đăng xuất tài khoản khỏi Amazon Cognito.
   *
   * 1. Xóa phiên OIDC trong ReactJS.
   * 2. Xóa Access Token của cơ chế JWT cũ nếu còn.
   * 3. Chuyển đến Cognito để kết thúc phiên đăng nhập.
   * 4. Quay lại trang chủ TechVina.
   */
  const handleLogout = async () => {
    try {
      // Xóa phiên đăng nhập Cognito ở ReactJS.
      await cognitoAuth.removeUser();

      // Xóa token cũ nếu còn trong Local Storage.
      localStorage.removeItem("accessToken");

      // Lấy Domain Cognito từ biến môi trường để có thể đổi tài khoản AWS.
      const cognitoDomain = import.meta.env.VITE_COGNITO_DOMAIN;

      // Tạo đường dẫn đăng xuất mà không viết cố định Domain Cognito.
      const logoutUrl = new URL("/logout", cognitoDomain);

      // Xác định App Client cần đăng xuất.
      logoutUrl.searchParams.set("client_id", cognitoAuthConfig.client_id);

      // Địa chỉ quay về sau khi đăng xuất.
      logoutUrl.searchParams.set(
        "logout_uri",
        cognitoAuthConfig.post_logout_redirect_uri,
      );

      // Kết thúc phiên đăng nhập tại Cognito.
      window.location.assign(logoutUrl.toString());
    } catch (error) {
      console.error("Lỗi đăng xuất Cognito:", error);
    }
  };

  return (
    <div className="header-main">
      <Container>
        <div className="header-main__content">
          {/* Logo thương hiệu TechVina */}
          <Link to="/" className="header-main__logo">
            Tech<span>Vina</span>
          </Link>

          {/* Nút danh mục sản phẩm */}
          <button type="button" className="header-main__btn">
            Danh mục
            <FaChevronDown className="category-button__icon" />
          </button>

          {/* Thanh tìm kiếm sản phẩm */}
          <form className="header-main__search">
            <input
              type="text"
              placeholder="Bạn muốn tìm sản phẩm nào?"
              aria-label="Tìm kiếm sản phẩm"
            />

            <button type="submit" aria-label="Tìm kiếm">
              <FaSearch />
            </button>
          </form>

          {/* Các chức năng người dùng */}
          <div className="header-main__actions">
            {/* Vị trí cửa hàng */}
            <button type="button" className="header-main__location">
              <FaMapMarkerAlt className="ic-map" />
              <span>Hà Nội</span>
              <FaChevronDown className="ic-down" />
            </button>

            {/* Kiểm tra trạng thái đăng nhập Cognito */}
            {isAuthenticated && user ? (
              // Hiển thị menu khi người dùng đã đăng nhập.
              <Dropdown align="end">
                {/* Nút mở menu tài khoản */}
                <Dropdown.Toggle
                  as="button"
                  type="button"
                  className="header-main__action"
                  style={{
                    background: "transparent",
                    border: "none",
                    display: "flex",
                    alignItems: "center",
                    gap: "8px",
                    cursor: "pointer",
                  }}
                >
                  <FaUser />
                  <span>{user.hoTen}</span>
                </Dropdown.Toggle>

                {/* Nội dung menu tài khoản */}
                <Dropdown.Menu>
                  {/* Email tài khoản Cognito */}
                  <Dropdown.Header>
                    <strong>{user.email}</strong>
                  </Dropdown.Header>

                  {/* Vai trò của tài khoản */}
                  <Dropdown.ItemText>
                    Vai trò: <strong>{user.vaiTro || "Chưa có nhóm"}</strong>
                  </Dropdown.ItemText>

                  <Dropdown.Divider />

                  {/* Chuyển đến trang tài khoản */}
                  <Dropdown.Item as={Link} to="/account">
                    Trang cá nhân
                  </Dropdown.Item>

                  {/* Kiểm tra liên kết Cognito với MySQL. */}
                  <Dropdown.Item as="button" onClick={testAccountLink}>
                    Kiểm tra liên kết MySQL
                  </Dropdown.Item>
                  <Dropdown.Divider />

                  {/* Đăng xuất Amazon Cognito */}
                  <Dropdown.Item
                    as="button"
                    onClick={handleLogout}
                    className="text-danger"
                  >
                    <FaSignOutAlt style={{ marginRight: "8px" }} />
                    Đăng xuất
                  </Dropdown.Item>
                </Dropdown.Menu>
              </Dropdown>
            ) : (
              // Chưa đăng nhập: hiển thị nút tài khoản.
              <Link to="/login" className="header-main__action">
                <FaUser />
                <span>Tài khoản</span>
              </Link>
            )}

            {/* Giỏ hàng */}
            <Link to="/cart" className="header-main__action">
              <span className="header-main__cart-icon">
                <FaShoppingCart />

                {/* Chỉ hiển thị số lượng khi có sản phẩm */}
                {cartCount > 0 && (
                  <span className="header-main__cart-count">
                    {cartCount > 99 ? "99+" : cartCount}
                  </span>
                )}
              </span>

              <span>Giỏ hàng</span>
            </Link>
          </div>
        </div>
      </Container>
    </div>
  );
};

export default HeaderMain;
