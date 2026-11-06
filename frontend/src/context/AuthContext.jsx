import { createContext, useContext, useState } from "react";

// Tạo Context quản lý trạng thái xác thực.
const AuthContext = createContext(null);

/**
 * AuthProvider cung cấp trạng thái đăng nhập
 * cho toàn bộ component bên trong ứng dụng.
 */
export const AuthProvider = ({ children }) => {
  // Khởi tạo Access Token từ Local Storage.
  const [accessToken, setAccessToken] = useState(() =>
    localStorage.getItem("accessToken"),
  );

  // Thông tin người dùng sau khi đăng nhập.
  const [user, setUser] = useState(null);

  // Kiểm tra trạng thái đăng nhập.
  const isAuthenticated = Boolean(accessToken);

  /**
   * Lưu thông tin xác thực sau khi đăng nhập thành công.
   */
  const dangNhap = (duLieu) => {
    localStorage.setItem("accessToken", duLieu.accessToken);

    setAccessToken(duLieu.accessToken);

    setUser({
      maTaiKhoan: duLieu.maTaiKhoan,
      hoTen: duLieu.hoTen,
      email: duLieu.email,
      vaiTro: duLieu.vaiTro,
    });
  };

  /**
   * Xóa thông tin xác thực khi đăng xuất.
   */
  const dangXuatLocal = () => {
    localStorage.removeItem("accessToken");

    setAccessToken(null);
    setUser(null);
  };

  return (
    <AuthContext.Provider
      value={{
        accessToken,
        user,
        isAuthenticated,
        dangNhap,
        dangXuatLocal,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

/**
 * Custom Hook giúp component sử dụng AuthContext.
 */
export const useAuth = () => {
  const context = useContext(AuthContext);

  if (!context) {
    throw new Error("useAuth phải được sử dụng bên trong AuthProvider.");
  }

  return context;
};
