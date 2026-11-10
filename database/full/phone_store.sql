-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: phone_store
-- ------------------------------------------------------
-- Server version	8.0.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `phone_store`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `phone_store` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `phone_store`;

--
-- Table structure for table `anh`
--

DROP TABLE IF EXISTS `anh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anh` (
  `MaAnh` int NOT NULL AUTO_INCREMENT,
  `MaPB` int NOT NULL,
  `DuongDanAnh` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ThuTuHienThi` int NOT NULL,
  PRIMARY KEY (`MaAnh`),
  UNIQUE KEY `UQ_PhienBan_Anh_DuongDan` (`MaPB`,`DuongDanAnh`),
  UNIQUE KEY `UQ_PhienBan_Anh_ThuTuHienThi` (`MaPB`,`ThuTuHienThi`),
  CONSTRAINT `FK_Anh_PhienBan` FOREIGN KEY (`MaPB`) REFERENCES `phienban` (`MaPB`),
  CONSTRAINT `anh_chk_1` CHECK ((`ThuTuHienThi` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cauhinhdungluong`
--

DROP TABLE IF EXISTS `cauhinhdungluong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cauhinhdungluong` (
  `MaCHDL` int NOT NULL AUTO_INCREMENT,
  `MaRam` int NOT NULL,
  `MaRom` int NOT NULL,
  PRIMARY KEY (`MaCHDL`),
  UNIQUE KEY `UQ_CauHinhDungLuong_RAM_ROM` (`MaRam`,`MaRom`),
  KEY `FK_CauHinhDungLuong_Rom` (`MaRom`),
  CONSTRAINT `FK_CauHinhDungLuong_Ram` FOREIGN KEY (`MaRam`) REFERENCES `ram` (`MaRam`),
  CONSTRAINT `FK_CauHinhDungLuong_Rom` FOREIGN KEY (`MaRom`) REFERENCES `rom` (`MaRom`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chitietdonhang`
--

DROP TABLE IF EXISTS `chitietdonhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietdonhang` (
  `MaCTDH` int NOT NULL AUTO_INCREMENT,
  `MaDH` int NOT NULL,
  `MaPB` int NOT NULL,
  `SoLuong` int NOT NULL,
  `DonGia` decimal(15,2) NOT NULL,
  `ThanhTien` decimal(15,2) NOT NULL,
  PRIMARY KEY (`MaCTDH`),
  UNIQUE KEY `UQ_ChiTietDonHang_DonHang_PhienBan` (`MaDH`,`MaPB`),
  KEY `FK_ChiTietDonHang_PhienBan` (`MaPB`),
  CONSTRAINT `FK_ChiTietDonHang_DonHang` FOREIGN KEY (`MaDH`) REFERENCES `donhang` (`MaDH`),
  CONSTRAINT `FK_ChiTietDonHang_PhienBan` FOREIGN KEY (`MaPB`) REFERENCES `phienban` (`MaPB`),
  CONSTRAINT `CK_ChiTietDonHang_DonGia` CHECK ((`DonGia` > 0)),
  CONSTRAINT `CK_ChiTietDonHang_SoLuong` CHECK ((`SoLuong` > 0)),
  CONSTRAINT `CK_ChiTietDonHang_ThanhTien` CHECK ((`ThanhTien` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chitietgiamgia`
--

DROP TABLE IF EXISTS `chitietgiamgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietgiamgia` (
  `MaCTGG` int NOT NULL AUTO_INCREMENT,
  `MaGiamGia` int NOT NULL,
  `MaPB` int NOT NULL,
  `GiaTriGiamRieng` decimal(15,2) DEFAULT NULL COMMENT 'Mức giảm riêng, NULL thì sử dụng mức giảm mặc định',
  PRIMARY KEY (`MaCTGG`),
  UNIQUE KEY `UQ_ChiTietGiamGia` (`MaGiamGia`,`MaPB`),
  KEY `FK_ChiTietGiamGia_PhienBan` (`MaPB`),
  CONSTRAINT `FK_ChiTietGiamGia_GiamGia` FOREIGN KEY (`MaGiamGia`) REFERENCES `giamgia` (`MaGiamGia`),
  CONSTRAINT `FK_ChiTietGiamGia_PhienBan` FOREIGN KEY (`MaPB`) REFERENCES `phienban` (`MaPB`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chitietgiohang`
--

DROP TABLE IF EXISTS `chitietgiohang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietgiohang` (
  `MaCTGH` int NOT NULL AUTO_INCREMENT,
  `MaGH` int NOT NULL,
  `MaPB` int NOT NULL,
  `SoLuong` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`MaCTGH`),
  UNIQUE KEY `UQ_ChiTietGioHang_GioHang_PhienBan` (`MaGH`,`MaPB`),
  KEY `FK_ChiTietGioHang_PhienBan` (`MaPB`),
  CONSTRAINT `FK_ChiTietGioHang_GioHang` FOREIGN KEY (`MaGH`) REFERENCES `giohang` (`MaGH`),
  CONSTRAINT `FK_ChiTietGioHang_PhienBan` FOREIGN KEY (`MaPB`) REFERENCES `phienban` (`MaPB`),
  CONSTRAINT `CK_ChiTietGioHang_SoLuong` CHECK ((`SoLuong` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chitietphieunhap`
--

DROP TABLE IF EXISTS `chitietphieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietphieunhap` (
  `MaCTPN` int NOT NULL AUTO_INCREMENT,
  `MaPN` int NOT NULL,
  `MaPB` int NOT NULL,
  `SoLuong` int NOT NULL,
  `GiaNhap` decimal(15,2) NOT NULL,
  PRIMARY KEY (`MaCTPN`),
  UNIQUE KEY `UQ_ChiTietPhieuNhap_PN_PB` (`MaPN`,`MaPB`),
  KEY `FK_PhienBan_ChiTietPhieuNhap` (`MaPB`),
  CONSTRAINT `FK_PhienBan_ChiTietPhieuNhap` FOREIGN KEY (`MaPB`) REFERENCES `phienban` (`MaPB`),
  CONSTRAINT `FK_PhieuNhap_ChiTietPhieuNhap` FOREIGN KEY (`MaPN`) REFERENCES `phieunhap` (`MaPN`),
  CONSTRAINT `chitietphieunhap_chk_1` CHECK ((`SoLuong` > 0)),
  CONSTRAINT `chitietphieunhap_chk_2` CHECK ((`GiaNhap` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chitietthongso`
--

DROP TABLE IF EXISTS `chitietthongso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietthongso` (
  `MaCTTS` int NOT NULL AUTO_INCREMENT,
  `MaSP` int NOT NULL,
  `MaThongSo` int NOT NULL,
  `GiaTri` text COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`MaCTTS`),
  UNIQUE KEY `UQ_ChiTietThongSo` (`MaSP`,`MaThongSo`),
  KEY `FK_ChiTietThongSo_ThongSo` (`MaThongSo`),
  CONSTRAINT `FK_ChiTietThongSo_SanPham` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`),
  CONSTRAINT `FK_ChiTietThongSo_ThongSo` FOREIGN KEY (`MaThongSo`) REFERENCES `thongsokythuat` (`MaThongSo`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `danhmuc`
--

DROP TABLE IF EXISTS `danhmuc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danhmuc` (
  `MaDanhMuc` int NOT NULL AUTO_INCREMENT,
  `TenDanhMuc` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DuongDanAnh` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ThuTuHienThi` int NOT NULL DEFAULT '0',
  `TrangThai` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`MaDanhMuc`),
  UNIQUE KEY `TenDanhMuc` (`TenDanhMuc`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `diachi`
--

DROP TABLE IF EXISTS `diachi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diachi` (
  `MaDiaChi` int NOT NULL AUTO_INCREMENT,
  `MaKH` int NOT NULL,
  `HoTenNguoiNhan` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `SoDienThoai` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TinhThanh` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `QuanHuyen` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `PhuongXa` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DiaChiChiTiet` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MacDinh` tinyint(1) NOT NULL DEFAULT '0',
  `TrangThai` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`MaDiaChi`),
  KEY `FK_DiaChi_KhachHang` (`MaKH`),
  CONSTRAINT `FK_DiaChi_KhachHang` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `donhang`
--

DROP TABLE IF EXISTS `donhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donhang` (
  `MaDH` int NOT NULL AUTO_INCREMENT,
  `MaKH` int NOT NULL,
  `MaTrangThai` int NOT NULL DEFAULT '1',
  `HoTenNguoiNhan` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `SoDienThoaiNguoiNhan` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TinhThanh` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `QuanHuyen` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `PhuongXa` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DiaChiChiTiet` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TongTien` decimal(15,2) NOT NULL DEFAULT '0.00',
  `NgayDat` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`MaDH`),
  KEY `FK_DonHang_KhachHang` (`MaKH`),
  KEY `FK_DonHang_TrangThai` (`MaTrangThai`),
  CONSTRAINT `FK_DonHang_KhachHang` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  CONSTRAINT `FK_DonHang_TrangThai` FOREIGN KEY (`MaTrangThai`) REFERENCES `trangthaidonhang` (`MaTrangThai`),
  CONSTRAINT `CK_DonHang_TongTien` CHECK ((`TongTien` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `giamgia`
--

DROP TABLE IF EXISTS `giamgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giamgia` (
  `MaGiamGia` int NOT NULL AUTO_INCREMENT,
  `TenGiamGia` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `LoaiGiamGia` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `GiaTriGiam` decimal(15,2) NOT NULL,
  `NgayBatDau` datetime NOT NULL,
  `NgayKetThuc` datetime NOT NULL,
  `TrangThai` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`MaGiamGia`),
  CONSTRAINT `CK_GiamGia_GiaTri` CHECK ((`GiaTriGiam` > 0)),
  CONSTRAINT `CK_GiamGia_Loai` CHECK ((`LoaiGiamGia` in (_utf8mb4'PERCENT',_utf8mb4'FIXED'))),
  CONSTRAINT `CK_GiamGia_PhanTram` CHECK (((`LoaiGiamGia` <> _utf8mb4'PERCENT') or (`GiaTriGiam` <= 100))),
  CONSTRAINT `CK_GiamGia_ThoiGian` CHECK ((`NgayKetThuc` > `NgayBatDau`)),
  CONSTRAINT `CK_GiamGia_TrangThai` CHECK ((`TrangThai` in (0,1)))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `giohang`
--

DROP TABLE IF EXISTS `giohang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giohang` (
  `MaGH` int NOT NULL AUTO_INCREMENT,
  `MaKH` int NOT NULL,
  `NgayTao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `NgayCapNhat` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`MaGH`),
  UNIQUE KEY `MaKH` (`MaKH`),
  CONSTRAINT `FK_GioHang_KhachHang` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hang`
--

DROP TABLE IF EXISTS `hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hang` (
  `MaHang` int NOT NULL AUTO_INCREMENT,
  `TenHang` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`MaHang`),
  UNIQUE KEY `TenHang` (`TenHang`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `MaKH` int NOT NULL AUTO_INCREMENT,
  `HoTen` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `SoDienThoai` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `MatKhau` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `GioiTinh` varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT '1',
  `MaVaiTro` int NOT NULL DEFAULT '1',
  `CognitoSub` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`MaKH`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `SoDienThoai` (`SoDienThoai`),
  UNIQUE KEY `UQ_KhachHang_CognitoSub` (`CognitoSub`),
  KEY `FK_KhachHang_VaiTro` (`MaVaiTro`),
  CONSTRAINT `FK_KhachHang_VaiTro` FOREIGN KEY (`MaVaiTro`) REFERENCES `vaitro` (`MaVaiTro`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `kho`
--

DROP TABLE IF EXISTS `kho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kho` (
  `MaKho` int NOT NULL AUTO_INCREMENT,
  `TenKho` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DiaChi` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`MaKho`),
  UNIQUE KEY `TenKho` (`TenKho`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lichsugia`
--

DROP TABLE IF EXISTS `lichsugia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lichsugia` (
  `MaLSG` int NOT NULL AUTO_INCREMENT,
  `MaPB` int NOT NULL,
  `Gia` decimal(15,2) NOT NULL,
  `NgayBatDau` datetime NOT NULL,
  `NgayKetThuc` datetime DEFAULT NULL,
  PRIMARY KEY (`MaLSG`),
  UNIQUE KEY `UQ_PhienBan_LichSuGia` (`MaPB`,`NgayBatDau`),
  CONSTRAINT `FK_LichSuGia_PhienBan` FOREIGN KEY (`MaPB`) REFERENCES `phienban` (`MaPB`),
  CONSTRAINT `CK_LichSuGia_ThoiGian` CHECK (((`NgayKetThuc` is null) or (`NgayKetThuc` > `NgayBatDau`))),
  CONSTRAINT `lichsugia_chk_1` CHECK ((`Gia` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lichsutrangthainhacungcap`
--

DROP TABLE IF EXISTS `lichsutrangthainhacungcap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lichsutrangthainhacungcap` (
  `MaLichSu` int NOT NULL AUTO_INCREMENT,
  `MaNCC` int NOT NULL,
  `MaTrangThaiCu` int NOT NULL,
  `MaTrangThaiMoi` int NOT NULL,
  `ThoiDiem` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MaNhanVien` int NOT NULL,
  PRIMARY KEY (`MaLichSu`),
  UNIQUE KEY `UQ_LS_NCC_ThoiDiem` (`MaNCC`,`ThoiDiem`),
  KEY `FK_LS_NCC_TrangThaiCu` (`MaTrangThaiCu`),
  KEY `FK_LS_NCC_TrangThaiMoi` (`MaTrangThaiMoi`),
  KEY `FK_LS_NhanVien` (`MaNhanVien`),
  CONSTRAINT `FK_LS_NCC` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`),
  CONSTRAINT `FK_LS_NCC_TrangThaiCu` FOREIGN KEY (`MaTrangThaiCu`) REFERENCES `trangthainhacungcap` (`MaTrangThai`),
  CONSTRAINT `FK_LS_NCC_TrangThaiMoi` FOREIGN KEY (`MaTrangThaiMoi`) REFERENCES `trangthainhacungcap` (`MaTrangThai`),
  CONSTRAINT `FK_LS_NhanVien` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`),
  CONSTRAINT `CK_LS_NCC_TrangThai` CHECK ((`MaTrangThaiCu` <> `MaTrangThaiMoi`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mausac`
--

DROP TABLE IF EXISTS `mausac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mausac` (
  `MaMauSac` int NOT NULL AUTO_INCREMENT,
  `TenMau` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`MaMauSac`),
  UNIQUE KEY `TenMau` (`TenMau`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nhacungcap`
--

DROP TABLE IF EXISTS `nhacungcap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhacungcap` (
  `MaNCC` int NOT NULL AUTO_INCREMENT,
  `TenNCC` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaTrangThai` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`MaNCC`),
  UNIQUE KEY `TenNCC` (`TenNCC`),
  KEY `FK_NhaCungCap_TrangThaiNhaCungCap` (`MaTrangThai`),
  CONSTRAINT `FK_NhaCungCap_TrangThaiNhaCungCap` FOREIGN KEY (`MaTrangThai`) REFERENCES `trangthainhacungcap` (`MaTrangThai`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `MaNhanVien` int NOT NULL AUTO_INCREMENT,
  `HoTen` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `NgaySinh` date NOT NULL,
  `GioiTinh` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MatKhau` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DuongDanAnh` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT '1',
  `MaVaiTro` int NOT NULL DEFAULT '2',
  `CognitoSub` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`MaNhanVien`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `UQ_NhanVien_CognitoSub` (`CognitoSub`),
  KEY `FK_NhanVien_VaiTro` (`MaVaiTro`),
  CONSTRAINT `FK_NhanVien_VaiTro` FOREIGN KEY (`MaVaiTro`) REFERENCES `vaitro` (`MaVaiTro`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nhomthongso`
--

DROP TABLE IF EXISTS `nhomthongso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhomthongso` (
  `MaNhom` int NOT NULL AUTO_INCREMENT,
  `TenNhom` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ThuTuHienThi` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`MaNhom`),
  UNIQUE KEY `TenNhom` (`TenNhom`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `phienban`
--

DROP TABLE IF EXISTS `phienban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phienban` (
  `MaPB` int NOT NULL AUTO_INCREMENT,
  `SKU` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaSP` int NOT NULL,
  `MaCHDL` int NOT NULL,
  `MaMauSac` int NOT NULL,
  `MaThiTruong` int NOT NULL,
  `NoiBat` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`MaPB`),
  UNIQUE KEY `SKU` (`SKU`),
  UNIQUE KEY `UQ_PhienBan_SanPham_CauHinhDungLuong_MauSac_ThiTruong` (`MaSP`,`MaCHDL`,`MaMauSac`,`MaThiTruong`),
  KEY `FK_PhienBan_CauHinhDungLuong` (`MaCHDL`),
  KEY `FK_PhienBan_MauSac` (`MaMauSac`),
  KEY `FK_PhienBan_ThiTruong` (`MaThiTruong`),
  CONSTRAINT `FK_PhienBan_CauHinhDungLuong` FOREIGN KEY (`MaCHDL`) REFERENCES `cauhinhdungluong` (`MaCHDL`),
  CONSTRAINT `FK_PhienBan_MauSac` FOREIGN KEY (`MaMauSac`) REFERENCES `mausac` (`MaMauSac`),
  CONSTRAINT `FK_PhienBan_SanPham` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`),
  CONSTRAINT `FK_PhienBan_ThiTruong` FOREIGN KEY (`MaThiTruong`) REFERENCES `thitruong` (`MaThiTruong`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `phieunhap`
--

DROP TABLE IF EXISTS `phieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieunhap` (
  `MaPN` int NOT NULL AUTO_INCREMENT,
  `MaNCC` int NOT NULL,
  `MaKho` int NOT NULL,
  `NgayTao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `NgayNhap` datetime DEFAULT NULL,
  `MaTrangThai` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`MaPN`),
  KEY `FK_PhieuNhap_NhaCungCap` (`MaNCC`),
  KEY `FK_PhieuNhap_TrangThai` (`MaTrangThai`),
  KEY `FK_PhieuNhap_Kho` (`MaKho`),
  CONSTRAINT `FK_PhieuNhap_Kho` FOREIGN KEY (`MaKho`) REFERENCES `kho` (`MaKho`),
  CONSTRAINT `FK_PhieuNhap_NhaCungCap` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`),
  CONSTRAINT `FK_PhieuNhap_TrangThai` FOREIGN KEY (`MaTrangThai`) REFERENCES `trangthaiphieunhap` (`MaTrangThai`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ram`
--

DROP TABLE IF EXISTS `ram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ram` (
  `MaRam` int NOT NULL AUTO_INCREMENT,
  `DungLuong` int NOT NULL,
  PRIMARY KEY (`MaRam`),
  UNIQUE KEY `DungLuong` (`DungLuong`),
  CONSTRAINT `ram_chk_1` CHECK ((`DungLuong` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `refreshtoken`
--

DROP TABLE IF EXISTS `refreshtoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refreshtoken` (
  `MaRefreshToken` int NOT NULL AUTO_INCREMENT,
  `MaKH` int DEFAULT NULL,
  `MaNhanVien` int DEFAULT NULL,
  `TokenHash` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `NgayTao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `NgayHetHan` datetime NOT NULL,
  `NgayThuHoi` datetime DEFAULT NULL,
  PRIMARY KEY (`MaRefreshToken`),
  UNIQUE KEY `UK_RefreshToken_TokenHash` (`TokenHash`),
  KEY `IDX_RefreshToken_MaKH` (`MaKH`),
  KEY `FK_RefreshToken_NhanVien` (`MaNhanVien`),
  CONSTRAINT `FK_RefreshToken_KhachHang` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`) ON DELETE CASCADE,
  CONSTRAINT `FK_RefreshToken_NhanVien` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON DELETE CASCADE,
  CONSTRAINT `CK_RefreshToken_Account` CHECK ((((`MaKH` is not null) and (`MaNhanVien` is null)) or ((`MaKH` is null) and (`MaNhanVien` is not null))))
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rom`
--

DROP TABLE IF EXISTS `rom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rom` (
  `MaRom` int NOT NULL AUTO_INCREMENT,
  `DungLuong` int NOT NULL,
  PRIMARY KEY (`MaRom`),
  UNIQUE KEY `DungLuong` (`DungLuong`),
  CONSTRAINT `rom_chk_1` CHECK ((`DungLuong` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanpham` (
  `MaSP` int NOT NULL AUTO_INCREMENT,
  `TenSP` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaModel` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaDanhMuc` int NOT NULL,
  `MaHang` int NOT NULL,
  `MoTa` text COLLATE utf8mb4_unicode_ci,
  `TrangThai` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`MaSP`),
  UNIQUE KEY `UQ_SanPham_Hang_Model` (`MaHang`,`MaModel`),
  UNIQUE KEY `UQ_SanPham_TenSP` (`TenSP`),
  KEY `FK_SanPham_DanhMuc` (`MaDanhMuc`),
  CONSTRAINT `FK_SanPham_DanhMuc` FOREIGN KEY (`MaDanhMuc`) REFERENCES `danhmuc` (`MaDanhMuc`),
  CONSTRAINT `FK_SanPham_Hang` FOREIGN KEY (`MaHang`) REFERENCES `hang` (`MaHang`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thanhtoan`
--

DROP TABLE IF EXISTS `thanhtoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thanhtoan` (
  `MaTT` int NOT NULL AUTO_INCREMENT,
  `MaDH` int NOT NULL,
  `PhuongThuc` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `SoTien` decimal(15,2) NOT NULL,
  `TrangThai` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `NgayThanhToan` datetime DEFAULT NULL,
  PRIMARY KEY (`MaTT`),
  UNIQUE KEY `MaDH` (`MaDH`),
  CONSTRAINT `FK_ThanhToan_DonHang` FOREIGN KEY (`MaDH`) REFERENCES `donhang` (`MaDH`),
  CONSTRAINT `CK_ThanhToan_SoTien` CHECK ((`SoTien` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thitruong`
--

DROP TABLE IF EXISTS `thitruong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thitruong` (
  `MaThiTruong` int NOT NULL AUTO_INCREMENT,
  `MaCode` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TenThiTruong` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`MaThiTruong`),
  UNIQUE KEY `MaCode` (`MaCode`),
  UNIQUE KEY `TenThiTruong` (`TenThiTruong`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thongsokythuat`
--

DROP TABLE IF EXISTS `thongsokythuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thongsokythuat` (
  `MaThongSo` int NOT NULL AUTO_INCREMENT,
  `MaNhom` int NOT NULL,
  `TenThongSo` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DonVi` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ThuTuHienThi` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`MaThongSo`),
  UNIQUE KEY `UQ_ThongSoKyThuat` (`MaNhom`,`TenThongSo`),
  CONSTRAINT `FK_ThongSoKyThuat_NhomThongSo` FOREIGN KEY (`MaNhom`) REFERENCES `nhomthongso` (`MaNhom`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tonkho`
--

DROP TABLE IF EXISTS `tonkho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tonkho` (
  `MaTonKho` int NOT NULL AUTO_INCREMENT,
  `MaKho` int NOT NULL,
  `MaPB` int NOT NULL,
  `SoLuongTon` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`MaTonKho`),
  UNIQUE KEY `MaKho` (`MaKho`,`MaPB`),
  KEY `FK_TonKho_PhienBan` (`MaPB`),
  CONSTRAINT `FK_TonKho_Kho` FOREIGN KEY (`MaKho`) REFERENCES `kho` (`MaKho`),
  CONSTRAINT `FK_TonKho_PhienBan` FOREIGN KEY (`MaPB`) REFERENCES `phienban` (`MaPB`),
  CONSTRAINT `CK_TonKho_SoLuong` CHECK ((`SoLuongTon` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `trangthaidonhang`
--

DROP TABLE IF EXISTS `trangthaidonhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trangthaidonhang` (
  `MaTrangThai` int NOT NULL AUTO_INCREMENT,
  `TenTrangThai` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`MaTrangThai`),
  UNIQUE KEY `TenTrangThai` (`TenTrangThai`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `trangthainhacungcap`
--

DROP TABLE IF EXISTS `trangthainhacungcap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trangthainhacungcap` (
  `MaTrangThai` int NOT NULL,
  `TenTrangThai` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`MaTrangThai`),
  UNIQUE KEY `TenTrangThai` (`TenTrangThai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `trangthaiphieunhap`
--

DROP TABLE IF EXISTS `trangthaiphieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trangthaiphieunhap` (
  `MaTrangThai` int NOT NULL AUTO_INCREMENT,
  `TenTrangThai` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`MaTrangThai`),
  UNIQUE KEY `TenTrangThai` (`TenTrangThai`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vaitro`
--

DROP TABLE IF EXISTS `vaitro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vaitro` (
  `MaVaiTro` int NOT NULL AUTO_INCREMENT,
  `TenVaiTro` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MoTa` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`MaVaiTro`),
  UNIQUE KEY `TenVaiTro` (`TenVaiTro`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video` (
  `MaVideo` int NOT NULL AUTO_INCREMENT,
  `MaPB` int NOT NULL,
  `DuongDanVideo` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`MaVideo`),
  UNIQUE KEY `UQ_Video_PhienBan` (`MaPB`),
  CONSTRAINT `FK_Video_PhienBan` FOREIGN KEY (`MaPB`) REFERENCES `phienban` (`MaPB`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: phone_store
-- ------------------------------------------------------
-- Server version	8.0.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `anh`
--

LOCK TABLES `anh` WRITE;
/*!40000 ALTER TABLE `anh` DISABLE KEYS */;
INSERT INTO `anh` VALUES (1,21,'/images/products/xiaomi/xiaomi-series/xiaomi-15/image_1262703568.webp',1),(2,22,'/images/products/xiaomi/xiaomi-series/xiaomi-15/image_1262703568.webp',1),(3,23,'/images/products/xiaomi/xiaomi-series/xiaomi-15/dien-thoai-xiaomi-15_5__1_1_1_1_1.webp',2),(4,24,'/images/products/xiaomi/xiaomi-series/xiaomi-15/dien-thoai-xiaomi-15_6__1_1_1_1.webp',2),(5,25,'/images/products/xiaomi/xiaomi-series/xiaomi-15/dien-thoai-xiaomi-15_14__1_1_1_1.webp',1),(6,26,'/images/products/xiaomi/xiaomi-series/xiaomi-15/image_1262703567.webp',1),(7,31,'/images/products/xiaomi/xiaomi-series/xiaomi-15-ultra/photo_2025-04-16_11-45-51.webp',1),(8,32,'/images/products/xiaomi/xiaomi-series/photo_2025-04-16_11-45-37.webp',1),(9,33,'/images/products/xiaomi/xiaomi-series/xiaomi-15-ultra/dien-thoai-xiaomi-15-ultra_12_.webp',1),(10,34,'/images/products/xiaomi/xiaomi-series/xiaomi-15-ultra/dien-thoai-xiaomi-15-ultra.webp',1),(11,35,'/images/products/xiaomi/xiaomi-series/xiaomi-15t/_i_n_tho_i_xiaomi_15t_12gb_256gb.webp',1),(12,38,'/images/products/xiaomi/xiaomi-series/xiaomi-15t/_i_n_tho_i_xiaomi_15t_12gb_256gb.webp',1),(13,36,'/images/products/xiaomi/xiaomi-series/xiaomi-15t/_i_n_tho_i_xiaomi_15t_12gb_256gb.webp',1),(14,39,'/images/products/xiaomi/xiaomi-series/xiaomi-15t/_i_n_tho_i_xiaomi_15t_12gb_256gb.webp',1),(15,37,'/images/products/xiaomi/xiaomi-series/xiaomi-15t/_i_n_tho_i_xiaomi_15t_12gb_256gb.webp',1),(16,40,'/images/products/xiaomi/xiaomi-series/xiaomi-15t/_i_n_tho_i_xiaomi_15t_12gb_256gb.webp',1),(17,41,'/images/products/apple/iphone-duo/iphone-duo-star-white-pdp-image-position-1-vi-vn-015908720.webp',1),(18,42,'/images/products/apple/iphone-duo/iphone-duo-night-sky-pdp-image-position-1-vi-vn-020220056.webp',1),(19,43,'/images/products/apple/iphone-duo/iphone-duo-star-white-pdp-image-position-1-vi-vn-015908720.webp',1),(20,44,'/images/products/apple/iphone-duo/iphone-duo-night-sky-pdp-image-position-1-vi-vn-020220056.webp',1),(21,45,'/images/products/apple/iphone-duo/iphone-duo-star-white-pdp-image-position-1-vi-vn-015908720.webp',1),(22,46,'/images/products/apple/iphone-duo/iphone-duo-night-sky-pdp-image-position-1-vi-vn-020220056.webp',1),(23,47,'/images/products/apple/iphone-duo/iphone-duo-star-white-pdp-image-position-1-vi-vn-015908720.webp',1),(24,48,'/images/products/apple/iphone-duo/iphone-duo-night-sky-pdp-image-position-1-vi-vn-020220056.webp',1),(25,21,'/images/products/xiaomi/xiaomi-series/xiaomi-15/image_1262703567.webp',2),(26,21,'/images/products/xiaomi/xiaomi-series/xiaomi-15/image_1262703569.webp',3),(27,21,'/images/products/xiaomi/xiaomi-series/xiaomi-15/dien-thoai-xiaomi-15_12__1_1_1_1.webp',4),(28,21,'/images/products/xiaomi/xiaomi-series/xiaomi-15/xiaomi_15_-_1_2.webp',5),(29,21,'/images/products/xiaomi/xiaomi-series/xiaomi-15/xiaomi_15_-_2_2.webp',6),(30,21,'/images/products/xiaomi/xiaomi-series/xiaomi-15/xiaomi_15_-_3_2.webp',7),(31,21,'/images/products/xiaomi/xiaomi-series/xiaomi-15/xiaomi_15_-_4_2.webp',8),(32,21,'/images/products/xiaomi/xiaomi-series/xiaomi-15/xiaomi_15_-_5_2.webp',9),(33,23,'/images/products/xiaomi/xiaomi-series/xiaomi-15/dien-thoai-xiaomi-15_12__1_1_1_1.webp',1),(35,24,'/images/products/xiaomi/xiaomi-series/xiaomi-15/dien-thoai-xiaomi-15_12__1_1_1_1.webp',1),(36,24,'/images/products/xiaomi/xiaomi-series/xiaomi-15/dien-thoai-xiaomi-15_5__1_1_1_1_1.webp',4);
/*!40000 ALTER TABLE `anh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cauhinhdungluong`
--

LOCK TABLES `cauhinhdungluong` WRITE;
/*!40000 ALTER TABLE `cauhinhdungluong` DISABLE KEYS */;
INSERT INTO `cauhinhdungluong` VALUES (1,1,1),(2,1,2),(3,2,2),(4,3,2),(5,3,3),(6,4,3),(7,4,4),(8,5,4),(9,5,5),(11,5,6),(10,6,5);
/*!40000 ALTER TABLE `cauhinhdungluong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `chitietgiamgia`
--

LOCK TABLES `chitietgiamgia` WRITE;
/*!40000 ALTER TABLE `chitietgiamgia` DISABLE KEYS */;
INSERT INTO `chitietgiamgia` VALUES (1,1,21,NULL),(2,1,23,NULL),(3,1,25,NULL),(4,1,22,7510000.00),(5,1,24,7510000.00),(6,1,26,7510000.00),(7,2,31,8870000.00),(8,2,32,8870000.00),(9,2,33,10260000.00),(10,2,34,10260000.00),(11,3,35,NULL),(12,3,36,NULL),(13,3,37,NULL),(14,3,38,2400000.00),(15,3,39,2400000.00),(16,3,40,2400000.00);
/*!40000 ALTER TABLE `chitietgiamgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `chitietphieunhap`
--

LOCK TABLES `chitietphieunhap` WRITE;
/*!40000 ALTER TABLE `chitietphieunhap` DISABLE KEYS */;
INSERT INTO `chitietphieunhap` VALUES (1,1,1,10,32500000.00),(2,1,2,10,30000000.00),(3,2,3,8,28500000.00),(4,2,4,15,10500000.00),(5,3,5,10,14000000.00),(6,3,6,10,15500000.00),(7,4,7,8,21000000.00),(8,4,8,12,15500000.00),(9,5,9,6,27000000.00),(10,5,10,12,11200000.00),(11,6,11,8,12500000.00),(12,6,12,8,16500000.00),(13,7,13,15,8000000.00),(14,7,14,15,7200000.00),(15,8,15,10,9500000.00),(16,8,16,6,13000000.00),(17,8,17,5,16000000.00),(18,8,18,4,28500000.00),(19,8,19,8,12000000.00),(20,8,20,8,14500000.00);
/*!40000 ALTER TABLE `chitietphieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `chitietthongso`
--

LOCK TABLES `chitietthongso` WRITE;
/*!40000 ALTER TABLE `chitietthongso` DISABLE KEYS */;
INSERT INTO `chitietthongso` VALUES (1,21,5,'AMOLED'),(2,21,6,'6.36 inch'),(3,21,7,'2670 × 1200'),(4,21,8,'120 Hz'),(5,21,32,'3200 nits'),(6,21,9,'Snapdragon 8 Elite'),(7,21,10,'2 nhân 4.32 GHz & 6 nhân 3.53 GHz'),(8,21,11,'Xiaomi HyperOS 2'),(9,21,33,'Adreno'),(10,21,12,'50MP (f/1.62) × 50MP (f/2.0) × 50MP (f/2.2) (góc rộng)'),(11,21,13,'8K @ 24fps/30fps, 4K @ 24fps/30fps/60fps'),(12,21,14,'Leica VARIO-SUMMILUX 1:1.62-2.2/14-60 ASPH. Ba ống kính, tám tiêu cự: Camera chính Leica, Camera tele Leica, Camera trước'),(13,21,15,'32MP (F/2.0)'),(14,21,17,'Tiêu cự 21mm\nHDR\nChế độ ban đêm\nChế độ chân dung\nChụp ảnh bằng giọng nói\nQuay video HDR10+'),(15,21,18,'5240mAh'),(16,21,20,'90W'),(17,21,21,'5G'),(18,21,22,'SIM kép (nano SIM + nano SIM/nano SIM + eSIM/eSIM + eSIM)'),(19,21,23,'Wi-Fi 7'),(20,21,24,'Bluetooth V6.0'),(21,21,26,'Type-C'),(22,21,36,'Cảm biến vân tay siêu âm'),(23,21,28,'152.3 × 71.2 × 8.08mm (Đen, Trắng, Xanh lá)\n152.3 × 71.2 × 8.48mm (Bạc Ánh Kim)'),(24,21,29,'191g (Đen, Trắng, Xanh lá)\n192g (Bạc Ánh Kim)'),(25,21,30,'Kính cường lực Xiaomi Shield\nKhung nhôm cường lực'),(26,21,31,'Kháng nước, kháng bụi IP68');
/*!40000 ALTER TABLE `chitietthongso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `danhmuc`
--

LOCK TABLES `danhmuc` WRITE;
/*!40000 ALTER TABLE `danhmuc` DISABLE KEYS */;
INSERT INTO `danhmuc` VALUES (1,'Điện thoại','/images/categories/387731_icon-menu-dien-thoai.webp',1,1),(2,'Laptop','/images/categories/836636_laptop.webp',2,1),(3,'Tablet','/images/categories/239513_may-tinh-bang.webp',3,1),(4,'Màn hình','/images/categories/342691_man-hinh.webp',4,1),(5,'PC, linh kiện máy tính','/images/categories/909502_542417_pc-dong-bo.webp',5,1),(6,'Đồng hồ','/images/categories/137080_653369_dong-ho.webp',6,1),(7,'Âm thanh','/images/categories/911183_am-thanh.webp',7,1),(8,'Tivi, Điện máy','/images/categories/751017_dien-may.webp',8,1),(9,'Smart home','/images/categories/733281_smart-home.webp',9,1),(10,'Camera','/images/categories/424752_camera-avt.webp',10,1),(11,'Thiết bị mạng','/images/categories/929281_830490_rt-be50-a-1.webp',11,1),(12,'Phụ kiện','/images/categories/649941_phu-kien.webp',12,1),(13,'Hàng cũ','/images/categories/957362_hang-cu.webp',13,1),(14,'Thu cũ đổi mới','/images/categories/615030_thu-cu.webp',14,1),(15,'Dịch vụ SIM thẻ','/images/categories/300419_frame-1000002349.webp',15,1),(16,'Khuyến mãi','/images/categories/556540_logo-tin-hot_638780614366347560.webp',16,1);
/*!40000 ALTER TABLE `danhmuc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `giamgia`
--

LOCK TABLES `giamgia` WRITE;
/*!40000 ALTER TABLE `giamgia` DISABLE KEYS */;
INSERT INTO `giamgia` VALUES (1,'Xiaomi 15 Sale thang 10','FIXED',7550000.00,'2026-10-30 00:00:00','2026-11-30 23:59:59',1),(2,'Xiaomi 15 Ultra Sale thang 10','FIXED',8870000.00,'2026-10-30 00:00:00','2026-11-30 23:59:59',1),(3,'Xiaomi 15T Sale tháng 10','FIXED',2100000.00,'2026-10-30 00:00:00','2026-11-30 23:59:59',1);
/*!40000 ALTER TABLE `giamgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hang`
--

LOCK TABLES `hang` WRITE;
/*!40000 ALTER TABLE `hang` DISABLE KEYS */;
INSERT INTO `hang` VALUES (1,'Apple',1),(2,'Samsung',1),(3,'Xiaomi',1),(4,'OPPO',1),(5,'Vivo',1),(6,'OnePlus',1),(7,'Realme',1),(8,'Google',1),(9,'Sony',1),(10,'ASUS',1);
/*!40000 ALTER TABLE `hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `kho`
--

LOCK TABLES `kho` WRITE;
/*!40000 ALTER TABLE `kho` DISABLE KEYS */;
INSERT INTO `kho` VALUES (1,'Kho Ha Noi','Ha Noi, Viet Nam',1),(2,'Kho Ho Chi Minh','Ho Chi Minh, Viet Nam',1);
/*!40000 ALTER TABLE `kho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `lichsugia`
--

LOCK TABLES `lichsugia` WRITE;
/*!40000 ALTER TABLE `lichsugia` DISABLE KEYS */;
INSERT INTO `lichsugia` VALUES (1,1,37990000.00,'2026-10-03 00:00:00',NULL),(2,2,34990000.00,'2026-10-03 00:00:00',NULL),(3,3,33090000.00,'2026-10-03 00:00:00',NULL),(4,4,12500000.00,'2026-10-03 00:00:00',NULL),(5,5,16400000.00,'2026-10-03 00:00:00',NULL),(6,6,18690000.00,'2026-10-03 00:00:00',NULL),(7,7,25000000.00,'2026-10-03 00:00:00',NULL),(8,8,18990000.00,'2026-10-03 00:00:00',NULL),(9,9,34990000.00,'2026-10-03 00:00:00',NULL),(10,10,13500000.00,'2026-10-03 00:00:00',NULL),(11,11,15000000.00,'2026-10-03 00:00:00',NULL),(12,12,20000000.00,'2026-10-03 00:00:00',NULL),(13,13,9990000.00,'2026-10-03 00:00:00',NULL),(14,14,9000000.00,'2026-10-03 00:00:00',NULL),(15,15,12000000.00,'2026-10-03 00:00:00',NULL),(16,16,16000000.00,'2026-10-03 00:00:00',NULL),(17,17,20000000.00,'2026-10-03 00:00:00',NULL),(18,18,35000000.00,'2026-10-03 00:00:00',NULL),(19,19,15000000.00,'2026-10-03 00:00:00',NULL),(20,20,18000000.00,'2026-10-03 00:00:00',NULL),(22,21,24540000.00,'2026-10-30 20:45:25',NULL),(23,23,24540000.00,'2026-10-30 20:45:25',NULL),(24,25,24540000.00,'2026-10-30 20:45:25',NULL),(25,22,26500000.00,'2026-10-30 20:45:25',NULL),(26,24,26500000.00,'2026-10-30 20:45:25',NULL),(27,26,26500000.00,'2026-10-30 20:45:25',NULL),(28,31,34360000.00,'2026-10-30 21:35:03',NULL),(29,32,34360000.00,'2026-10-30 21:35:03',NULL),(30,33,38250000.00,'2026-10-30 21:35:03',NULL),(31,34,38250000.00,'2026-10-30 21:35:03',NULL),(32,35,13990000.00,'2026-10-30 22:15:58',NULL),(33,36,13990000.00,'2026-10-30 22:15:58',NULL),(34,37,13990000.00,'2026-10-30 22:15:58',NULL),(35,38,14990000.00,'2026-10-30 22:15:58',NULL),(36,39,14990000.00,'2026-10-30 22:15:58',NULL),(37,40,14990000.00,'2026-10-30 22:15:58',NULL),(38,41,64990000.00,'2026-11-04 10:20:26',NULL),(39,42,64990000.00,'2026-11-04 10:20:26',NULL),(40,43,71490000.00,'2026-11-04 10:20:26',NULL),(41,44,71490000.00,'2026-11-04 10:20:26',NULL),(42,45,84490000.00,'2026-11-04 10:20:26',NULL),(43,46,84490000.00,'2026-11-04 10:20:26',NULL),(44,47,103990000.00,'2026-11-04 10:31:54',NULL),(45,48,103990000.00,'2026-11-04 10:31:54',NULL);
/*!40000 ALTER TABLE `lichsugia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `mausac`
--

LOCK TABLES `mausac` WRITE;
/*!40000 ALTER TABLE `mausac` DISABLE KEYS */;
INSERT INTO `mausac` VALUES (9,'Bạc'),(1,'Đen'),(7,'Đỏ'),(8,'Hồng'),(5,'Tím'),(2,'Trắng'),(6,'Vàng'),(4,'Xám'),(3,'Xanh'),(10,'Xanh lá');
/*!40000 ALTER TABLE `mausac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `nhacungcap`
--

LOCK TABLES `nhacungcap` WRITE;
/*!40000 ALTER TABLE `nhacungcap` DISABLE KEYS */;
INSERT INTO `nhacungcap` VALUES (1,'Nha cung cap Apple',1),(2,'Nha cung cap Samsung',1),(3,'Nha cung cap Xiaomi',1),(4,'Nha cung cap OPPO',1),(5,'Nha cung cap Vivo',1),(6,'Nha cung cap OnePlus',1),(7,'Nha cung cap Realme',1),(8,'Nha phan phoi dien thoai ABC',1);
/*!40000 ALTER TABLE `nhacungcap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `nhomthongso`
--

LOCK TABLES `nhomthongso` WRITE;
/*!40000 ALTER TABLE `nhomthongso` DISABLE KEYS */;
INSERT INTO `nhomthongso` VALUES (1,'Màn hình',1),(2,'Hệ điều hành & CPU',2),(3,'Bộ nhớ & Lưu trữ',3),(4,'Camera sau',4),(5,'Camera trước',5),(6,'Pin & Sạc',6),(7,'Kết nối',7),(8,'Thiết kế & Trọng lượng',8),(9,'Tiện ích',9);
/*!40000 ALTER TABLE `nhomthongso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `phienban`
--

LOCK TABLES `phienban` WRITE;
/*!40000 ALTER TABLE `phienban` DISABLE KEYS */;
INSERT INTO `phienban` VALUES (1,'IPH17PM-12-256-BLK-VN',1,6,1,1,0),(2,'IPH17P-12-256-WHT-VN',2,6,2,1,0),(3,'S26U-12-512-BLK-VN',3,7,1,1,0),(4,'A57-8-256-BLU-VN',4,5,3,1,0),(5,'XMI17-12-256-BLK-VN',5,6,1,1,0),(6,'XMI17T-12-512-GRY-VN',6,7,4,1,0),(7,'R16P-12-256-BLK-VN',7,6,1,1,0),(8,'R16-8-256-PNK-VN',8,5,8,1,0),(9,'X300P-16-512-BLK-VN',9,8,1,1,0),(10,'V70FE-8-256-BLU-VN',10,5,3,1,0),(11,'OP12-12-256-GRN-VN',11,6,10,1,0),(12,'OP11-16-256-BLK-VN',12,6,1,1,0),(13,'RM16T-8-256-BLU-VN',13,5,3,1,0),(14,'RM16-8-256-WHT-VN',14,5,2,1,0),(15,'GP8-8-128-BLK-VN',15,4,1,1,0),(16,'GP8P-12-256-GRY-VN',16,6,4,1,0),(17,'SX1-12-256-BLK-VN',17,6,1,1,0),(18,'SX1V-12-256-GRN-VN',18,6,10,1,0),(19,'ZF9-8-256-BLU-VN',19,5,3,1,0),(20,'ROG-16-512-BLK-VN',20,8,1,1,0),(21,'XM15-12-256-BLK-VN',21,6,1,1,1),(22,'XM15-12-512-BLK-VN',21,7,1,1,0),(23,'XM15-12-256-WHT-VN',21,6,2,1,0),(24,'XM15-12-512-WHT-VN',21,7,2,1,0),(25,'XM15-12-256-GRN-VN',21,6,10,1,0),(26,'XM15-12-512-GRN-VN',21,7,10,1,0),(31,'XMI15U-16-512-BLK-VN',22,8,1,1,1),(32,'XMI15U-16-512-SLV-VN',22,8,9,1,0),(33,'XMI15U-16-1024-BLK-VN',22,9,1,1,0),(34,'XMI15U-16-1024-SLV-VN',22,9,9,1,0),(35,'X15T-12-256-BLK-VN',23,6,1,1,1),(36,'X15T-12-256-GRY-VN',23,6,4,1,0),(37,'X15T-12-256-YLW-VN',23,6,6,1,0),(38,'X15T-12-512-BLK-VN',23,7,1,1,0),(39,'X15T-12-512-GRY-VN',23,7,4,1,0),(40,'X15T-12-512-YLW-VN',23,7,6,1,0),(41,'IPDUO-256-WHT-VN',24,6,2,1,1),(42,'IPDUO-256-BLK-VN',24,6,1,1,0),(43,'IPDUO-512-WHT-VN',24,7,2,1,0),(44,'IPDUO-512-BLK-VN',24,7,1,1,0),(45,'IPDUO-1024-WHT-VN',24,9,2,1,0),(46,'IPDUO-1024-BLK-VN',24,9,1,1,0),(47,'IPDUO-2048-WHT-VN',24,11,2,1,0),(48,'IPDUO-2048-BLK-VN',24,11,1,1,0);
/*!40000 ALTER TABLE `phienban` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `phieunhap`
--

LOCK TABLES `phieunhap` WRITE;
/*!40000 ALTER TABLE `phieunhap` DISABLE KEYS */;
INSERT INTO `phieunhap` VALUES (1,1,1,'2026-09-20 08:00:00','2026-09-20 10:00:00',2),(2,2,1,'2026-09-21 08:00:00','2026-09-21 10:30:00',2),(3,3,1,'2026-09-22 08:00:00','2026-09-22 11:00:00',2),(4,4,2,'2026-09-23 08:00:00','2026-09-23 09:30:00',2),(5,5,2,'2026-09-24 08:00:00','2026-09-24 10:00:00',2),(6,6,2,'2026-09-25 08:00:00','2026-09-25 11:00:00',2),(7,7,1,'2026-09-26 08:00:00','2026-09-26 09:00:00',2),(8,8,2,'2026-09-27 08:00:00','2026-09-27 10:30:00',2);
/*!40000 ALTER TABLE `phieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ram`
--

LOCK TABLES `ram` WRITE;
/*!40000 ALTER TABLE `ram` DISABLE KEYS */;
INSERT INTO `ram` VALUES (1,4),(2,6),(3,8),(4,12),(5,16),(6,24);
/*!40000 ALTER TABLE `ram` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rom`
--

LOCK TABLES `rom` WRITE;
/*!40000 ALTER TABLE `rom` DISABLE KEYS */;
INSERT INTO `rom` VALUES (1,64),(2,128),(3,256),(4,512),(5,1024),(6,2048);
/*!40000 ALTER TABLE `rom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES (1,'iPhone 17 Pro Max','IPH17PM',1,1,'iPhone 17 Pro Max - smartphone cao cap cua Apple',1),(2,'iPhone 17 Pro','IPH17P',1,1,'iPhone 17 Pro - smartphone cao cap cua Apple',1),(3,'Samsung Galaxy S26 Ultra','SM-S948',1,2,'Samsung Galaxy S26 Ultra - smartphone cao cap Samsung',1),(4,'Samsung Galaxy A57 5G','SM-A576',1,2,'Samsung Galaxy A57 5G - smartphone tam trung Samsung',1),(5,'Xiaomi 17 5G','XMI17',1,3,'Xiaomi 17 5G - smartphone cao cap Xiaomi',1),(6,'Xiaomi 17T 5G','XMI17T',1,3,'Xiaomi 17T 5G - smartphone Xiaomi',1),(7,'OPPO Reno16 Pro 5G','OP-R16P',1,4,'OPPO Reno16 Pro 5G - smartphone OPPO',1),(8,'OPPO Reno16 5G','OP-R16',1,4,'OPPO Reno16 5G - smartphone OPPO',1),(9,'vivo X300 Pro 5G','VIVO-X300P',1,5,'vivo X300 Pro 5G - smartphone cao cap vivo',1),(10,'vivo V70 FE 5G','VIVO-V70FE',1,5,'vivo V70 FE 5G - smartphone vivo',1),(11,'OnePlus 12','OP12',1,6,'OnePlus 12 - smartphone cao cap OnePlus',1),(12,'OnePlus 11','OP11',1,6,'OnePlus 11 - smartphone cao cap OnePlus',1),(13,'realme 16T 5G','RM16T',1,7,'realme 16T 5G - smartphone realme',1),(14,'realme 16 5G','RM16',1,7,'realme 16 5G - smartphone realme',1),(15,'Google Pixel 8','GP-P8',1,8,'Google Pixel 8 - smartphone Google',1),(16,'Google Pixel 8 Pro','GP-P8P',1,8,'Google Pixel 8 Pro - smartphone Google',1),(17,'Sony Xperia 1','SONY-X1',1,9,'Sony Xperia 1 - smartphone Sony',1),(18,'Sony Xperia 1 V','SONY-X1V',1,9,'Sony Xperia 1 V - smartphone Sony',1),(19,'ASUS Zenfone 9','AS-ZF9',1,10,'ASUS Zenfone 9 - smartphone ASUS',1),(20,'ASUS ROG Phone','AS-ROG',1,10,'ASUS ROG Phone - smartphone gaming ASUS',1),(21,'Xiaomi 15','XM15',1,3,'Xiaomi 15 - smartphone cao cấp Xiaomi',1),(22,'Xiaomi 15 Ultra','XMI15U',1,3,'Xiaomi 15 Ultra - smartphone cao cap Xiaomi',1),(23,'Xiaomi 15T','XMI15T',1,3,'Xiaomi 15T - smartphone Xiaomi',1),(24,'iPhone Duo','IPD',1,1,'iPhone Duo - smartphone gap moi nhat',1);
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `thitruong`
--

LOCK TABLES `thitruong` WRITE;
/*!40000 ALTER TABLE `thitruong` DISABLE KEYS */;
INSERT INTO `thitruong` VALUES (1,'VN','Việt Nam'),(2,'QT','Quốc tế');
/*!40000 ALTER TABLE `thitruong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `thongsokythuat`
--

LOCK TABLES `thongsokythuat` WRITE;
/*!40000 ALTER TABLE `thongsokythuat` DISABLE KEYS */;
INSERT INTO `thongsokythuat` VALUES (1,3,'RAM','GB',1),(2,3,'Bộ nhớ trong','GB',2),(3,3,'Thẻ nhớ ngoài',NULL,3),(4,3,'Hỗ trợ thẻ tối đa','TB',4),(5,1,'Công nghệ màn hình',NULL,1),(6,1,'Kích thước màn hình','inch',2),(7,1,'Độ phân giải',NULL,3),(8,1,'Tần số quét','Hz',4),(9,2,'Chip xử lý (CPU)',NULL,1),(10,2,'Tốc độ CPU',NULL,2),(11,2,'Hệ điều hành',NULL,3),(12,4,'Độ phân giải','MP',1),(13,4,'Quay phim',NULL,2),(14,4,'Tính năng',NULL,3),(15,5,'Độ phân giải','MP',1),(16,5,'Quay phim',NULL,2),(17,5,'Tính năng',NULL,3),(18,6,'Dung lượng pin','mAh',1),(19,6,'Công nghệ pin',NULL,2),(20,6,'Hỗ trợ sạc tối đa','W',3),(21,7,'Mạng di động',NULL,1),(22,7,'Loại SIM',NULL,2),(23,7,'Wi-Fi',NULL,3),(24,7,'Bluetooth',NULL,4),(25,7,'GPS',NULL,5),(26,7,'Cổng kết nối/sạc',NULL,6),(27,7,'Jack tai nghe',NULL,7),(28,8,'Kích thước',NULL,1),(29,8,'Trọng lượng','g',2),(30,8,'Chất liệu',NULL,3),(31,9,'Tính năng đặc biệt',NULL,1),(32,1,'Độ sáng màn hình','nits',5),(33,2,'Vi xử lý đồ họa (GPU)',NULL,4),(34,4,'Đèn Flash',NULL,4),(35,7,'Số khe SIM',NULL,8),(36,7,'Cảm biến',NULL,9);
/*!40000 ALTER TABLE `thongsokythuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tonkho`
--

LOCK TABLES `tonkho` WRITE;
/*!40000 ALTER TABLE `tonkho` DISABLE KEYS */;
INSERT INTO `tonkho` VALUES (1,1,1,15),(2,1,2,12),(3,1,3,10),(4,1,4,20),(5,1,5,15),(6,1,6,10),(7,1,7,8),(8,1,8,12),(9,1,9,7),(10,1,10,15),(11,1,11,8),(12,1,12,8),(13,1,13,15),(14,1,14,20),(15,1,15,10),(16,1,16,6),(17,1,17,5),(18,1,18,4),(19,1,19,8),(20,1,20,10),(21,2,1,12),(22,2,2,10),(23,2,3,8),(24,2,4,15),(25,2,5,12),(26,2,6,8),(27,2,7,6),(28,2,8,10),(29,2,9,5),(30,2,10,12),(31,2,11,6),(32,2,12,6),(33,2,13,12),(34,2,14,15),(35,2,15,8),(36,2,16,5),(37,2,17,4),(38,2,18,3),(39,2,19,6),(40,2,20,8),(41,1,21,12),(42,2,21,8),(43,1,22,10),(44,2,22,6),(45,1,23,8),(46,2,23,5),(47,1,24,10),(48,2,24,6),(49,1,25,7),(50,2,25,4),(51,1,26,8),(52,2,26,5),(53,1,31,6),(54,2,31,4),(55,1,32,5),(56,2,32,3),(57,1,33,7),(58,2,33,4),(59,1,34,4),(60,2,34,3),(61,1,35,10),(62,2,35,6),(63,1,36,8),(64,2,36,5),(65,1,37,9),(66,2,37,5),(67,1,47,6),(68,2,47,5),(69,1,48,8),(70,2,48,7),(71,1,45,6),(72,2,45,5),(73,1,46,8),(74,2,46,7),(79,1,41,6),(80,2,41,5),(81,1,42,6),(82,2,42,5),(83,1,43,5),(84,2,43,5),(85,1,44,5),(86,2,44,5);
/*!40000 ALTER TABLE `tonkho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `trangthaidonhang`
--

LOCK TABLES `trangthaidonhang` WRITE;
/*!40000 ALTER TABLE `trangthaidonhang` DISABLE KEYS */;
INSERT INTO `trangthaidonhang` VALUES (1,'Chờ xác nhận'),(5,'Đã giao'),(6,'Đã hủy'),(2,'Đã xác nhận'),(3,'Đang chuẩn bị'),(4,'Đang giao');
/*!40000 ALTER TABLE `trangthaidonhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `trangthainhacungcap`
--

LOCK TABLES `trangthainhacungcap` WRITE;
/*!40000 ALTER TABLE `trangthainhacungcap` DISABLE KEYS */;
INSERT INTO `trangthainhacungcap` VALUES (1,'Đang hoạt động'),(3,'Ngừng hợp tác'),(2,'Tạm ngưng');
/*!40000 ALTER TABLE `trangthainhacungcap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `trangthaiphieunhap`
--

LOCK TABLES `trangthaiphieunhap` WRITE;
/*!40000 ALTER TABLE `trangthaiphieunhap` DISABLE KEYS */;
INSERT INTO `trangthaiphieunhap` VALUES (1,'Chờ nhập'),(3,'Đã hủy'),(2,'Đã nhập');
/*!40000 ALTER TABLE `trangthaiphieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vaitro`
--

LOCK TABLES `vaitro` WRITE;
/*!40000 ALTER TABLE `vaitro` DISABLE KEYS */;
INSERT INTO `vaitro` VALUES (1,'CUSTOMER','Khách hàng mua sản phẩm'),(2,'STAFF','Nhân viên quản lý và xử lý đơn hàng'),(3,'ADMIN','Quản trị viên toàn bộ hệ thống');
/*!40000 ALTER TABLE `vaitro` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
