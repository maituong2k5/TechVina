import { useEffect, useState } from "react";
import sanPhamService from "../../../services/sanPhamService";
import ProductCard from "../../../components/common/ProductCard/ProductCard";
import "./ProductSection.scss";

/**
 * Component hiển thị danh sách sản phẩm nổi bật.
 */
const ProductSection = () => {
  // Danh sách sản phẩm lấy từ Backend
  const [sanPhamList, setSanPhamList] = useState([]);

  // Trạng thái tải dữ liệu
  const [isLoading, setIsLoading] = useState(true);

  // Thông báo lỗi khi gọi API thất bại
  const [error, setError] = useState(null);

  /**
   * Gọi API lấy danh sách sản phẩm nổi bật.
   */
  useEffect(() => {
    const fetchSanPhamNoiBat = async () => {
      try {
        setIsLoading(true);

        const data = await sanPhamService.getSanPhamNoiBat();

        setSanPhamList(data);
      } catch (error) {
        console.error("Lỗi khi lấy sản phẩm nổi bật:", error);
        setError("Không thể tải danh sách sản phẩm.");
      } finally {
        setIsLoading(false);
      }
    };

    fetchSanPhamNoiBat();
  }, []);

  // Hiển thị khi đang tải dữ liệu
  if (isLoading) {
    return <p>Đang tải sản phẩm...</p>;
  }

  // Hiển thị khi xảy ra lỗi
  if (error) {
    return <p>{error}</p>;
  }

  return (
    <section className="product-section">
      <h2 className="product-section__title">Sản phẩm nổi bật</h2>

      <div className="product-section__list">
        {sanPhamList.map((sanPham) => (
          <ProductCard key={sanPham.maSP} sanPham={sanPham} />
        ))}
      </div>
    </section>
  );
};

export default ProductSection;
