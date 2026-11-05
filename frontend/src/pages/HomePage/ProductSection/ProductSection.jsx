import { useEffect, useState } from "react";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

import sanPhamService from "../../../services/productService";
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

  // Gọi API khi component được khởi tạo
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

  return (
    <section className="product-section">
      {/* Khung bao quanh khu vực sản phẩm */}
      <div className="product-section__inner">
        {/* Tiêu đề */}
        <div className="mb-4">
          <h2 className="product-section__title">Sản phẩm nổi bật</h2>

          <p className="product-section__subtitle mb-0">
            Những sản phẩm hot nhất
          </p>
        </div>

        {/* Trạng thái tải dữ liệu */}
        {isLoading && <p>Đang tải sản phẩm...</p>}

        {/* Thông báo lỗi */}
        {error && <p className="text-danger">{error}</p>}

        {/* Danh sách sản phẩm */}
        {!isLoading && !error && (
          <Row className="g-4">
            {sanPhamList.map((sanPham) => (
              <Col key={sanPham.maSP} xs={12} sm={6} md={6} lg={3} xl={3}>
                <ProductCard sanPham={sanPham} />
              </Col>
            ))}
          </Row>
        )}
      </div>
    </section>
  );
};

export default ProductSection;
