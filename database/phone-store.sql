-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: phone_store
-- ------------------------------------------------------
-- Server version	8.0.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anh`
--

LOCK TABLES `anh` WRITE;
/*!40000 ALTER TABLE `anh` DISABLE KEYS */;
INSERT INTO `anh` VALUES (1,21,'/images/products/xiaomi/xiaomi-series/xiaomi-15/dien-thoai-xiaomi-15_24_.webp',1),(2,22,'/images/products/xiaomi/xiaomi-series/xiaomi-15/dien-thoai-xiaomi-15_24_.webp',1),(3,23,'/images/products/xiaomi/xiaomi-series/xiaomi-15/dien-thoai-xiaomi-15_11_.webp',1),(4,24,'/images/products/xiaomi/xiaomi-series/xiaomi-15/dien-thoai-xiaomi-15_11_.webp',1),(5,25,'/images/products/xiaomi/xiaomi-series/xiaomi-15/dien-thoai-xiaomi-15_19_.webp',1),(6,26,'/images/products/xiaomi/xiaomi-series/xiaomi-15/dien-thoai-xiaomi-15_19_.webp',1),(7,31,'/images/products/xiaomi/xiaomi-series/xiaomi-15-ultra/dien-thoai-xiaomi-15-ultra_12_.webp',1),(8,32,'/images/products/xiaomi/xiaomi-series/xiaomi-15-ultra/dien-thoai-xiaomi-15-ultra.webp',1),(9,33,'/images/products/xiaomi/xiaomi-series/xiaomi-15-ultra/dien-thoai-xiaomi-15-ultra_12_.webp',1),(10,34,'/images/products/xiaomi/xiaomi-series/xiaomi-15-ultra/dien-thoai-xiaomi-15-ultra.webp',1),(11,35,'/images/products/xiaomi/xiaomi-series/xiaomi-15t/xiaomi-15t-5g-11_2_2.webp',1),(12,38,'/images/products/xiaomi/xiaomi-series/xiaomi-15t/xiaomi-15t-5g-11_2_2.webp',1),(13,36,'/images/products/xiaomi/xiaomi-series/xiaomi-15t/cps-02-12_1_.webp',1),(14,39,'/images/products/xiaomi/xiaomi-series/xiaomi-15t/cps-02-12_1_.webp',1),(15,37,'/images/products/xiaomi/xiaomi-series/xiaomi-15t/xiaomi-15t-5g-22_2_2.webp',1),(16,40,'/images/products/xiaomi/xiaomi-series/xiaomi-15t/xiaomi-15t-5g-22_2_2.webp',1);
/*!40000 ALTER TABLE `anh` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cauhinhdungluong`
--

LOCK TABLES `cauhinhdungluong` WRITE;
/*!40000 ALTER TABLE `cauhinhdungluong` DISABLE KEYS */;
INSERT INTO `cauhinhdungluong` VALUES (1,1,1),(2,1,2),(3,2,2),(4,3,2),(5,3,3),(6,4,3),(7,4,4),(8,5,4),(9,5,5),(10,6,5);
/*!40000 ALTER TABLE `cauhinhdungluong` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `chitietdonhang`
--

LOCK TABLES `chitietdonhang` WRITE;
/*!40000 ALTER TABLE `chitietdonhang` DISABLE KEYS */;
/*!40000 ALTER TABLE `chitietdonhang` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `chitietgiamgia`
--

LOCK TABLES `chitietgiamgia` WRITE;
/*!40000 ALTER TABLE `chitietgiamgia` DISABLE KEYS */;
INSERT INTO `chitietgiamgia` VALUES (1,1,21,NULL),(2,1,23,NULL),(3,1,25,NULL),(4,1,22,7510000.00),(5,1,24,7510000.00),(6,1,26,7510000.00),(7,2,31,8870000.00),(8,2,32,8870000.00),(9,2,33,10260000.00),(10,2,34,10260000.00),(11,3,35,NULL),(12,3,36,NULL),(13,3,37,NULL),(14,3,38,2400000.00),(15,3,39,2400000.00),(16,3,40,2400000.00);
/*!40000 ALTER TABLE `chitietgiamgia` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `chitietgiohang`
--

LOCK TABLES `chitietgiohang` WRITE;
/*!40000 ALTER TABLE `chitietgiohang` DISABLE KEYS */;
/*!40000 ALTER TABLE `chitietgiohang` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `chitietphieunhap`
--

LOCK TABLES `chitietphieunhap` WRITE;
/*!40000 ALTER TABLE `chitietphieunhap` DISABLE KEYS */;
INSERT INTO `chitietphieunhap` VALUES (1,1,1,10,32500000.00),(2,1,2,10,30000000.00),(3,2,3,8,28500000.00),(4,2,4,15,10500000.00),(5,3,5,10,14000000.00),(6,3,6,10,15500000.00),(7,4,7,8,21000000.00),(8,4,8,12,15500000.00),(9,5,9,6,27000000.00),(10,5,10,12,11200000.00),(11,6,11,8,12500000.00),(12,6,12,8,16500000.00),(13,7,13,15,8000000.00),(14,7,14,15,7200000.00),(15,8,15,10,9500000.00),(16,8,16,6,13000000.00),(17,8,17,5,16000000.00),(18,8,18,4,28500000.00),(19,8,19,8,12000000.00),(20,8,20,8,14500000.00);
/*!40000 ALTER TABLE `chitietphieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danhmuc`
--

DROP TABLE IF EXISTS `danhmuc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danhmuc` (
  `MaDanhMuc` int NOT NULL AUTO_INCREMENT,
  `TenDanhMuc` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaDanhMucCha` int DEFAULT NULL,
  `DuongDanAnh` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ThuTuHienThi` int NOT NULL DEFAULT '0',
  `TrangThai` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`MaDanhMuc`),
  UNIQUE KEY `TenDanhMuc` (`TenDanhMuc`),
  KEY `FK_DanhMuc_DanhMucCha` (`MaDanhMucCha`),
  CONSTRAINT `FK_DanhMuc_DanhMucCha` FOREIGN KEY (`MaDanhMucCha`) REFERENCES `danhmuc` (`MaDanhMuc`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danhmuc`
--

LOCK TABLES `danhmuc` WRITE;
/*!40000 ALTER TABLE `danhmuc` DISABLE KEYS */;
INSERT INTO `danhmuc` VALUES (1,'Điện thoại',NULL,NULL,1,1),(2,'Laptop',NULL,NULL,2,1),(3,'Phụ kiện',NULL,NULL,3,1),(4,'Smartwatch',NULL,NULL,4,1),(5,'Đồng hồ',NULL,NULL,5,1),(6,'Tablet',NULL,NULL,6,1),(7,'Máy cũ, Thu cũ',NULL,NULL,7,1),(8,'Màn hình, Máy in',NULL,NULL,8,1),(9,'Sim, Thẻ cào',NULL,NULL,9,1),(10,'Phụ kiện di động',3,NULL,1,1),(11,'Phụ kiện laptop, PC',3,NULL,2,1),(12,'Thiết bị nghe nhìn, lưu trữ, thu âm',3,NULL,3,1),(13,'Camera',3,NULL,4,1),(18,'Sạc dự phòng',10,'/images-phu-kien/phu-kien-di-dong/sac-du-phong.png',1,1),(19,'Sạc, cáp',10,'/images-phu-kien/phu-kien-di-dong/sac-cap.png',2,1),(20,'Ốp lưng điện thoại',10,'/images-phu-kien/phu-kien-di-dong/op-lung-dt.png',3,1),(21,'Ốp lưng máy tính bảng',10,'/images-phu-kien/phu-kien-di-dong/op-lung-mt-bang.png',4,1),(22,'Miếng dán',10,'/images-phu-kien/phu-kien-di-dong/mieng-dan.png',5,1),(23,'Miếng dán Camera',10,'/images-phu-kien/phu-kien-di-dong/mieng-dan-camera.png',6,1),(24,'Túi đựng AirPods',10,'/images-phu-kien/phu-kien-di-dong/tui-dung-airpods.png',7,1),(25,'Quạt mini',10,'/images-phu-kien/phu-kien-di-dong/quat-mini.png',8,1),(26,'Bút tablet',10,'/images-phu-kien/phu-kien-di-dong/but-tablet.png',9,1),(27,'Giá đỡ điện thoại, laptop',10,'/images-phu-kien/phu-kien-di-dong/gia-do-laptop-dienthoai.png',10,1),(28,'Dây đeo điện thoại',10,'/images-phu-kien/phu-kien-di-dong/day-deo-dt.png',11,1),(29,'Ống kính điện thoại',10,'/images-phu-kien/phu-kien-di-dong/ong-kinh-dt.png',12,1),(30,'Hub, cáp chuyển đổi',11,'/images-phu-kien/phu-kien-laptop-pc/hub-cap-chuyen-doi.png',1,1),(31,'Chuột máy tính',11,'/images-phu-kien/phu-kien-laptop-pc/chuot-may-tinh.png',2,1),(32,'Bàn phím',11,'/images-phu-kien/phu-kien-laptop-pc/ban-phim.png',3,1),(33,'Router - Thiết bị mạng',11,'/images-phu-kien/phu-kien-laptop-pc/router.png',4,1),(34,'Balo, túi chống sốc',11,'/images-phu-kien/phu-kien-laptop-pc/balo-tui-chong-soc.png',5,1),(35,'Túi đựng phụ kiện',11,'/images-phu-kien/phu-kien-laptop-pc/tui-dung-phu-kien.png',6,1),(36,'Phần mềm',11,'/images-phu-kien/phu-kien-laptop-pc/phan-mem.png',8,1),(37,'Giá treo màn hình',11,'/images-phu-kien/phu-kien-laptop-pc/gia-treo-man-hinh.png',9,1),(38,'Miếng lót chuột',11,'/images-phu-kien/phu-kien-laptop-pc/mieng-lot-chuot.png',10,1),(39,'Bảng vẽ điện tử',11,'/images-phu-kien/phu-kien-laptop-pc/bang-ve-dientu.png',11,1),(40,'Tai nghe Bluetooth',12,'/images-phu-kien/phu-kien-nghe-nhin-luutru-thuam/tai-nghe-bluetooth.png',1,1),(41,'Tai nghe dây',12,'/images-phu-kien/phu-kien-nghe-nhin-luutru-thuam/tai-nghe-day.png',2,1),(42,'Tai nghe chụp tai',12,'/images-phu-kien/phu-kien-nghe-nhin-luutru-thuam/tai-nghe-chup-tai.png',3,1),(43,'Tai nghe thể thao',12,'/images-phu-kien/phu-kien-nghe-nhin-luutru-thuam/tai-nghe-the-thao.png',4,1),(44,'Loa',12,'/images-phu-kien/phu-kien-nghe-nhin-luutru-thuam/loa.png',5,1),(45,'Micro',12,'/images-phu-kien/phu-kien-nghe-nhin-luutru-thuam/micro.png',6,1),(46,'Máy chiếu',12,'/images-phu-kien/phu-kien-nghe-nhin-luutru-thuam/may-chieu.png',7,1),(47,'Kính thông minh',12,'/images-phu-kien/phu-kien-nghe-nhin-luutru-thuam/kinh-thong-minh.png',8,1),(48,'Ổ cứng',12,'/images-phu-kien/phu-kien-nghe-nhin-luutru-thuam/o-cung.png',9,1),(49,'Thẻ nhớ',12,'/images-phu-kien/phu-kien-nghe-nhin-luutru-thuam/the-nho.png',10,1),(50,'USB',12,'/images-phu-kien/phu-kien-nghe-nhin-luutru-thuam/usb.png',11,1),(51,'Webcam',13,'/images-phu-kien/camera/web-cam.png',12,1),(52,'Camera Giám Sát',13,'/images-phu-kien/camera/camera-giam-sat.png',1,1),(53,'Camera trong nhà',13,'/images-phu-kien/camera/camera-trong-nha.png',2,1),(54,'Camera ngoài trời',13,'/images-phu-kien/camera/camera-ngoai-troi.png',3,1),(55,'Camera Năng Lượng Mặt Trời',13,'/images-phu-kien/camera/camera-nang-luong-mat-troi.png',4,1),(56,'Camera 4G',13,'/images-phu-kien/camera/camera-4g.png',5,1),(57,'Chuông cửa Camera',13,'/images-phu-kien/camera/chuong-cua-camera.png',6,1),(59,'Phủ phím laptop',11,'/images-phu-kien/phu-kien-laptop-pc/phu-phim-laptop.png',7,1);
/*!40000 ALTER TABLE `danhmuc` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `diachi`
--

LOCK TABLES `diachi` WRITE;
/*!40000 ALTER TABLE `diachi` DISABLE KEYS */;
/*!40000 ALTER TABLE `diachi` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `donhang`
--

LOCK TABLES `donhang` WRITE;
/*!40000 ALTER TABLE `donhang` DISABLE KEYS */;
/*!40000 ALTER TABLE `donhang` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `giamgia`
--

LOCK TABLES `giamgia` WRITE;
/*!40000 ALTER TABLE `giamgia` DISABLE KEYS */;
INSERT INTO `giamgia` VALUES (1,'Xiaomi 15 Sale thang 10','FIXED',7550000.00,'2026-10-30 00:00:00','2026-11-30 23:59:59',1),(2,'Xiaomi 15 Ultra Sale thang 10','FIXED',8870000.00,'2026-10-30 00:00:00','2026-11-30 23:59:59',1),(3,'Xiaomi 15T Sale tháng 10','FIXED',2100000.00,'2026-10-30 00:00:00','2026-11-30 23:59:59',1);
/*!40000 ALTER TABLE `giamgia` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `giohang`
--

LOCK TABLES `giohang` WRITE;
/*!40000 ALTER TABLE `giohang` DISABLE KEYS */;
/*!40000 ALTER TABLE `giohang` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `hang`
--

LOCK TABLES `hang` WRITE;
/*!40000 ALTER TABLE `hang` DISABLE KEYS */;
INSERT INTO `hang` VALUES (1,'Apple',1),(2,'Samsung',1),(3,'Xiaomi',1),(4,'OPPO',1),(5,'Vivo',1),(6,'OnePlus',1),(7,'Realme',1),(8,'Google',1),(9,'Sony',1),(10,'ASUS',1);
/*!40000 ALTER TABLE `hang` ENABLE KEYS */;
UNLOCK TABLES;

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
  `SoDienThoai` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MatKhau` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `NgaySinh` date DEFAULT NULL,
  `GioiTinh` varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TrangThai` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`MaKH`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `SoDienThoai` (`SoDienThoai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `kho`
--

LOCK TABLES `kho` WRITE;
/*!40000 ALTER TABLE `kho` DISABLE KEYS */;
INSERT INTO `kho` VALUES (1,'Kho Ha Noi','Ha Noi, Viet Nam',1),(2,'Kho Ho Chi Minh','Ho Chi Minh, Viet Nam',1);
/*!40000 ALTER TABLE `kho` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lichsugia`
--

LOCK TABLES `lichsugia` WRITE;
/*!40000 ALTER TABLE `lichsugia` DISABLE KEYS */;
INSERT INTO `lichsugia` VALUES (1,1,37990000.00,'2026-10-03 00:00:00',NULL),(2,2,34990000.00,'2026-10-03 00:00:00',NULL),(3,3,33090000.00,'2026-10-03 00:00:00',NULL),(4,4,12500000.00,'2026-10-03 00:00:00',NULL),(5,5,16400000.00,'2026-10-03 00:00:00',NULL),(6,6,18690000.00,'2026-10-03 00:00:00',NULL),(7,7,25000000.00,'2026-10-03 00:00:00',NULL),(8,8,18990000.00,'2026-10-03 00:00:00',NULL),(9,9,34990000.00,'2026-10-03 00:00:00',NULL),(10,10,13500000.00,'2026-10-03 00:00:00',NULL),(11,11,15000000.00,'2026-10-03 00:00:00',NULL),(12,12,20000000.00,'2026-10-03 00:00:00',NULL),(13,13,9990000.00,'2026-10-03 00:00:00',NULL),(14,14,9000000.00,'2026-10-03 00:00:00',NULL),(15,15,12000000.00,'2026-10-03 00:00:00',NULL),(16,16,16000000.00,'2026-10-03 00:00:00',NULL),(17,17,20000000.00,'2026-10-03 00:00:00',NULL),(18,18,35000000.00,'2026-10-03 00:00:00',NULL),(19,19,15000000.00,'2026-10-03 00:00:00',NULL),(20,20,18000000.00,'2026-10-03 00:00:00',NULL),(22,21,24540000.00,'2026-10-30 20:45:25',NULL),(23,23,24540000.00,'2026-10-30 20:45:25',NULL),(24,25,24540000.00,'2026-10-30 20:45:25',NULL),(25,22,26500000.00,'2026-10-30 20:45:25',NULL),(26,24,26500000.00,'2026-10-30 20:45:25',NULL),(27,26,26500000.00,'2026-10-30 20:45:25',NULL),(28,31,34360000.00,'2026-10-30 21:35:03',NULL),(29,32,34360000.00,'2026-10-30 21:35:03',NULL),(30,33,38250000.00,'2026-10-30 21:35:03',NULL),(31,34,38250000.00,'2026-10-30 21:35:03',NULL),(32,35,13990000.00,'2026-10-30 22:15:58',NULL),(33,36,13990000.00,'2026-10-30 22:15:58',NULL),(34,37,13990000.00,'2026-10-30 22:15:58',NULL),(35,38,14990000.00,'2026-10-30 22:15:58',NULL),(36,39,14990000.00,'2026-10-30 22:15:58',NULL),(37,40,14990000.00,'2026-10-30 22:15:58',NULL);
/*!40000 ALTER TABLE `lichsugia` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `lichsutrangthainhacungcap`
--

LOCK TABLES `lichsutrangthainhacungcap` WRITE;
/*!40000 ALTER TABLE `lichsutrangthainhacungcap` DISABLE KEYS */;
/*!40000 ALTER TABLE `lichsutrangthainhacungcap` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `mausac`
--

LOCK TABLES `mausac` WRITE;
/*!40000 ALTER TABLE `mausac` DISABLE KEYS */;
INSERT INTO `mausac` VALUES (9,'Bạc'),(1,'Đen'),(7,'Đỏ'),(8,'Hồng'),(5,'Tím'),(2,'Trắng'),(6,'Vàng'),(4,'Xám'),(3,'Xanh'),(10,'Xanh lá');
/*!40000 ALTER TABLE `mausac` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `nhacungcap`
--

LOCK TABLES `nhacungcap` WRITE;
/*!40000 ALTER TABLE `nhacungcap` DISABLE KEYS */;
INSERT INTO `nhacungcap` VALUES (1,'Nha cung cap Apple',1),(2,'Nha cung cap Samsung',1),(3,'Nha cung cap Xiaomi',1),(4,'Nha cung cap OPPO',1),(5,'Nha cung cap Vivo',1),(6,'Nha cung cap OnePlus',1),(7,'Nha cung cap Realme',1),(8,'Nha phan phoi dien thoai ABC',1);
/*!40000 ALTER TABLE `nhacungcap` ENABLE KEYS */;
UNLOCK TABLES;

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
  PRIMARY KEY (`MaNhanVien`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phienban`
--

LOCK TABLES `phienban` WRITE;
/*!40000 ALTER TABLE `phienban` DISABLE KEYS */;
INSERT INTO `phienban` VALUES (1,'IPH17PM-12-256-BLK-VN',1,6,1,1),(2,'IPH17P-12-256-WHT-VN',2,6,2,1),(3,'S26U-12-512-BLK-VN',3,7,1,1),(4,'A57-8-256-BLU-VN',4,5,3,1),(5,'XMI17-12-256-BLK-VN',5,6,1,1),(6,'XMI17T-12-512-GRY-VN',6,7,4,1),(7,'R16P-12-256-BLK-VN',7,6,1,1),(8,'R16-8-256-PNK-VN',8,5,8,1),(9,'X300P-16-512-BLK-VN',9,8,1,1),(10,'V70FE-8-256-BLU-VN',10,5,3,1),(11,'OP12-12-256-GRN-VN',11,6,10,1),(12,'OP11-16-256-BLK-VN',12,6,1,1),(13,'RM16T-8-256-BLU-VN',13,5,3,1),(14,'RM16-8-256-WHT-VN',14,5,2,1),(15,'GP8-8-128-BLK-VN',15,4,1,1),(16,'GP8P-12-256-GRY-VN',16,6,4,1),(17,'SX1-12-256-BLK-VN',17,6,1,1),(18,'SX1V-12-256-GRN-VN',18,6,10,1),(19,'ZF9-8-256-BLU-VN',19,5,3,1),(20,'ROG-16-512-BLK-VN',20,8,1,1),(21,'XM15-12-256-BLK-VN',21,6,1,1),(22,'XM15-12-512-BLK-VN',21,7,1,1),(23,'XM15-12-256-WHT-VN',21,6,2,1),(24,'XM15-12-512-WHT-VN',21,7,2,1),(25,'XM15-12-256-GRN-VN',21,6,10,1),(26,'XM15-12-512-GRN-VN',21,7,10,1),(31,'XMI15U-16-512-BLK-VN',22,8,1,1),(32,'XMI15U-16-512-SLV-VN',22,8,9,1),(33,'XMI15U-16-1024-BLK-VN',22,9,1,1),(34,'XMI15U-16-1024-SLV-VN',22,9,9,1),(35,'X15T-12-256-BLK-VN',23,6,1,1),(36,'X15T-12-256-GRY-VN',23,6,4,1),(37,'X15T-12-256-YLW-VN',23,6,6,1),(38,'X15T-12-512-BLK-VN',23,7,1,1),(39,'X15T-12-512-GRY-VN',23,7,4,1),(40,'X15T-12-512-YLW-VN',23,7,6,1);
/*!40000 ALTER TABLE `phienban` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `phieunhap`
--

LOCK TABLES `phieunhap` WRITE;
/*!40000 ALTER TABLE `phieunhap` DISABLE KEYS */;
INSERT INTO `phieunhap` VALUES (1,1,1,'2026-09-20 08:00:00','2026-09-20 10:00:00',2),(2,2,1,'2026-09-21 08:00:00','2026-09-21 10:30:00',2),(3,3,1,'2026-09-22 08:00:00','2026-09-22 11:00:00',2),(4,4,2,'2026-09-23 08:00:00','2026-09-23 09:30:00',2),(5,5,2,'2026-09-24 08:00:00','2026-09-24 10:00:00',2),(6,6,2,'2026-09-25 08:00:00','2026-09-25 11:00:00',2),(7,7,1,'2026-09-26 08:00:00','2026-09-26 09:00:00',2),(8,8,2,'2026-09-27 08:00:00','2026-09-27 10:30:00',2);
/*!40000 ALTER TABLE `phieunhap` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ram`
--

LOCK TABLES `ram` WRITE;
/*!40000 ALTER TABLE `ram` DISABLE KEYS */;
INSERT INTO `ram` VALUES (1,4),(2,6),(3,8),(4,12),(5,16),(6,24);
/*!40000 ALTER TABLE `ram` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rom`
--

LOCK TABLES `rom` WRITE;
/*!40000 ALTER TABLE `rom` DISABLE KEYS */;
INSERT INTO `rom` VALUES (1,64),(2,128),(3,256),(4,512),(5,1024);
/*!40000 ALTER TABLE `rom` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES (1,'iPhone 17 Pro Max','IPH17PM',1,1,'iPhone 17 Pro Max - smartphone cao cap cua Apple',1),(2,'iPhone 17 Pro','IPH17P',1,1,'iPhone 17 Pro - smartphone cao cap cua Apple',1),(3,'Samsung Galaxy S26 Ultra','SM-S948',1,2,'Samsung Galaxy S26 Ultra - smartphone cao cap Samsung',1),(4,'Samsung Galaxy A57 5G','SM-A576',1,2,'Samsung Galaxy A57 5G - smartphone tam trung Samsung',1),(5,'Xiaomi 17 5G','XMI17',1,3,'Xiaomi 17 5G - smartphone cao cap Xiaomi',1),(6,'Xiaomi 17T 5G','XMI17T',1,3,'Xiaomi 17T 5G - smartphone Xiaomi',1),(7,'OPPO Reno16 Pro 5G','OP-R16P',1,4,'OPPO Reno16 Pro 5G - smartphone OPPO',1),(8,'OPPO Reno16 5G','OP-R16',1,4,'OPPO Reno16 5G - smartphone OPPO',1),(9,'vivo X300 Pro 5G','VIVO-X300P',1,5,'vivo X300 Pro 5G - smartphone cao cap vivo',1),(10,'vivo V70 FE 5G','VIVO-V70FE',1,5,'vivo V70 FE 5G - smartphone vivo',1),(11,'OnePlus 12','OP12',1,6,'OnePlus 12 - smartphone cao cap OnePlus',1),(12,'OnePlus 11','OP11',1,6,'OnePlus 11 - smartphone cao cap OnePlus',1),(13,'realme 16T 5G','RM16T',1,7,'realme 16T 5G - smartphone realme',1),(14,'realme 16 5G','RM16',1,7,'realme 16 5G - smartphone realme',1),(15,'Google Pixel 8','GP-P8',1,8,'Google Pixel 8 - smartphone Google',1),(16,'Google Pixel 8 Pro','GP-P8P',1,8,'Google Pixel 8 Pro - smartphone Google',1),(17,'Sony Xperia 1','SONY-X1',1,9,'Sony Xperia 1 - smartphone Sony',1),(18,'Sony Xperia 1 V','SONY-X1V',1,9,'Sony Xperia 1 V - smartphone Sony',1),(19,'ASUS Zenfone 9','AS-ZF9',1,10,'ASUS Zenfone 9 - smartphone ASUS',1),(20,'ASUS ROG Phone','AS-ROG',1,10,'ASUS ROG Phone - smartphone gaming ASUS',1),(21,'Xiaomi 15','XM15',1,3,'Xiaomi 15 - smartphone cao cấp Xiaomi',1),(22,'Xiaomi 15 Ultra','XMI15U',1,3,'Xiaomi 15 Ultra - smartphone cao cap Xiaomi',1),(23,'Xiaomi 15T','XMI15T',1,3,'Xiaomi 15T - smartphone Xiaomi',1);
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `thanhtoan`
--

LOCK TABLES `thanhtoan` WRITE;
/*!40000 ALTER TABLE `thanhtoan` DISABLE KEYS */;
/*!40000 ALTER TABLE `thanhtoan` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `thitruong`
--

LOCK TABLES `thitruong` WRITE;
/*!40000 ALTER TABLE `thitruong` DISABLE KEYS */;
INSERT INTO `thitruong` VALUES (1,'VN','Việt Nam'),(2,'QT','Quốc tế');
/*!40000 ALTER TABLE `thitruong` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tonkho`
--

LOCK TABLES `tonkho` WRITE;
/*!40000 ALTER TABLE `tonkho` DISABLE KEYS */;
INSERT INTO `tonkho` VALUES (1,1,1,15),(2,1,2,12),(3,1,3,10),(4,1,4,20),(5,1,5,15),(6,1,6,10),(7,1,7,8),(8,1,8,12),(9,1,9,7),(10,1,10,15),(11,1,11,8),(12,1,12,8),(13,1,13,15),(14,1,14,20),(15,1,15,10),(16,1,16,6),(17,1,17,5),(18,1,18,4),(19,1,19,8),(20,1,20,10),(21,2,1,12),(22,2,2,10),(23,2,3,8),(24,2,4,15),(25,2,5,12),(26,2,6,8),(27,2,7,6),(28,2,8,10),(29,2,9,5),(30,2,10,12),(31,2,11,6),(32,2,12,6),(33,2,13,12),(34,2,14,15),(35,2,15,8),(36,2,16,5),(37,2,17,4),(38,2,18,3),(39,2,19,6),(40,2,20,8);
/*!40000 ALTER TABLE `tonkho` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `trangthaidonhang`
--

LOCK TABLES `trangthaidonhang` WRITE;
/*!40000 ALTER TABLE `trangthaidonhang` DISABLE KEYS */;
INSERT INTO `trangthaidonhang` VALUES (1,'Chờ xác nhận'),(5,'Đã giao'),(6,'Đã hủy'),(2,'Đã xác nhận'),(3,'Đang chuẩn bị'),(4,'Đang giao');
/*!40000 ALTER TABLE `trangthaidonhang` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `trangthainhacungcap`
--

LOCK TABLES `trangthainhacungcap` WRITE;
/*!40000 ALTER TABLE `trangthainhacungcap` DISABLE KEYS */;
INSERT INTO `trangthainhacungcap` VALUES (1,'Đang hoạt động'),(3,'Ngừng hợp tác'),(2,'Tạm ngưng');
/*!40000 ALTER TABLE `trangthainhacungcap` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `trangthaiphieunhap`
--

LOCK TABLES `trangthaiphieunhap` WRITE;
/*!40000 ALTER TABLE `trangthaiphieunhap` DISABLE KEYS */;
INSERT INTO `trangthaiphieunhap` VALUES (1,'Chờ nhập'),(3,'Đã hủy'),(2,'Đã nhập');
/*!40000 ALTER TABLE `trangthaiphieunhap` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-11-02 16:57:14
