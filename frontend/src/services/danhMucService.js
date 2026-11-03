import api from "./api";

/**
 * Service xử lý các API liên quan đến danh mục.
 */
const danhMucService = {
  /**
   * Lấy danh sách danh mục đang hoạt động.
   *
   * @returns {Promise<Array>} Danh sách danh mục
   */
  getDanhMucDangHoatDong: async () => {
    return await api("/api/danh-muc/active");
  },

  /**
   * Lấy toàn bộ danh mục.
   *
   * @returns {Promise<Array>} Danh sách danh mục
   */
  getAllDanhMuc: async () => {
    return await api("/api/danh-muc");
  },

  /**
   * Lấy thông tin danh mục theo ID.
   *
   * @param {number} id - Mã danh mục
   * @returns {Promise<Object>} Thông tin danh mục
   */
  getDanhMucById: async (id) => {
    return await api(`/api/danh-muc/${id}`);
  },
};

export default danhMucService;
