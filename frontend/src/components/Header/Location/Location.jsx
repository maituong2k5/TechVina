import { FaLocationDot, FaChevronDown } from "react-icons/fa6";
import "./Location.scss";

const Location = () => {
  return (
    <button className="header__location">
      <FaLocationDot className="location__icon" />
      <span className="location__text">Hồ Chí Minh</span>
      <FaChevronDown className="location__arrow" />
    </button>
  );
};

export default Location;
