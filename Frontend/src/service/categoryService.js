// Service dùng để gọi API liên quan đến danh mục
const API_URL = "http://localhost:8080/api/danh-muc";

/**
 * Lấy tất cả danh mục từ Backend
 *
 * @returns {Promise<Array>} Danh sách danh mục
 */
export const getAllCategories = async () => {
  const response = await fetch(API_URL);

  // Kiểm tra API có trả về thành công hay không
  if (!response.ok) {
    throw new Error("Không thể lấy danh sách danh mục");
  }

  return await response.json();
};
