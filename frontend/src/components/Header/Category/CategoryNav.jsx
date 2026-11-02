import { Link } from "react-router-dom";
import {
  LuSmartphone,
  LuLaptop,
  LuHeadphones,
  LuWatch,
  LuClock3,
  LuTablet,
  LuMonitor,
  LuChevronDown,
} from "react-icons/lu";
import "./CategoryNav.scss";
import IconOlePhone from "../../../assets/may-cu-24x24.png";
const Category = () => {
  return (
    <nav className="category-nav">
      <Link to="/dien-thoai" className="category-nav__link">
        <LuSmartphone />
        <span>Điện thoại</span>
      </Link>

      <Link to="/laptop" className="category-nav__link">
        <LuLaptop />
        <span>Laptop</span>
      </Link>

      <div className="category-nav__item category-nav__item--hasmenu">
        <Link to="/phu-kien" className="category-nav__link">
          <LuHeadphones />
          <span>Phụ kiện</span>
          <LuChevronDown className="category-nav__arrow" />
        </Link>
      </div>

      <Link to="/dong-ho-thong-minh-ldp" className="category-nav__link">
        <LuWatch />
        <span>Smartwatch</span>
      </Link>

      <div className="category-nav__item category-nav__item--hasmenu">
        <Link to="/may-tinh-bang" className="category-nav__link">
          <LuTablet />
          <span>Tablet</span>
          <LuChevronDown className="category-nav__arrow" />
        </Link>
      </div>

      <div className="category-nav__item category-nav__item--hasmenu">
        <Link to="/may-doi-tra" className="category-nav__link">
          <img src={IconOlePhone} />
          <span>Máy cũ</span>
          <LuChevronDown className="category-nav__arrow" />
        </Link>
      </div>

      <div className="category-nav__item category-nav__item--hasmenu">
        <Link to="/pc-may-in" className="category-nav__link">
          <LuMonitor />
          <span>Màn hình, Máy in</span>
          <LuChevronDown className="category-nav__arrow" />
        </Link>
      </div>
    </nav>
  );
};

export default Category;
