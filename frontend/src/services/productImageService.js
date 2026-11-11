// API lấy danh sách ảnh theo phiên bản sản phẩm.
const API_URL = "/api/phien-ban";

/**
 * Lấy danh sách ảnh của một phiên bản sản phẩm.
 * @param {number} maPB - Mã phiên bản sản phẩm.
 * @returns {Promise<Array>} Danh sách ảnh.
 */
export const getDanhSachAnh = async (maPB) => {
  try {
    const response = await fetch(`${API_URL}/${maPB}/anh`);

    // Kiểm tra kết quả trả về từ Backend.
    if (!response.ok) {
      throw new Error(`Không thể lấy danh sách ảnh. HTTP ${response.status}`);
    }

    return await response.json();
  } catch (error) {
    console.error("Lỗi lấy danh sách ảnh:", error);
    throw error;
  }
};
