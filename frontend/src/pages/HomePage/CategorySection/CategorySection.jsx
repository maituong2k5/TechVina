import { useEffect, useState } from "react";
import danhMucService from "../../../services/categoryService";
import "./CategorySection.scss";

/**
 * Component hiển thị danh sách danh mục sản phẩm.
 */

const CategorySection = () => {
  // Lưu danh sách danh mục lấy từ Backend
  const [danhMucList, setDanhMucList] = useState([]);

  //Trang thai tai du lieu
  const [isLoading, setLoading] = useState(true);

  // Lưu thông báo lỗi nếu gọi API thất bại
  const [error, setError] = useState(null);

  /**
   * Gọi API lấy danh mục đang hoạt động.
   */

  useEffect(() => {
    const fetchDanhMuc = async () => {
      try {
        setLoading(true);

        const data = await danhMucService.getDanhMucDangHoatDong();

        setDanhMucList(data);
        console.log(data);
      } catch (error) {
        console.error("Lỗi khi lấy danh mục:", error);
        setError("Không thể tải danh sách danh mục.");
      } finally {
        setLoading(false);
      }
    };
    fetchDanhMuc();
  }, []);

  //Hiển thị khi đang tải dữ liệu
  if (isLoading) {
    return <p>Đang tải danh mục</p>;
  }

  //Hiển thị xảy ra lỗi
  if (error) {
    return <p>{error}</p>;
  }

  return (
    <section className="category-section">
      <h2 className="category-section__title">Danh mục nổi bật</h2>

      <div className="category-section__list">
        {danhMucList.map((danhMuc) => (
          <div className="category-section__item" key={danhMuc.maDanhMuc}>
            <img src={danhMuc.duongDanAnh} alt={danhMuc.tenDanhMuc} />

            <span>{danhMuc.tenDanhMuc}</span>
          </div>
        ))}
      </div>
    </section>
  );
};

export default CategorySection;
