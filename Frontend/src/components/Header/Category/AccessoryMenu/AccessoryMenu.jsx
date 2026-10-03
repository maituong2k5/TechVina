import { LuHeadphones, LuChevronDown } from "react-icons/lu";
import { Link } from "react-router-dom";
import AccessoryDropdown from "./AccessoryDropdown";

const AccessoryMenu = () => {
  return (
    <div className="category-nav__item category-nav__item--hasmenu">
      {/* Menu chính Phụ kiện */}
      <Link to="/phu-kien" className="category-nav__link">
        <LuHeadphones />
        <span>Phụ kiện</span>
        <LuChevronDown className="category-nav__arrow" />
      </Link>

      {/* Menu dropdown */}
      <AccessoryDropdown />
    </div>
  );
};

export default AccessoryMenu;
