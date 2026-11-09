// Lấy địa chỉ Backend từ biến môi trường.
const BASE_URL = import.meta.env.VITE_API_BASE_URL;

/**
 * Hàm gọi API dùng chung cho toàn bộ ứng dụng.
 *
 * @param {string} endpoint - Đường dẫn API
 * @param {object} options - Cấu hình request
 * @returns {Promise<any>} Dữ liệu JSON từ Backend
 */
const api = async (endpoint, options = {}) => {
  // Gửi HTTP Request đến Backend
  const response = await fetch(`${BASE_URL}${endpoint}`, {
    ...options,

    // Cho phép gửi và nhận cookie từ Backend
    credentials: "include",

    headers: {
      "Content-Type": "application/json",
      ...options.headers,
    },
  });

  // Kiểm tra trạng thái HTTP
  if (!response.ok) {
    throw new Error(`API Error: ${response.status} - ${response.statusText}`);
  }

  // Xử lý trường hợp API không trả về nội dung
  if (response.status === 204) {
    return null;
  }

  // Chuyển JSON Response thành JavaScript Object
  return response.json();
};

export default api;
