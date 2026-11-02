import { Link } from "react-router-dom";
import { Container } from "react-bootstrap";
import {
  FaSearch,
  FaUser,
  FaShoppingCart,
  FaChevronDown,
  FaMapMarkerAlt,
} from "react-icons/fa";

import "./HeaderMain.scss";

const HeaderMain = () => {
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

            <Link to="/account" className="header-main__action">
              <FaUser />
              <span>Tài khoản</span>
            </Link>

            <Link to="/cart" className="header-main__action">
              <FaShoppingCart />
              <span>Giỏ hàng</span>
            </Link>
          </div>
        </div>
      </Container>
    </div>
  );
};

export default HeaderMain;
