import react from "@vitejs/plugin-react";
import { defineConfig } from "vite";

// Cấu hình Vite cho Frontend TechVina
export default defineConfig({
  plugins: [react()],

  server: {
    // Tự động mở trình duyệt khi chạy Frontend
    open: true,

    // Chuyển tiếp API từ localhost đến Backend qua CloudFront
    proxy: {
      "/api": {
        target: "http://localhost:8080",
        changeOrigin: true,
        secure: true,
      },
    },
  },
});
