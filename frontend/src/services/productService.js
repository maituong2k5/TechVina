import api from "./api";

/**
 * Service xử lý các API liên quan đến sản phẩm.
 */
const sanPhamService = {
  /**
   * Lấy danh sách sản phẩm nổi bật đang hoạt động.
   *
   * @returns {Promise<Array>} Danh sách sản phẩm nổi bật
   */
  getSanPhamNoiBat: async () => {
    return await api("/api/san-pham/noi-bat");
  },
};

export default sanPhamService;
