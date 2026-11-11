/**
 * Service xử lý API thông số kỹ thuật sản phẩm.
 */

// Địa chỉ Backend Spring Boot
const API_URL = "/api/thong-so-ky-thuat";

/**
 * Lấy danh sách thông số kỹ thuật theo mã sản phẩm.
 *
 * @param {number} maSP - Mã sản phẩm.
 * @returns {Promise<Array>} Danh sách nhóm thông số kỹ thuật.
 */
export const getThongSoKyThuat = async (maSP) => {
  try {
    const response = await fetch(`${API_URL}/${maSP}`);

    // Kiểm tra trạng thái HTTP
    if (!response.ok) {
      throw new Error(
        `Không thể lấy thông số kỹ thuật. HTTP ${response.status}`,
      );
    }

    // Chuyển dữ liệu JSON thành JavaScript
    return await response.json();
  } catch (error) {
    console.error("Lỗi lấy thông số kỹ thuật:", error);
    throw error;
  }
};
