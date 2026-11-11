/**
 * Service xử lý API thông tin cơ bản của sản phẩm.
 */

const API_URL = "/api/san-pham";

/**
 * Lấy thông tin sản phẩm theo mã sản phẩm.
 *
 * @param {number} maSP - Mã sản phẩm.
 * @returns {Promise<Object>} Thông tin sản phẩm.
 */
export const getThongTinSanPham = async (maSP) => {
  try {
    const response = await fetch(`${API_URL}/${maSP}/chi-tiet`);

    // Kiểm tra trạng thái HTTP.
    if (!response.ok) {
      throw new Error(
        `Không thể lấy thông tin sản phẩm. HTTP ${response.status}`,
      );
    }

    return await response.json();
  } catch (error) {
    console.error("Lỗi lấy thông tin sản phẩm:", error);
    throw error;
  }
};
