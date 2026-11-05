import React, { createContext, useContext, useState } from "react";

// Tạo Context để chia sẻ dữ liệu giỏ hàng giữa các component.
const CartContext = createContext(null);

/**
 * Quản lý trạng thái giỏ hàng cho toàn bộ ứng dụng.
 */
export const CartProvider = ({ children }) => {
  // Danh sách sản phẩm trong giỏ hàng.
  const [cartItems, setCartItems] = useState([]);

  /**
   * Thêm sản phẩm vào giỏ hàng.
   * Nếu phiên bản đã tồn tại thì cộng dồn số lượng.
   */
  const addToCart = (product, quantity = 1) => {
    setCartItems((prevItems) => {
      const existingItem = prevItems.find((item) => item.maPB === product.maPB);

      if (existingItem) {
        return prevItems.map((item) =>
          item.maPB === product.maPB
            ? { ...item, quantity: item.quantity + quantity }
            : item,
        );
      }

      return [...prevItems, { ...product, quantity }];
    });
  };

  // Tổng số sản phẩm trong giỏ hàng, tính cả số lượng từng sản phẩm.
  const cartCount = cartItems.reduce((total, item) => total + item.quantity, 0);

  return (
    <CartContext.Provider
      value={{
        cartItems,
        cartCount,
        addToCart,
      }}
    >
      {children}
    </CartContext.Provider>
  );
};

/**
 * Hook dùng để truy cập dữ liệu giỏ hàng.
 */
export const useCart = () => {
  const context = useContext(CartContext);

  if (!context) {
    throw new Error("useCart phải được sử dụng bên trong CartProvider");
  }

  return context;
};
