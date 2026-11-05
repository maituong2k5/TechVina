import React, { useState } from "react";
import { FaShoppingCart } from "react-icons/fa";
import { useCart } from "../../../../context/CartContext";
import "./ProductInformation.scss";

/**
 * Hiển thị thông tin và thao tác mua sản phẩm.
 * Dữ liệu hiện tại là dữ liệu mẫu, sau này sẽ lấy từ API Spring Boot.
 */
const ProductInformation = ({
  danhSachPhienBan = [],
  thongTinSanPham,
  selectedMaPB,
  setSelectedMaPB,
  anhTheoPhienBan = {},
}) => {
  // Lấy hàm thêm sản phẩm vào giỏ hàng.
  const { addToCart } = useCart();

  // Số lượng sản phẩm khách hàng muốn mua.
  const [quantity, setQuantity] = useState(1);

  // Tăng số lượng nhưng không vượt quá tồn kho.
  const increaseQuantity = () => {
    const soLuongTon = phienBanDuocChon?.soLuongTon ?? 0;

    setQuantity((prev) => Math.min(prev + 1, soLuongTon));
  };
  // Giảm số lượng sản phẩm
  const decreaseQuantity = () => {
    setQuantity((prev) => Math.max(prev - 1, 1));
  };

  // Lấy thông tin phiên bản hiện đang được chọn.
  const phienBanDuocChon = danhSachPhienBan.find(
    (phienBan) => phienBan.maPB === selectedMaPB,
  );

  // Kiểm tra phiên bản được chọn có còn hàng không.
  const hetHang = !phienBanDuocChon || phienBanDuocChon.soLuongTon <= 0;

  /**
   * Lấy danh sách cấu hình RAM/ROM không trùng lặp.
   */
  const versions = [
    ...new Map(
      danhSachPhienBan.map((phienBan) => [
        `${phienBan.ram}GB ${phienBan.rom}GB`,
        `${phienBan.ram}GB ${phienBan.rom}GB`,
      ]),
    ).values(),
  ];

  /**
   * Lấy danh sách màu sắc theo cấu hình RAM/ROM đang chọn.
   */
  const colors = [
    ...new Set(
      danhSachPhienBan
        .filter((phienBan) => {
          if (!phienBanDuocChon) return false;

          return (
            phienBan.ram === phienBanDuocChon.ram &&
            phienBan.rom === phienBanDuocChon.rom
          );
        })
        .map((phienBan) => phienBan.tenMau),
    ),
  ];

  // Tìm phiên bản đại diện cho từng màu trong RAM/ROM đang chọn.
  const colorOptions = colors.map((tenMau) => {
    const phienBan = danhSachPhienBan.find(
      (item) =>
        item.ram === phienBanDuocChon?.ram &&
        item.rom === phienBanDuocChon?.rom &&
        item.tenMau === tenMau,
    );

    return {
      tenMau,
      maPB: phienBan?.maPB,
      anh: phienBan ? anhTheoPhienBan[phienBan.maPB]?.[0] : null,
    };
  });

  /**
   * Chọn cấu hình RAM/ROM.
   * Ưu tiên giữ nguyên màu sắc đang chọn.
   * Nếu màu hiện tại không tồn tại ở cấu hình mới,
   * hệ thống sẽ chọn phiên bản đầu tiên.
   */
  const handleSelectVersion = (version) => {
    const [ram, rom] = version
      .split(" ")
      .map((value) => parseInt(value.replace("GB", ""), 10));

    // Tìm phiên bản có cùng RAM, ROM và màu hiện tại.
    let phienBan = danhSachPhienBan.find(
      (item) =>
        item.ram === ram &&
        item.rom === rom &&
        item.tenMau === phienBanDuocChon?.tenMau,
    );

    // Nếu không có màu tương ứng, lấy phiên bản đầu tiên.
    if (!phienBan) {
      phienBan = danhSachPhienBan.find(
        (item) => item.ram === ram && item.rom === rom,
      );
    }

    if (phienBan) {
      setSelectedMaPB(phienBan.maPB);
      setQuantity(1);
    }
  };

  /**
   * Chọn màu sắc trong cấu hình RAM/ROM hiện tại.
   */
  const handleSelectColor = (tenMau) => {
    if (!phienBanDuocChon) return;

    const phienBan = danhSachPhienBan.find(
      (item) =>
        item.ram === phienBanDuocChon.ram &&
        item.rom === phienBanDuocChon.rom &&
        item.tenMau === tenMau,
    );

    if (phienBan) {
      setSelectedMaPB(phienBan.maPB);
      setQuantity(1);
    }
  };

  /**
   * Định dạng giá tiền theo đơn vị Việt Nam đồng.
   */
  const formatCurrency = (price) => {
    if (price == null) return "Đang cập nhật";

    return new Intl.NumberFormat("vi-VN", {
      style: "currency",
      currency: "VND",
    }).format(price);
  };

  /**
   * Thêm phiên bản sản phẩm đang chọn vào giỏ hàng.
   */
  const handleAddToCart = () => {
    // Không cho phép thêm sản phẩm khi hết hàng.
    if (hetHang) return;

    // Chuẩn bị thông tin phiên bản sản phẩm.
    const product = {
      maPB: phienBanDuocChon.maPB,
      maSP: thongTinSanPham?.maSP,
      tenSP: thongTinSanPham?.tenSP,
      sku: phienBanDuocChon.sku,
      ram: phienBanDuocChon.ram,
      rom: phienBanDuocChon.rom,
      tenMau: phienBanDuocChon.tenMau,
      gia: phienBanDuocChon.gia,
      anh: anhTheoPhienBan[phienBanDuocChon.maPB]?.[0] || null,
    };

    // Thêm sản phẩm cùng số lượng người dùng đã chọn.
    addToCart(product, quantity);
  };

  return (
    <div className="product-information">
      {/* Thông tin cơ bản */}
      <div className="product-information__header">
        {/* Chỉ hiển thị khi có thông tin giảm giá thực tế */}
        {/* <span className="product-information__discount">
          
        </span> */}

        <h1 className="product-information__name">
          {thongTinSanPham?.tenSP || "Đang tải sản phẩm..."}
        </h1>

        {/* Thông tin cơ bản lấy từ API */}
        <div className="product-information__meta">
          <span>Mã sản phẩm: {thongTinSanPham?.maSP ?? "..."}</span>

          <span>
            Thương hiệu:{" "}
            <strong>{thongTinSanPham?.tenHang || "Đang cập nhật"}</strong>
          </span>

          <span>
            Danh mục:{" "}
            <strong>{thongTinSanPham?.tenDanhMuc || "Đang cập nhật"}</strong>
          </span>
        </div>

        {/* Đánh giá sản phẩm */}
        <div className="product-information__rating">
          {/* Chỉ hiển thị khi có thông tin giảm giá thực tế */}
          {/* <span className="product-information__stars">★★★★★</span>
          <span>4.8 (32 đánh giá)</span>
          <button type="button">Viết đánh giá</button> */}
        </div>
      </div>

      {/* Giá bán và số lượng tồn kho */}
      {/* Giá bán và số lượng tồn kho */}
      <div className="product-information__price">
        <div>
          <span className="product-information__current-price">
            {formatCurrency(phienBanDuocChon?.gia)}
          </span>
        </div>

        <span className="product-information__stock">
          ● Còn {phienBanDuocChon?.soLuongTon ?? 0} sản phẩm
        </span>
      </div>

      {/* Lựa chọn phiên bản RAM/ROM */}
      <div className="product-information__option">
        <h3>Phiên bản</h3>

        <div className="product-information__version-list">
          {versions.map((version) => {
            // Kiểm tra phiên bản RAM/ROM đang được chọn.
            const isActive = phienBanDuocChon
              ? version ===
                `${phienBanDuocChon.ram}GB ${phienBanDuocChon.rom}GB`
              : false;

            return (
              <button
                key={version}
                type="button"
                className={isActive ? "option-button active" : "option-button"}
                onClick={() => handleSelectVersion(version)}
              >
                {version}
              </button>
            );
          })}
        </div>
      </div>

      {/* Lựa chọn màu sắc */}
      <div className="product-information__option">
        <h3>Màu sắc</h3>

        <div className="product-information__color-list">
          {colorOptions.map((color) => (
            <button
              key={color.tenMau}
              type="button"
              className={
                phienBanDuocChon?.tenMau === color.tenMau
                  ? "color-button active"
                  : "color-button"
              }
              onClick={() => handleSelectColor(color.tenMau)}
            >
              {color.anh && (
                <img
                  src={color.anh}
                  alt={`Điện thoại màu ${color.tenMau}`}
                  className="color-button__image"
                />
              )}

              <span className="color-button__name">{color.tenMau}</span>
            </button>
          ))}
        </div>
      </div>

      {/* Số lượng và thao tác mua hàng */}
      <div className="product-information__actions">
        <div className="quantity-control">
          <button
            type="button"
            onClick={decreaseQuantity}
            disabled={quantity <= 1}
          >
            −
          </button>

          <span>{quantity}</span>

          <button
            type="button"
            onClick={increaseQuantity}
            disabled={quantity >= (phienBanDuocChon?.soLuongTon ?? 0)}
          >
            +
          </button>
        </div>

        <button
          type="button"
          className="btn-add-cart"
          disabled={hetHang}
          onClick={handleAddToCart}
        >
          {hetHang ? (
            "Hết hàng"
          ) : (
            <>
              <FaShoppingCart />
              <span>Thêm vào giỏ hàng</span>
            </>
          )}
        </button>

        <button type="button" className="btn-buy-now" disabled={hetHang}>
          {hetHang ? "Hết hàng" : "Mua ngay"}
        </button>
      </div>

      {/* Cam kết dịch vụ */}
      <div className="product-information__benefits">
        <div>
          <strong>🚚 Giao hàng toàn quốc</strong>
          <span>Miễn phí từ 500.000đ</span>
        </div>

        <div>
          <strong>🛡️ Bảo hành chính hãng</strong>
          <span>12 tháng</span>
        </div>

        <div>
          <strong>🔄 Đổi trả dễ dàng</strong>
          <span>Trong 7 ngày</span>
        </div>

        <div>
          <strong>🎧 Hỗ trợ 24/7</strong>
          <span>Hotline: 1900 1234</span>
        </div>
      </div>
    </div>
  );
};

export default ProductInformation;
