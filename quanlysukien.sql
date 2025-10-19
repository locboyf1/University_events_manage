/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS `quanlysukien` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `quanlysukien`;

DROP TABLE IF EXISTS `baiviet`;
CREATE TABLE IF NOT EXISTS `baiviet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tieude` varchar(200) NOT NULL,
  `mota` varchar(500) DEFAULT NULL,
  `anh` longtext NOT NULL,
  `noidung` longtext NOT NULL,
  `ngaytao` datetime(6) NOT NULL,
  `hienthi` tinyint(1) NOT NULL,
  `manguoidung` int NOT NULL,
  `madanhmuc` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_baiviet_danhmucbaiviet` (`madanhmuc`),
  KEY `FK_baiviet_nguoidung` (`manguoidung`),
  CONSTRAINT `FK_baiviet_danhmucbaiviet` FOREIGN KEY (`madanhmuc`) REFERENCES `danhmucbaiviet` (`id`),
  CONSTRAINT `FK_baiviet_nguoidung` FOREIGN KEY (`manguoidung`) REFERENCES `nguoidung` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `binhluanbaiviet`;
CREATE TABLE IF NOT EXISTS `binhluanbaiviet` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `noidung` varchar(500) NOT NULL,
  `thoigian` datetime(6) NOT NULL,
  `manguoidung` int NOT NULL,
  `mabaiviet` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_binhluanbaiviet_baiviet` (`mabaiviet`),
  KEY `FK_binhluanbaiviet_nguoidung` (`manguoidung`),
  CONSTRAINT `FK_binhluanbaiviet_baiviet` FOREIGN KEY (`mabaiviet`) REFERENCES `baiviet` (`id`),
  CONSTRAINT `FK_binhluanbaiviet_nguoidung` FOREIGN KEY (`manguoidung`) REFERENCES `nguoidung` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `chitietquyen`;
CREATE TABLE IF NOT EXISTS `chitietquyen` (
  `mavaitro` int NOT NULL,
  `maquyen` int NOT NULL,
  PRIMARY KEY (`mavaitro`,`maquyen`),
  KEY `FK_chitietquyen_quyen` (`maquyen`),
  CONSTRAINT `FK_chitietquyen_quyen` FOREIGN KEY (`maquyen`) REFERENCES `quyen` (`id`),
  CONSTRAINT `FK_chitietquyen_vaitro` FOREIGN KEY (`mavaitro`) REFERENCES `vaitro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `danhmucbaiviet`;
CREATE TABLE IF NOT EXISTS `danhmucbaiviet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tendanhmuc` varchar(30) NOT NULL,
  `thutu` int NOT NULL,
  `mota` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `danhmucsukien`;
CREATE TABLE IF NOT EXISTS `danhmucsukien` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tendanhmuc` varchar(30) NOT NULL,
  `thutu` int NOT NULL,
  `mota` varchar(500) DEFAULT NULL,
  `bidanh` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `danhmucsukien` (`id`, `tendanhmuc`, `thutu`, `mota`, `bidanh`) VALUES
	(1, 'Cuộc thi', 1, 'Cuộc thi học thuật (lập trình, marketing, khởi nghiệp...). Cuộc thi tài năng (ca hát, nhảy múa, nhiếp ảnh...). Hackathon, Code Camp.', 'cuoc-thi'),
	(2, 'Hội thảo', 2, 'Hội nghị khoa học sinh viên, báo cáo nghiên cứu. Lịch bảo vệ khóa luận, luận văn. Hội thảo chuyên đề, diễn thuyết của khách mời', 'hoi-thao'),
	(3, 'Khen thưởng', 3, 'Lễ Khai giảng, Lễ Tốt nghiệp. Lễ Tuyên dương & Khen thưởng. Lễ trao học bổng.', 'khen-thuong'),
	(4, 'Cộng đồng', 4, 'Các sự kiện mang ý nghĩa xã hội, đóng góp cho cộng đồng. Chiến dịch Mùa hè xanh, Tiếp sức mùa thi. Ngày hội hiến máu nhân đạo. Các chương trình từ thiện, quyên góp.', 'cong-dong');

DROP TABLE IF EXISTS `danhsachthamgia`;
CREATE TABLE IF NOT EXISTS `danhsachthamgia` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `manguoidung` int NOT NULL,
  `masukien` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_danhsachthamgia_nguoidung` (`manguoidung`),
  KEY `FK_danhsachthamgia_sukien` (`masukien`),
  CONSTRAINT `FK_danhsachthamgia_nguoidung` FOREIGN KEY (`manguoidung`) REFERENCES `nguoidung` (`id`),
  CONSTRAINT `FK_danhsachthamgia_sukien` FOREIGN KEY (`masukien`) REFERENCES `sukien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `khoa`;
CREATE TABLE IF NOT EXISTS `khoa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tenkhoa` varchar(100) NOT NULL,
  `mota` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `khoa` (`id`, `tenkhoa`, `mota`) VALUES
	(1, 'Trường Kinh tế', 'Trường Kinh tế'),
	(2, 'Viện Kỹ thuật và Công nghệ', 'Viện Kỹ thuật và Công nghệ'),
	(3, 'Trường Khoa học Xã hội và Nhân văn', 'Trường Khoa học Xã hội và Nhân văn'),
	(4, 'Khoa Giáo dục Quốc phòng và An ninh', 'Khoa Giáo dục Quốc phòng và An ninh'),
	(5, 'Khoa Sư phạm Ngoại ngữ ', 'Khoa Sư phạm Ngoại ngữ ');

DROP TABLE IF EXISTS `lienlac`;
CREATE TABLE IF NOT EXISTS `lienlac` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ten` varchar(50) NOT NULL,
  `email` varchar(30) NOT NULL,
  `sdt` varchar(11) NOT NULL,
  `tieude` varchar(100) NOT NULL,
  `noidung` varchar(500) NOT NULL,
  `thoigian` datetime(6) NOT NULL,
  `daphanhoi` tinyint(1) NOT NULL,
  `manguoidung` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lienlac_nguoidung` (`manguoidung`),
  CONSTRAINT `FK_lienlac_nguoidung` FOREIGN KEY (`manguoidung`) REFERENCES `nguoidung` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `lop`;
CREATE TABLE IF NOT EXISTS `lop` (
  `id` int NOT NULL AUTO_INCREMENT,
  `manganh` int NOT NULL,
  `tenlop` varchar(100) NOT NULL,
  `khoaso` int NOT NULL,
  `mota` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `manganh` (`manganh`),
  CONSTRAINT `FK_lop_nganh` FOREIGN KEY (`manganh`) REFERENCES `nganh` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `lop` (`id`, `manganh`, `tenlop`, `khoaso`, `mota`) VALUES
	(1, 4, '63K1 CNTT', 63, '63K1 CNTT'),
	(2, 4, '63K2 CNTT', 63, '63K2 CNTT'),
	(3, 4, '64K1 CNTT', 64, '64K1 CNTT'),
	(4, 4, '64K2 CNTT', 64, '64K2 CNTT');

DROP TABLE IF EXISTS `menu`;
CREATE TABLE IF NOT EXISTS `menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tieude` varchar(30) NOT NULL,
  `bidanh` varchar(30) NOT NULL,
  `thutu` int NOT NULL,
  `mota` varchar(100) DEFAULT NULL,
  `hien` tinyint(1) NOT NULL,
  `mamenucha` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_menu_menu` (`mamenucha`),
  CONSTRAINT `FK_menu_menu` FOREIGN KEY (`mamenucha`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `menu` (`id`, `tieude`, `bidanh`, `thutu`, `mota`, `hien`, `mamenucha`) VALUES
	(1, 'Trang chủ', 'trangchu', 1, NULL, 1, NULL);

DROP TABLE IF EXISTS `nganh`;
CREATE TABLE IF NOT EXISTS `nganh` (
  `id` int NOT NULL AUTO_INCREMENT,
  `makhoa` int NOT NULL,
  `tennganh` varchar(100) NOT NULL,
  `mota` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `makhoa` (`makhoa`),
  CONSTRAINT `FK_nganh_khoa` FOREIGN KEY (`makhoa`) REFERENCES `khoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `nganh` (`id`, `makhoa`, `tennganh`, `mota`) VALUES
	(1, 5, 'Sư phạm tiếng Anh', 'Sư phạm tiếng Anh'),
	(2, 5, 'Ngôn ngữ Anh', 'Ngôn ngữ Anh'),
	(3, 2, 'Công nghệ thông tin CLC', 'Công nghệ thông tin CLC'),
	(4, 2, 'Công nghệ thông tin', 'Công nghệ thông tin'),
	(5, 2, 'Kỹ thuật phần mềm', 'Kỹ thuật phần mềm'),
	(6, 2, 'Khoa học máy tính', 'Khoa học máy tính'),
	(7, 2, 'Kỹ thuật điện tử - viễn thông', 'Kỹ thuật điện tử - viễn thông'),
	(8, 1, 'Kinh tế đầu tư', 'Kinh tế đầu tư'),
	(9, 1, 'Quản trị kinh doanh', 'Quản trị kinh doanh'),
	(10, 1, 'Tài chính ngân hàng', 'Tài chính ngân hàng');

DROP TABLE IF EXISTS `nguoidung`;
CREATE TABLE IF NOT EXISTS `nguoidung` (
  `id` int NOT NULL AUTO_INCREMENT,
  `taikhoan` varchar(30) NOT NULL,
  `matkhau` varchar(200) NOT NULL,
  `hoten` varchar(60) NOT NULL,
  `malop` int DEFAULT NULL,
  `sdt` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mavaitro` int NOT NULL,
  `anh` varchar(255) NOT NULL,
  `hoatdong` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `mavaitro` (`mavaitro`),
  KEY `malop` (`malop`),
  CONSTRAINT `FK_nguoidung_lop` FOREIGN KEY (`malop`) REFERENCES `lop` (`id`),
  CONSTRAINT `FK_nguoidung_vaitro` FOREIGN KEY (`mavaitro`) REFERENCES `vaitro` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `nguoidung` (`id`, `taikhoan`, `matkhau`, `hoten`, `malop`, `sdt`, `email`, `mavaitro`, `anh`, `hoatdong`) VALUES
	(10, '225748020110052', '$2a$10$oVxwORnoVI1FbrSeVT2THuFVPk2gA7A2hG4sE9UXX.2rpxCUTrqMO', 'Hà Vi', 1, NULL, NULL, 7, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMdBqlgrpFx60XH_CdP3DpEZ7oTmvQuF4i9A&s', 1),
	(11, 'loctq', '$2a$10$n3u8uTiTbnMalTLTxTgBXeuhQQZSINiuW6x0qp6EKP2Db1YjWFmVO', 'Quang Lộc', 3, NULL, NULL, 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMdBqlgrpFx60XH_CdP3DpEZ7oTmvQuF4i9A&s', 1);

DROP TABLE IF EXISTS `quyen`;
CREATE TABLE IF NOT EXISTS `quyen` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tenquyen` varchar(50) NOT NULL DEFAULT '',
  `mota` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `sukien`;
CREATE TABLE IF NOT EXISTS `sukien` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tensukien` varchar(300) NOT NULL,
  `bidanh` varchar(300) NOT NULL,
  `mota` varchar(500) DEFAULT NULL,
  `noidung` longtext NOT NULL,
  `anh` varchar(500) NOT NULL,
  `diachi` varchar(100) NOT NULL,
  `thoigiantao` datetime(6) NOT NULL,
  `thoigianbatdau` datetime(6) NOT NULL,
  `thoigianketthuc` datetime(6) DEFAULT NULL,
  `thoigiansuagannhat` datetime(6) DEFAULT NULL,
  `sdthotro` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `emailhotro` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `batbuoc` tinyint(1) NOT NULL,
  `hienthi` tinyint(1) NOT NULL,
  `manguoidung` int NOT NULL,
  `madanhmuc` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sukien_nguoidung` (`manguoidung`),
  KEY `FK_sukien_danhmucsukien` (`madanhmuc`),
  CONSTRAINT `FK_sukien_danhmucsukien` FOREIGN KEY (`madanhmuc`) REFERENCES `danhmucsukien` (`id`),
  CONSTRAINT `FK_sukien_nguoidung` FOREIGN KEY (`manguoidung`) REFERENCES `nguoidung` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `sukien` (`id`, `tensukien`, `bidanh`, `mota`, `noidung`, `anh`, `diachi`, `thoigiantao`, `thoigianbatdau`, `thoigianketthuc`, `thoigiansuagannhat`, `sdthotro`, `emailhotro`, `batbuoc`, `hienthi`, `manguoidung`, `madanhmuc`) VALUES
	(1, ' LỄ TUYÊN DƯƠNG SINH VIÊN ĐẠT DANH HIỆU SINH VIÊN GIỎI NĂM HỌC 2024 - 2025 ', 'le_trao_giai', 'Sáng ngày 12/10/2025, khép lại chuỗi hoạt động tuyên dương thành tích học tập của sinh viên trong năm học 2024 - 2025, Trường Đại học Vinh long trọng tổ chức Lễ tuyên dương sinh viên đạt danh hiệu Sinh viên Giỏi dành cho sinh viên Trường Sư phạm - đơn vị có số lượng sinh viên Giỏi lớn nhất toàn Trường với 1.201 em được vinh danh.', '<p>\r\n🎓 504 SINH VIÊN ĐẠT DANH HIỆU SINH VIÊN GIỎI NĂM HỌC 2024 - 2025 ĐƯỢC TUYÊN DƯƠNG CHIỀU NGÀY 11/10/2025\r\nĐó là những gương mặt sinh viên tiêu biểu đến từ các đơn vị: Khoa Giáo dục Quốc phòng; Khoa Xây dựng; Viện Nông nghiệp và Tài nguyên; Viện Công nghệ Hóa sinh Môi trường; Khoa Sư phạm Ngoại ngữ.\r\n🌺 Những gương mặt rạng rỡ, những nụ cười tự hào trong buổi lễ hôm nay là minh chứng cho hành trình không ngừng nỗ lực của các bạn sinh viên trên con đường học tập và rèn luyện.\r\n💫 Mỗi phần thưởng là kết tinh của tri thức, kỷ luật và niềm đam mê. Những sinh viên được vinh danh hôm nay sẽ tiếp tục là niềm tự hào của Nhà trường, của gia đình và của chính bản thân mình.\r\nChúc mừng các bạn – những đại diện tiêu biểu của tuổi trẻ Trường Đại học Vinh!\r\n</p>\r\n<p>\r\n🎓 LỄ TUYÊN DƯƠNG SINH VIÊN ĐẠT DANH HIỆU SINH VIÊN GIỎI NĂM HỌC 2024 - 2025 - TRƯỜNG SƯ PHẠM\r\n🌷 Sáng ngày 12/10/2025, khép lại chuỗi hoạt động tuyên dương thành tích học tập của sinh viên trong năm học 2024 - 2025, Trường Đại học Vinh long trọng tổ chức Lễ tuyên dương sinh viên đạt danh hiệu Sinh viên Giỏi dành cho sinh viên Trường Sư phạm - đơn vị có số lượng sinh viên Giỏi lớn nhất toàn Trường với 1.201 em được vinh danh.\r\n💖 Buổi lễ không chỉ là dịp tôn vinh những nỗ lực học tập, mà còn là hành trình tri ân, là khoảnh khắc lưu giữ những ký ức đẹp trong đời sinh viên. Những gương mặt rạng rỡ, những nụ cười tự hào là minh chứng sống động cho tinh thần vượt khó, ý chí vươn lên và niềm đam mê tri thức của thế hệ sinh viên Sư phạm hôm nay.\r\n🎉 Xin chúc mừng các bạn sinh viên Trường Sư phạm - những “người gieo hạt tri thức” tương lai, hãy luôn giữ vững ngọn lửa đam mê, lan tỏa giá trị nhân văn và tiếp nối truyền thống vẻ vang của mái trường anh hùng!\r\n🌿 2.423 sinh viên giỏi được vinh danh qua các đợt là 2.423 câu chuyện về đam mê, khát vọng và nghị lực - minh chứng cho sức sống, trí tuệ và bản lĩnh của tuổi trẻ Trường Đại học Vinh trong kỷ nguyên hội nhập.\r\n</p>\r\n<p>\r\n🎓 427 SINH VIÊN VIỆN KỸ THUẬT - CÔNG NGHỆ VÀ TRƯỜNG KHOA HỌC XÃ HỘI VÀ NHÂN VĂN NHẬN DANH HIỆU SINH VIÊN GIỎI NĂM HỌC 2024 - 2025\r\n🌼 Niềm vui, sự xúc động và tự hào lan tỏa trong từng khoảnh khắc trao danh hiệu Sinh viên Giỏi năm học 2024 - 2025.\r\n🎖 427 sinh viên của 2 đơn vị được vinh danh chính là minh chứng cho bản lĩnh, trí tuệ và tinh thần vươn lên không ngừng của thế hệ trẻ Trường Đại học Vinh - những con người đang góp phần làm nên uy tín, thương hiệu và vị thế của Nhà trường trong hệ thống giáo dục đại học Việt Nam và thế giới.\r\nChúc mừng các bạn sinh viên đã chạm đến “trái ngọt” sau một năm học đầy nỗ lực! 🌟\r\n</p>', 'https://scontent.fvii1-1.fna.fbcdn.net/v/t39.30808-6/560535852_825542316720104_6487899405877514450_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=127cfc&_nc_eui2=AeEGtiDopzNDPWjZY_gZkOh-vuMPpgHNC6S-4w-mAc0LpEn7R05O3DnN4eTe94A_s1T59WuvTfF71YItMYNyKDQ4&_nc_ohc=wpeNIDdNfLkQ7kNvwGVycyb&_nc_oc=Adm3KVFgbKDxYvuMiyWymLJ45ZDJmhmr9scNjlzV843QHZp4EbJ5X1BwlRE7Nq-extt6TJsEh8mMUoxawDpEI7Id&_nc_zt=23&_nc_ht=scontent.fvii1-1.fna&_nc_gid=xi5uMZso3Ph5bqhjInCF7A&oh=00_AfeoM8SAj9HgywZ-IpwJ1AX9EhRfnxdx5xWkgrEWh1IXxw&oe=68F907FC', 'Hội trường ', '2025-10-18 14:10:55.000000', '2025-10-18 14:10:56.000000', NULL, NULL, NULL, NULL, 1, 1, 11, 3);

DROP TABLE IF EXISTS `vaitro`;
CREATE TABLE IF NOT EXISTS `vaitro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tenvaitro` varchar(30) DEFAULT NULL,
  `mota` varchar(100) DEFAULT NULL,
  `bidanh` varchar(20) NOT NULL,
  `capbac` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `vaitro` (`id`, `tenvaitro`, `mota`, `bidanh`, `capbac`) VALUES
	(1, 'Quản trị viên', 'toàn quyền', 'quantrivien', 10),
	(2, 'Quản trị sự kiện', 'sự kiện', 'quantrisukien', 8),
	(3, 'Quản trị giao diện web', 'Menu, banner, ...', 'quantrigiaodien', 8),
	(4, 'Quản trị bài viết', NULL, 'quantribaiviet', 8),
	(5, 'Quản trị tài khoản', NULL, 'quantritaikhoan', 8),
	(6, 'Người tạo sự kiện', 'Các khoa, viện, ...', 'nguoitaosukien', 6),
	(7, 'Sinh viên', 'Chỉ dùng thôi', 'sinhvien', 2);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
