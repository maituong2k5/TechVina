import { useEffect, useState } from "react";
import { getAllCategories } from "../../../../service/categoryService";

const AccessoryDropdown = () => {
  // Lưu danh sách danh mục lấy từ Backend
  const [categories, setCategories] = useState([]);

  // Trạng thái loading
  const [loading, setLoading] = useState(true);

  // Lỗi khi gọi API
  const [error, setError] = useState(null);

  // Lưu danh mục Phụ kiện
  const [phuKien, setPhuKien] = useState(null);

  /**
   * Gọi API lấy danh sách danh mục
   * khi component được render lần đầu.
   */
  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const data = await getAllCategories();

        // Kiểm tra dữ liệu Backend trả về
        console.log("Danh mục từ Backend:", data);

        // Lưu dữ liệu Backend trả về
        setCategories(data);

        // Tìm danh mục Phụ kiện từ dữ liệu Backend
        const phuKienData = data.find((category) => category.maDanhMuc === 3);

        // Lưu vào state
        setPhuKien(phuKienData);

        console.log("Danh mục Phụ kiện:", phuKienData);
      } catch (error) {
        console.error("Lỗi khi lấy danh mục:", error);
        setError("Không thể tải danh mục");
      } finally {
        setLoading(false);
      }
    };

    fetchCategories();
  }, []);

  // Đang tải dữ liệu
  if (loading) {
    return <div className="category-nav__mega-menu">Đang tải...</div>;
  }

  // Có lỗi
  if (error) {
    return <div className="category-nav__mega-menu">{error}</div>;
  }

  return (
    <div className="category-nav__mega-menu">
      {phuKien?.danhMucCon?.map((parent) => (
        <div key={parent.maDanhMuc}>
          {/* Tên nhóm danh mục */}
          <h3>{parent.tenDanhMuc}</h3>

          {/* Các danh mục con */}
          {parent.danhMucCon?.map((child) => (
            <div className="category-nav__category-item" key={child.maDanhMuc}>
              {/* Ảnh danh mục */}
              <img src={child.duongDanAnh} alt={child.tenDanhMuc} />

              {/* Tên danh mục */}
              <p>{child.tenDanhMuc}</p>
            </div>
          ))}
        </div>
      ))}
    </div>
  );
};

export default AccessoryDropdown;
