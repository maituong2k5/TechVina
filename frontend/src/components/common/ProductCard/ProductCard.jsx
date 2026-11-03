import { Card, Button, Badge } from "react-bootstrap";
import "./ProductCard.scss";

const ProductCard = ({ sanPham, onAddToCart, onBuyNow }) => {
  // Kiểm tra dữ liệu đầu vào
  if (!sanPham) return null;

  // Định dạng giá tiền Việt Nam
  const formatPrice = (price) => {
    return new Intl.NumberFormat("vi-VN").format(price) + "₫";
  };

  const giaBan = Number(sanPham.giaBan || 0);

  // Giá gốc chỉ xuất hiện khi API cung cấp
  const giaGoc = Number(sanPham.giaGoc || 0);

  // Tính phần trăm giảm giá
  const phanTramGiam =
    giaGoc > giaBan && giaGoc > 0
      ? Math.round(((giaGoc - giaBan) / giaGoc) * 100)
      : 0;

  // Kiểm tra số lượng tồn kho
  const soLuongTon = Number(sanPham.soLuongTon || 0);
  const conHang = soLuongTon > 0;

  // Đường dẫn đến trang chi tiết sản phẩm
  const chiTietUrl = `/san-pham/${sanPham.maSP}`;

  return (
    <Card className="product-card h-100">
      {/* Khu vực hình ảnh */}
      <div className="product-card__image-wrapper">
        {phanTramGiam > 0 && (
          <Badge bg="danger" className="product-card__discount">
            -{phanTramGiam}%
          </Badge>
        )}

        <Card.Link href={chiTietUrl}>
          <Card.Img
            variant="top"
            src={sanPham.anhDaiDien || "/images/products/no-image.webp"}
            alt={sanPham.tenSP}
            className="product-card__image"
          />
        </Card.Link>
      </div>

      {/* Thông tin sản phẩm */}
      <Card.Body className="product-card__body">
        <Card.Title className="product-card__name">
          <Card.Link href={chiTietUrl}>
            <span>{sanPham.tenSP}</span>
            <span>{sanPham.ram}</span>
            <span>{sanPham.rom}</span>
          </Card.Link>
        </Card.Title>

        {/* Giá bán và giá gốc */}
        <div className="product-card__price">
          <span className="product-card__current-price">
            {formatPrice(giaBan)}
          </span>

          {phanTramGiam > 0 && (
            <span className="product-card__original-price">
              {formatPrice(giaGoc)}
            </span>
          )}
        </div>

        {/* Trạng thái tồn kho */}
        <div
          className={`product-card__stock ${
            conHang ? "in-stock" : "out-stock"
          }`}
        >
          {conHang ? `Còn ${soLuongTon} sản phẩm` : "Hết hàng"}
        </div>

        {/* Các nút thao tác */}
        <div className="product-card__actions">
          <Button
            variant="outline-primary"
            className="product-card__cart-button"
            disabled={!conHang}
            onClick={() => onAddToCart?.(sanPham)}
          >
            Thêm vào giỏ
          </Button>

          <Button
            variant="primary"
            className="product-card__buy-button"
            disabled={!conHang}
            onClick={() => onBuyNow?.(sanPham)}
          >
            Mua ngay
          </Button>
        </div>
      </Card.Body>
    </Card>
  );
};

export default ProductCard;
