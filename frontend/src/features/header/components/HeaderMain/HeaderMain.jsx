import { Link } from "react-router-dom";
import { Container } from "react-bootstrap";
import { useCart } from "../../../../context/CartContext";
import { useAuth } from "../../../../context/AuthContext";

import {
  FaSearch,
  FaUser,
  FaShoppingCart,
  FaChevronDown,
  FaMapMarkerAlt,
} from "react-icons/fa";

import "./HeaderMain.scss";

const HeaderMain = () => {
  // Lấy tổng số lượng sản phẩm trong giỏ hàng.
  const { cartCount } = useCart();

  // Lấy trạng thái đăng nhập và thông tin tài khoản.
  const { user, isAuthenticated } = useAuth();
  return (
    <div className="header-main">
      <Container>
        <div className="header-main__content">
          {/* Logo thương hiệu */}
          <Link to="/" className="header-main__logo">
            Tech<span>Vina</span>
          </Link>
          <button type="submit" className="header-main__btn">
            Danh mục
            <FaChevronDown className="category-button__icon" />
          </button>

          {/* Thanh tìm kiếm */}
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
            <button className="header-main__location">
              <FaMapMarkerAlt className="ic-map" />
              <span>Hà Nội</span>
              <FaChevronDown className="ic-down" />
            </button>

            {/* Hiển thị tài khoản theo trạng thái đăng nhập */}
            {isAuthenticated && user ? (
              <Link to="/account" className="header-main__action">
                <FaUser />
                <span>{user.hoTen}</span>
              </Link>
            ) : (
              <Link to="/login" className="header-main__action">
                <FaUser />
                <span>Tài khoản</span>
              </Link>
            )}

            <Link to="/cart" className="header-main__action">
              <span className="header-main__cart-icon">
                <FaShoppingCart />

                {/* Chỉ hiển thị badge khi giỏ hàng có sản phẩm */}
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
