import { Link } from "react-router-dom";

import "./Breadcrumb.scss";

/**
 * Component hiển thị đường dẫn điều hướng.
 *
 * @param {Array} items - Danh sách các cấp điều hướng.
 */
const Breadcrumb = ({ items = [] }) => {
  return (
    <nav className="breadcrumb" aria-label="Breadcrumb">
      <ol className="breadcrumb__list">
        {items.map((item, index) => {
          const isLastItem = index === items.length - 1;

          return (
            <li className="breadcrumb__item" key={`${item.label}-${index}`}>
              {/* Phần tử cuối là trang hiện tại */}
              {isLastItem ? (
                <span className="breadcrumb__current" aria-current="page">
                  {item.label}
                </span>
              ) : (
                <Link to={item.to || "/"}>{item.label}</Link>
              )}

              {/* Dấu phân cách giữa các cấp */}
              {!isLastItem && (
                <span className="breadcrumb__separator">&gt;</span>
              )}
            </li>
          );
        })}
      </ol>
    </nav>
  );
};

export default Breadcrumb;
