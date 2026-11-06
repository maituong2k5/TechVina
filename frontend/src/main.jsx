import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter } from "react-router-dom";

// Import Bootstrap
import "bootstrap/dist/css/bootstrap.min.css";

// Import Design System của TechVina
import "./styles/variables.css";
import "./index.css";

// Import Context Providers
import { AuthProvider } from "./context/AuthContext";

// Import hệ thống Routing
import AppRoutes from "./routes/AppRoutes.jsx";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <BrowserRouter>
      {/* Quản lý trạng thái đăng nhập toàn ứng dụng */}
      <AuthProvider>
        <AppRoutes />
      </AuthProvider>
    </BrowserRouter>
  </StrictMode>,
);
