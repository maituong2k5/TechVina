import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter } from "react-router-dom";

// Import Bootstrap.
import "bootstrap/dist/css/bootstrap.min.css";

// Import Design System của TechVina.
import "./styles/variables.css";
import "./index.css";

// Import AuthProvider hiện tại của TechVina.
// Đổi tên khi import để tránh trùng với AuthProvider của Cognito.
import { AuthProvider as TechVinaAuthProvider } from "./context/AuthContext";

// Import AuthProvider từ thư viện react-oidc-context.
// Provider này quản lý quá trình đăng nhập và JWT của Cognito.
import { AuthProvider as CognitoAuthProvider } from "react-oidc-context";

// Import cấu hình Amazon Cognito đã tạo.
import { cognitoAuthConfig } from "./services/authConfig.js";

// Import hệ thống Routing.
import AppRoutes from "./routes/AppRoutes.jsx";

/**
 * Xử lý sau khi đăng nhập Cognito thành công.
 *
 * Cognito chuyển hướng về ReactJS kèm Authorization Code.
 * Thư viện sẽ xử lý Code để lấy token.
 * Sau đó hàm này xóa tham số xác thực khỏi URL.
 */
const onSigninCallback = () => {
  window.history.replaceState(
    {},
    document.title,
    window.location.pathname + window.location.hash,
  );
};

// Khởi tạo ứng dụng ReactJS.
createRoot(document.getElementById("root")).render(
  <StrictMode>
    {/* Cognito quản lý xác thực OIDC và token. */}
    <CognitoAuthProvider
      {...cognitoAuthConfig}
      onSigninCallback={onSigninCallback}
    >
      {/* Quản lý điều hướng các trang trong ứng dụng. */}
      <BrowserRouter>
        {/* Giữ nguyên Context đăng nhập hiện tại của TechVina. */}
        <TechVinaAuthProvider>
          {/* Hiển thị hệ thống các trang của TechVina. */}
          <AppRoutes />
        </TechVinaAuthProvider>
      </BrowserRouter>
    </CognitoAuthProvider>
  </StrictMode>,
);
