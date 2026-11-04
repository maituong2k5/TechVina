import { Card, Button, Badge } from "react-bootstrap";
import { FaShoppingCart } from "react-icons/fa";
import "./ProductCard.scss";

const ProductCard = ({ sanPham, onAddToCart, onBuyNow }) => {
  // Kiểm tra dữ liệu đầu vào
  if (!sanPham) return null;

  // Định dạng giá tiền Việt Nam
  const formatPrice = (price) => {
    return new Intl.NumberFormat("vi-VN").format(price) + "₫";
  };

  // Lấy giá gốc và giá sau giảm từ API
  const giaGoc = Number(sanPham.giaGoc || 0);
  const giaSauGiam = Number(sanPham.giaSauGiam || 0);

  // Lấy thông tin giảm giá từ Backend
  const giaTriGiam = Number(sanPham.giaTriGiam || 0);
  const phanTramGiam = Number(sanPham.phanTramGiam || 0);

  // Kiểm tra sản phẩm có đang được giảm giá hay không
  const coGiamGia = giaTriGiam > 0 && giaSauGiam < giaGoc;

  // Kiểm tra số lượng tồn kho
  const soLuongTon = Number(sanPham.soLuongTon || 0);
  const conHang = soLuongTon > 0;

  // Đường dẫn trang chi tiết gồm mã sản phẩm và mã phiên bản
  const chiTietUrl = `/san-pham/${sanPham.maSP}/${sanPham.maPB}`;
  return (
    <Card className="product-card h-100">
      {/* Khu vực hình ảnh */}
      <div className="product-card__image-wrapper">
        {coGiamGia && (
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

        {/* Giá sau giảm và giá gốc */}
        <div className="product-card__price">
          <span className="product-card__current-price">
            {formatPrice(giaSauGiam)}
          </span>

          {coGiamGia && (
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
            <FaShoppingCart />
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
