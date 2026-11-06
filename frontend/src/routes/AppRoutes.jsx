import { Routes, Route } from "react-router-dom";

import App from "../App";

// Import các trang khách hàng
import HomePage from "../pages/HomePage/HomePage";
import ProductDetail from "../pages/ProductDetail/ProductDetail";
import LoginPage from "../pages/LoginPage/LoginPage";
// Layout dành cho nhân viên
import StaffLayout from "../layouts/staff/StaffLayout";
// Import Staff Dashboard
import StaffDashboard from "../pages/Staff/Dashboard/StaffDashboard";

import CartPage from "../pages/CartPage/CartPage";

const AppRoutes = () => {
  return (
    <Routes>
      {/* Trang đăng nhập - không sử dụng Layout chính */}
      <Route path="/login" element={<LoginPage />} />

      {/* ================= CUSTOMER ================= */}

      {/* Layout chung của website */}
      <Route path="/" element={<App />}>
        {/* Trang chủ */}
        <Route index element={<HomePage />} />

        {/* Giỏ hàng */}
        <Route path="cart" element={<CartPage />} />

        {/* Trang chi tiết sản phẩm */}
        <Route path="san-pham/:maSP/:maPB" element={<ProductDetail />} />
      </Route>

      {/* ================= STAFF ================= */}

      {/* Trang nhân viên - tạm thời dùng giao diện kiểm tra */}
      {/* ================= STAFF ================= */}

      <Route path="/staff" element={<StaffLayout />}>
        {/* Dashboard tổng quan */}
        <Route index element={<StaffDashboard />} />

        {/* Quản lý sản phẩm */}
        <Route path="products" element={<h2>Quản lý sản phẩm</h2>} />

        {/* Quản lý đơn hàng */}
        <Route path="orders" element={<h2>Quản lý đơn hàng</h2>} />
      </Route>

      {/* ================= ADMIN ================= */}

      {/* Trang quản trị - tạm thời dùng giao diện kiểm tra */}
      <Route
        path="/admin/*"
        element={<div>Trang quản trị hệ thống TechVina</div>}
      />
    </Routes>
  );
};

export default AppRoutes;
