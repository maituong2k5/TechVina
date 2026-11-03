import "./ProductCard.scss";

/**
 * Component hiển thị thông tin cơ bản của một sản phẩm.
 *
 * @param {Object} props
 * @param {Object} props.sanPham - Dữ liệu sản phẩm từ Backend
 */
const ProductCard = ({ sanPham }) => {
  // Kiểm tra dữ liệu sản phẩm
  if (!sanPham) {
    return null;
  }

  return (
    <article className="product-card">
      {/* Khu vực hình ảnh sản phẩm */}
      <div className="product-card__image">
        <img src="/images/products/no-image.webp" alt={sanPham.tenSP} />
      </div>

      {/* Thông tin sản phẩm */}
      <div className="product-card__content">
        <h3 className="product-card__name">{sanPham.tenSP}</h3>

        <p className="product-card__brand">{sanPham.hang?.tenHang}</p>

        <p className="product-card__category">{sanPham.danhMuc?.tenDanhMuc}</p>
      </div>
    </article>
  );
};

export default ProductCard;
