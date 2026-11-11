/**
 * Service xử lý API phiên bản sản phẩm phục vụ mua hàng.
 */

// Địa chỉ API lấy danh sách phiên bản sản phẩm.
const API_URL = "/api/san-pham";

/**
 * Lấy danh sách phiên bản theo mã sản phẩm.
 *
 * @param {number} maSP - Mã sản phẩm.
 * @returns {Promise<Array>} Danh sách phiên bản kèm giá và tồn kho.
 */
export const getDanhSachPhienBan = async (maSP) => {
  try {
    const response = await fetch(`${API_URL}/${maSP}/phien-ban`);

    // Kiểm tra trạng thái HTTP.
    if (!response.ok) {
      throw new Error(
        `Không thể lấy danh sách phiên bản. HTTP ${response.status}`,
      );
    }

    // Chuyển JSON thành JavaScript.
    return await response.json();
  } catch (error) {
    console.error("Lỗi lấy danh sách phiên bản:", error);
    throw error;
  }
};
