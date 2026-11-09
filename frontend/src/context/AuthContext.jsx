import { createContext, useContext } from "react";

// Hook lấy thông tin xác thực từ Amazon Cognito.
import { useAuth as useCognitoAuth } from "react-oidc-context";

// Tạo Context quản lý trạng thái đăng nhập của TechVina.
const AuthContext = createContext(null);

/**
 * AuthProvider đồng bộ thông tin Cognito
 * với các component hiện tại của TechVina.
 */
export const AuthProvider = ({ children }) => {
  // Lấy trạng thái đăng nhập từ Cognito.
  const cognito = useCognitoAuth();

  // Lấy Access Token do Cognito cấp.
  const accessToken = cognito.isAuthenticated
    ? (cognito.user?.access_token ?? null)
    : null;

  // Lấy thông tin người dùng từ ID Token.
  const profile = cognito.user?.profile;

  // Lấy danh sách nhóm người dùng.
  const tokenGroups = profile?.["cognito:groups"];

  const groups = Array.isArray(tokenGroups) ? tokenGroups : [];

  // Xác định vai trò trong hệ thống TechVina.
  // Ưu tiên ADMIN, tiếp theo STAFF rồi CUSTOMER.
  // Chỉ dùng để hiển thị giao diện.
  const vaiTro =
    ["ADMIN", "STAFF", "CUSTOMER"].find((role) => groups.includes(role)) ??
    null;

  // Đồng bộ trạng thái xác thực từ Cognito.
  const isAuthenticated = cognito.isAuthenticated;

  // Tạo thông tin người dùng cho TechVina.
  const user =
    isAuthenticated && profile
      ? {
          // Cognito User ID, chưa phải mã tài khoản MySQL.
          cognitoSub: profile.sub,

          // Thông tin hiển thị.
          hoTen: profile.name || profile.email || "Người dùng",

          email: profile.email || "",

          // Vai trò Cognito.
          vaiTro: vaiTro,
        }
      : null;

  /**
   * Đăng nhập bằng Amazon Cognito.
   * Chuyển trình duyệt đến trang đăng nhập Cognito.
   */
  const dangNhap = () => {
    return cognito.signinRedirect();
  };

  /**
   * Xóa thông tin đăng nhập Cognito ở ReactJS.
   *
   * Hàm này chỉ xóa phiên cục bộ.
   * Đăng xuất hoàn toàn cần kết thúc phiên Cognito
   * như hàm handleLogout trong main.jsx.
   */
  const dangXuatLocal = () => {
    return cognito.removeUser();
  };

  // Cung cấp trạng thái xác thực cho toàn ứng dụng.
  return (
    <AuthContext.Provider
      value={{
        accessToken,
        user,
        isAuthenticated,
        isLoading: cognito.isLoading,
        dangNhap,
        dangXuatLocal,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

/**
 * Custom Hook để các component TechVina
 * có thể sử dụng thông tin đăng nhập.
 */
export const useAuth = () => {
  // Lấy dữ liệu từ AuthContext.
  const context = useContext(AuthContext);

  // Kiểm tra component có nằm trong AuthProvider không.
  if (!context) {
    throw new Error("useAuth phải được sử dụng bên trong AuthProvider.");
  }

  return context;
};
