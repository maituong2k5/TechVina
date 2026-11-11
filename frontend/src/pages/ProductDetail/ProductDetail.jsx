import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import { getThongSoKyThuat } from "../../services/technicalSpecificationsService";
import { getDanhSachPhienBan } from "../../services/productVersionService";
import { getThongTinSanPham } from "../../services/productInformationService";
import { getDanhSachAnh } from "../../services/productImageService";
import Breadcrumb from "./Breadcrumb/Breadcrumb";

// Component thư viện hình ảnh sản phẩm
import ProductGallery from "./ProductDetailMain/ProductGallery/ProductGallery";
import ProductInformation from "./ProductDetailMain/ProductInformation/ProductInformation";
import TechnicalSpecifications from "./ProductDetailMain/TechnicalSpecifications/TechnicalSpecifications";

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
  // Danh sách phiên bản kèm giá và tồn kho.
  const [danhSachPhienBan, setDanhSachPhienBan] = useState([]);

  // Lưu danh sách ảnh theo từng mã phiên bản.
  const [anhTheoPhienBan, setAnhTheoPhienBan] = useState({});

  // Trạng thái tải danh sách ảnh của các phiên bản.
  const [loadingAnhTheoPhienBan, setLoadingAnhTheoPhienBan] = useState(false);

  // Lưu mã phiên bản sản phẩm đang được chọn.
  const [selectedMaPB, setSelectedMaPB] = useState(null);

  // Lưu thông tin cơ bản của sản phẩm.
  const [thongTinSanPham, setThongTinSanPham] = useState(null);

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

  /**
   * Gọi API lấy danh sách phiên bản khi mã sản phẩm thay đổi.
   */
  useEffect(() => {
    const fetchDanhSachPhienBan = async () => {
      try {
        const data = await getDanhSachPhienBan(maSP);

        setDanhSachPhienBan(data);
      } catch (err) {
        console.error("Không thể tải danh sách phiên bản:", err);
      }
    };

    if (maSP) {
      fetchDanhSachPhienBan();
    }
  }, [maSP]);

  /**
   * Lấy danh sách ảnh của tất cả phiên bản sản phẩm.
   * Mỗi phiên bản được lưu ảnh theo mã MaPB.
   */
  useEffect(() => {
    if (danhSachPhienBan.length === 0) {
      setAnhTheoPhienBan({});
      setLoadingAnhTheoPhienBan(false);
      return;
    }

    let isMounted = true;

    const fetchAnhTheoPhienBan = async () => {
      setLoadingAnhTheoPhienBan(true);

      try {
        const ketQua = await Promise.all(
          danhSachPhienBan.map(async (phienBan) => {
            const danhSachAnh = await getDanhSachAnh(phienBan.maPB);

            return [phienBan.maPB, danhSachAnh.map((anh) => anh.duongDanAnh)];
          }),
        );

        if (isMounted) {
          setAnhTheoPhienBan(Object.fromEntries(ketQua));
        }
      } catch (error) {
        console.error("Không thể tải ảnh các phiên bản:", error);
      } finally {
        if (isMounted) {
          setLoadingAnhTheoPhienBan(false);
        }
      }
    };

    fetchAnhTheoPhienBan();

    return () => {
      isMounted = false;
    };
  }, [danhSachPhienBan]);

  /**
   * Lấy thông tin cơ bản khi mã sản phẩm thay đổi.
   */
  useEffect(() => {
    const fetchThongTinSanPham = async () => {
      try {
        const data = await getThongTinSanPham(maSP);

        setThongTinSanPham(data);
      } catch (error) {
        console.error("Không thể tải thông tin sản phẩm:", error);
      }
    };

    if (maSP) {
      fetchThongTinSanPham();
    }
  }, [maSP]);

  /**
   * Tự động chọn phiên bản đầu tiên khi tải danh sách phiên bản.
   */
  useEffect(() => {
    if (danhSachPhienBan.length > 0) {
      const maPBHopLe = danhSachPhienBan.some(
        (phienBan) => phienBan.maPB === selectedMaPB,
      );

      // Chỉ chọn phiên bản đầu tiên nếu chưa có phiên bản hợp lệ.
      if (!maPBHopLe) {
        setSelectedMaPB(danhSachPhienBan[0].maPB);
      }
    }
  }, [danhSachPhienBan, selectedMaPB]);

  return (
    <div className="product-detail">
      <div className="container">
        {/* Đường dẫn điều hướng */}
        <Breadcrumb items={breadcrumbItems} />

        {/* Khu vực chính: hình ảnh bên trái, thông tin bên phải */}
        <div className="product-detail-main">
          <div className="product-detail-main__gallery">
            <ProductGallery
              key={selectedMaPB}
              selectedMaPB={selectedMaPB}
              danhSachAnh={anhTheoPhienBan[selectedMaPB] || []}
              loadingAnh={loadingAnhTheoPhienBan}
            />
          </div>

          <div className="product-detail-main__information">
            <ProductInformation
              danhSachPhienBan={danhSachPhienBan}
              thongTinSanPham={thongTinSanPham}
              selectedMaPB={selectedMaPB}
              setSelectedMaPB={setSelectedMaPB}
              anhTheoPhienBan={anhTheoPhienBan}
            />
          </div>
        </div>

        <section className="technical-specifications">
          {/* Trạng thái đang tải */}
          {loading && <p>Đang tải thông số kỹ thuật...</p>}

          {/* Trạng thái lỗi */}
          {!loading && error && <p>{error}</p>}

          {/* Hiển thị thông số kỹ thuật */}
          {!loading && !error && danhSachThongSo.length > 0 && (
            <TechnicalSpecifications danhSachThongSo={danhSachThongSo} />
          )}

          {/* Trường hợp chưa có thông số */}
          {!loading && !error && danhSachThongSo.length === 0 && (
            <p>Chưa có thông số kỹ thuật.</p>
          )}
        </section>
      </div>
    </div>
  );
};

export default ProductDetail;
