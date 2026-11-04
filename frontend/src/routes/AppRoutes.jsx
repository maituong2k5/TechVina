import { Routes, Route } from "react-router-dom";

import App from "../App";
import HomePage from "../pages/HomePage/HomePage";
import ProductDetail from "../pages/ProductDetail/ProductDetail";

import CartPage from "../pages/CartPage/CartPage";

function AppRoutes() {
  return (
    <Routes>
      {/* Layout chung */}
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
}

export default AppRoutes;
