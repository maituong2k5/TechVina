import { Routes, Route } from "react-router-dom";

import App from "../App";
import HomePage from "../pages/HomePage/HomePage";
import ProductDetail from "../pages/ProductDetail/ProductDetail";
import LoginPage from "../pages/LoginPage/LoginPage";

import CartPage from "../pages/CartPage/CartPage";

const AppRoutes = () => {
  return (
    <Routes>
      {/* Trang đăng nhập - không sử dụng Layout chính */}
      <Route path="/account" element={<LoginPage />} />

      {/* Layout chung của website */}
      <Route path="/" element={<App />}>
        {/* Trang chủ */}
        <Route index element={<HomePage />} />

        {/* Giỏ hàng */}
        <Route path="cart" element={<CartPage />} />

        {/* Trang chi tiết sản phẩm */}
        <Route path="san-pham/:maSP/:maPB" element={<ProductDetail />} />
      </Route>
    </Routes>
  );
};

export default AppRoutes;
