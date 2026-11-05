import { useEffect, useRef, useState } from "react";
import { Carousel } from "react-bootstrap";
import Lightbox from "yet-another-react-lightbox";
import Zoom from "yet-another-react-lightbox/plugins/zoom";

import "yet-another-react-lightbox/styles.css";
import "./ProductGallery.scss";

/**
 * Component hiển thị thư viện hình ảnh sản phẩm.
 */
const ProductGallery = (
  { selectedMaPB, danhSachAnh = [] },
  loadingAnh = false,
) => {
  // Vị trí ảnh lớn đang được chọn.
  const [anhHienTai, setAnhHienTai] = useState(0);
  // Theo dõi trạng thái mở/đóng cửa sổ phóng to ảnh.
  const [isLightboxOpen, setIsLightboxOpen] = useState(false);

  // Tham chiếu đến khu vực chứa ảnh thu nhỏ.
  const thumbnailTrackRef = useRef(null);

  // Theo dõi khả năng cuộn của danh sách ảnh nhỏ.
  const [canScrollLeft, setCanScrollLeft] = useState(false);
  const [canScrollRight, setCanScrollRight] = useState(true);

  // Theo dõi trạng thái kéo danh sách ảnh.
  const isDraggingRef = useRef(false);

  // Lưu vị trí chuột khi bắt đầu kéo.
  const startXRef = useRef(0);

  // Lưu vị trí cuộn ban đầu.
  const scrollLeftRef = useRef(0);

  // Xác định người dùng có thực sự kéo hay chỉ nhấn chuột.
  const hasDraggedRef = useRef(false);

  // Bắt đầu kéo danh sách ảnh.
  const handleMouseDown = (event) => {
    const track = thumbnailTrackRef.current;

    if (!track || event.button !== 0) return;

    isDraggingRef.current = true;
    hasDraggedRef.current = false;

    startXRef.current = event.pageX;
    scrollLeftRef.current = track.scrollLeft;
  };

  // Di chuyển danh sách ảnh theo chuột.
  const handleMouseMove = (event) => {
    const track = thumbnailTrackRef.current;

    if (!isDraggingRef.current || !track) return;

    const distance = event.pageX - startXRef.current;

    if (Math.abs(distance) > 5) {
      hasDraggedRef.current = true;
    }

    if (hasDraggedRef.current) {
      track.scrollLeft = scrollLeftRef.current - distance;
    }
  };

  // Kết thúc thao tác kéo.
  const handleMouseUp = () => {
    isDraggingRef.current = false;
  };

  // Ngăn việc nhấn nhầm ảnh sau khi kéo.
  const handleThumbnailClick = (event) => {
    if (hasDraggedRef.current) {
      event.preventDefault();
      event.stopPropagation();
      hasDraggedRef.current = false;
    }
  };

  // Kiểm tra danh sách ảnh còn có thể cuộn theo hướng nào.
  const updateScrollButtons = () => {
    const track = thumbnailTrackRef.current;

    if (!track) return;

    const maxScrollLeft = track.scrollWidth - track.clientWidth;

    setCanScrollLeft(track.scrollLeft > 1);
    setCanScrollRight(track.scrollLeft < maxScrollLeft - 1);
  };

  // Cuộn danh sách ảnh thu nhỏ sang trái hoặc phải.
  const handleScrollThumbnail = (direction) => {
    const track = thumbnailTrackRef.current;

    if (!track) return;

    track.scrollBy({
      left: direction * 200,
      behavior: "smooth",
    });
  };

  // Tự động cuộn đến ảnh thu nhỏ đang được chọn.
  useEffect(() => {
    const track = thumbnailTrackRef.current;

    if (!track) return;

    const activeThumbnail = track.querySelector(".active");

    if (!activeThumbnail) return;

    const scrollPosition =
      activeThumbnail.offsetLeft -
      track.offsetLeft -
      (track.clientWidth - activeThumbnail.clientWidth) / 2;

    track.scrollTo({
      left: scrollPosition,
      behavior: "smooth",
    });
  }, [anhHienTai]);

  return (
    <div className="product-gallery">
      {/* Khu vực ảnh sản phẩm lớn */}
      {/* Khu vực ảnh sản phẩm lớn */}
      <div className="product-gallery__main">
        {loadingAnh ? (
          // Trạng thái đang tải ảnh.
          <p className="product-gallery__message">Đang tải hình ảnh...</p>
        ) : danhSachAnh.length > 0 ? (
          <>
            <Carousel
              activeIndex={anhHienTai}
              onSelect={(selectedIndex) => setAnhHienTai(selectedIndex)}
              indicators={false}
              interval={null}
              controls={true}
              wrap={false}
            >
              {danhSachAnh.map((anh, index) => (
                <Carousel.Item key={anh}>
                  <img
                    src={anh}
                    alt={`Ảnh sản phẩm ${index + 1}`}
                    className="product-gallery__image"
                    onClick={() => setIsLightboxOpen(true)}
                    role="button"
                    tabIndex={0}
                    aria-label="Nhấn để phóng to ảnh"
                    onKeyDown={(event) => {
                      if (event.key === "Enter" || event.key === " ") {
                        event.preventDefault();
                        setIsLightboxOpen(true);
                      }
                    }}
                  />
                </Carousel.Item>
              ))}
            </Carousel>

            {/* Bộ đếm ảnh */}
            <span className="product-gallery__counter">
              {anhHienTai + 1}/{danhSachAnh.length}
            </span>
          </>
        ) : (
          // Trường hợp phiên bản chưa có hình ảnh.
          <p className="product-gallery__message">Chưa có hình ảnh sản phẩm.</p>
        )}
      </div>

      {/* Băng chuyền ảnh thu nhỏ */}
      {!loadingAnh && danhSachAnh.length > 0 && (
        <div className="product-gallery__thumbnail-wrapper">
          {/* Nút cuộn sang trái */}
          {canScrollLeft && (
            <button
              type="button"
              className="product-gallery__scroll-button"
              onClick={() => handleScrollThumbnail(-1)}
              aria-label="Cuộn ảnh sang trái"
            >
              &#10094;
            </button>
          )}

          {/* Danh sách ảnh thu nhỏ */}
          <div
            className="product-gallery__thumbnails"
            ref={thumbnailTrackRef}
            onMouseDown={handleMouseDown}
            onMouseMove={handleMouseMove}
            onMouseUp={handleMouseUp}
            onMouseLeave={handleMouseUp}
            onClickCapture={handleThumbnailClick}
            onDragStart={(event) => event.preventDefault()}
            onScroll={updateScrollButtons}
          >
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

          {canScrollRight && (
            <button
              type="button"
              className="product-gallery__scroll-button"
              onClick={() => handleScrollThumbnail(1)}
              aria-label="Cuộn ảnh sang phải"
            >
              &#10095;
            </button>
          )}
        </div>
      )}

      {/* Cửa sổ xem ảnh phóng to */}
      <Lightbox
        open={isLightboxOpen}
        close={() => setIsLightboxOpen(false)}
        slides={danhSachAnh.map((anh) => ({
          src: anh,
        }))}
        index={anhHienTai}
        on={{
          view: ({ index }) => setAnhHienTai(index),
        }}
        plugins={[Zoom]}
        carousel={{
          finite: true,
        }}
      />
    </div>
  );
};

export default ProductGallery;
