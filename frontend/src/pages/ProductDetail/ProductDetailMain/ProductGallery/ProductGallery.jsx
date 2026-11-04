import { useState } from "react";
import { Carousel, Button } from "react-bootstrap";

import "./ProductGallery.scss";

/**
 * Component hiển thị thư viện hình ảnh sản phẩm.
 */
const ProductGallery = () => {
  // Danh sách ảnh sản phẩm (tạm thời sử dụng dữ liệu mẫu)
  const danhSachAnh = [
    "/images/products/xiaomi/xiaomi-series/xiaomi-15/xiaomi_15_-_1_2.webp",
    "/images/products/xiaomi/xiaomi-series/xiaomi-15/xiaomi_15_-_2_2.webp",
    "/images/products/xiaomi/xiaomi-series/xiaomi-15/xiaomi_15_-_3_2.webp",
    "/images/products/xiaomi/xiaomi-series/xiaomi-15/xiaomi_15_-_4_2.webp",
  ];

  // Lưu vị trí ảnh đang được chọn
  const [anhHienTai, setAnhHienTai] = useState(0);

  return (
    <div className="product-gallery">
      {/* Khu vực ảnh sản phẩm lớn */}
      <div className="product-gallery__main">
        <Carousel
          activeIndex={anhHienTai}
          onSelect={(selectedIndex) => setAnhHienTai(selectedIndex)}
          indicators={false}
          interval={null}
          controls={true}
        >
          {danhSachAnh.map((anh, index) => (
            <Carousel.Item key={anh}>
              <img
                src={anh}
                alt={`Ảnh sản phẩm ${index + 1}`}
                className="product-gallery__image"
              />
            </Carousel.Item>
          ))}
        </Carousel>

        {/* Bộ đếm ảnh */}
        <span className="product-gallery__counter">
          {anhHienTai + 1}/{danhSachAnh.length}
        </span>
      </div>

      {/* Băng chuyền ảnh nhỏ */}
      <div className="product-gallery__thumbnails">
        {danhSachAnh.map((anh, index) => (
          <button
            type="button"
            key={anh}
            className={`product-gallery__thumbnail ${
              anhHienTai === index ? "active" : ""
            }`}
            onClick={() => setAnhHienTai(index)}
            aria-label={`Chọn ảnh ${index + 1}`}
            aria-pressed={anhHienTai === index}
          >
            <img src={anh} alt={`Ảnh thu nhỏ ${index + 1}`} />
          </button>
        ))}
      </div>

      {/* Danh sách màu sắc và tiện ích */}
      <div className="product-gallery__options">
        <Button variant="light">Bạc</Button>

        <Button variant="light">Đen</Button>

        <Button variant="light">Đỏ Burgundy</Button>

        <Button variant="light">Băng Thanh</Button>

        <Button variant="light">Thông số kỹ thuật</Button>

        <Button variant="light">Thông tin sản phẩm</Button>

        <Button variant="light">Xem trên điện thoại</Button>
      </div>
    </div>
  );
};

export default ProductGallery;
