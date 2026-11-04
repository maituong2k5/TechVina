import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import { getThongSoKyThuat } from "../../services/thongSoKyThuatService";
import Breadcrumb from "./Breadcrumb/Breadcrumb";
// Component thư viện hình ảnh sản phẩm
import ProductGallery from "./ProductDetailMain/ProductGallery/ProductGallery";

import "./ProductDetail.scss";

/**
 * Trang chi tiết sản phẩm.
 */
const ProductDetail = () => {
  // Lấy mã sản phẩm và mã phiên bản từ URL
  const { maSP, maPB } = useParams();

  // Danh sách đường dẫn điều hướng
  const breadcrumbItems = [
    { label: "Trang chủ", to: "/" },
    { label: "Điện thoại", to: "/dien-thoai" },
    { label: "Xiaomi", to: "/hang/xiaomi" },
    { label: "Xiaomi 15 12GB 256GB" },
  ];

  // Danh sách nhóm thông số kỹ thuật
  const [danhSachThongSo, setDanhSachThongSo] = useState([]);

  // Trạng thái tải dữ liệu
  const [loading, setLoading] = useState(true);

  // Lưu thông báo lỗi nếu API thất bại
  const [error, setError] = useState("");

  /**
   * Gọi API lấy thông số kỹ thuật khi maSP thay đổi.
   */
  useEffect(() => {
    const fetchThongSoKyThuat = async () => {
      try {
        setLoading(true);
        setError("");

        const data = await getThongSoKyThuat(maSP);

        setDanhSachThongSo(data);
      } catch (err) {
        setError("Không thể tải thông số kỹ thuật.");
      } finally {
        setLoading(false);
      }
    };

    if (maSP) {
      fetchThongSoKyThuat();
    }
  }, [maSP]);

  return (
    <div className="product-detail">
      <div className="container">
        {/* Đường dẫn điều hướng */}
        <Breadcrumb items={breadcrumbItems} />

        {/* Khu vực hình ảnh sản phẩm */}
        <ProductGallery />
        <h2>Chi tiết sản phẩm</h2>

        <p>Mã sản phẩm: {maSP}</p>
        <p>Mã phiên bản: {maPB}</p>

        <section className="technical-specifications">
          <h3>Thông số kỹ thuật</h3>

          {/* Trạng thái đang tải */}
          {loading && <p>Đang tải thông số kỹ thuật...</p>}

          {/* Trạng thái lỗi */}
          {!loading && error && <p>{error}</p>}

          {/* Hiển thị danh sách thông số */}
          {!loading &&
            !error &&
            danhSachThongSo.map((nhom, index) => (
              <div
                className="specification-group"
                key={`${nhom.tenNhom}-${index}`}
              >
                <h4>{nhom.tenNhom}</h4>

                <div className="specification-list">
                  {nhom.danhSachThongSo.map((thongSo, itemIndex) => (
                    <div
                      className="specification-item"
                      key={`${thongSo.tenThongSo}-${itemIndex}`}
                    >
                      <span>{thongSo.tenThongSo}</span>
                      <span>{thongSo.giaTri}</span>
                    </div>
                  ))}
                </div>
              </div>
            ))}

          {/* Trường hợp sản phẩm chưa có thông số */}
          {!loading && !error && danhSachThongSo.length === 0 && (
            <p>Chưa có thông số kỹ thuật.</p>
          )}
        </section>
      </div>
    </div>
  );
};

export default ProductDetail;
