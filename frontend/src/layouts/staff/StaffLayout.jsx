import { NavLink, Outlet } from "react-router-dom";

import "./StaffLayout.scss";

/**
 * Layout dành riêng cho nhân viên TechVina.
 * Bao gồm Sidebar, Header và khu vực nội dung chính.
 */
const StaffLayout = () => {
  // Danh sách chức năng dành cho nhân viên.
  const menuItems = [
    {
      label: "Tổng quan",
      path: "/staff",
      end: true,
    },
    {
      label: "Quản lý sản phẩm",
      path: "/staff/products",
    },
    {
      label: "Quản lý đơn hàng",
      path: "/staff/orders",
    },
  ];

  return (
    <div className="staff-layout">
      {/* Sidebar */}
      <aside className="staff-sidebar">
        <div className="staff-sidebar__brand">
          <h2>TechVina</h2>
          <span>Staff Portal</span>
        </div>

        <nav className="staff-sidebar__nav">
          <p className="staff-sidebar__heading">MENU</p>

          {menuItems.map((item) => (
            <NavLink
              key={item.path}
              to={item.path}
              end={item.end}
              className={({ isActive }) =>
                `staff-sidebar__link ${isActive ? "active" : ""}`
              }
            >
              {item.label}
            </NavLink>
          ))}
        </nav>
      </aside>

      {/* Khu vực nội dung */}
      <div className="staff-main">
        <header className="staff-header">
          <div>
            <h3>Trang nhân viên</h3>
            <p>Hệ thống quản lý TechVina</p>
          </div>

          <div className="staff-header__account">
            <span>Nhân viên</span>
          </div>
        </header>

        <main className="staff-content">
          <Outlet />
        </main>
      </div>
    </div>
  );
};

export default StaffLayout;
