import { Link } from "react-router-dom";
import {
  FaMobileAlt,
  FaLaptop,
  FaHeadphones,
  FaChevronDown,
} from "react-icons/fa";
import "./CategoryNav.scss";
const Category = () => {
  return (
    <nav className="category-nav">
      <Link to="/dien-thoai" className="category-nav__link">
        <FaMobileAlt />
        <span>Điện thoại</span>
      </Link>

      <Link to="/laptop" className="category-nav__link">
        <FaLaptop />
        <span>Laptop</span>
      </Link>

      <div className="category-nav__item category-nav__item--hasmenu">
        <Link to="/phu-kien" className="category-nav__link">
          <FaHeadphones />
          <span>Phụ kiện</span>
          <FaChevronDown className="category-nav__arrow" />
        </Link>
      </div>
    </nav>
  );
};

export default Category;
