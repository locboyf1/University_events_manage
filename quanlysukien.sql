-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 12, 2025 lúc 03:24 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlysukien`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `baiviet`
--

CREATE TABLE `baiviet` (
  `id` bigint(20) NOT NULL,
  `tieude` varchar(200) NOT NULL,
  `mota` varchar(500) NOT NULL,
  `noidung` longtext NOT NULL,
  `ngaytao` datetime NOT NULL,
  `manguoidung` int(11) NOT NULL,
  `hienthi` tinyint(1) NOT NULL,
  `anh` longtext NOT NULL,
  `madanhmuc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `binhluanbaiviet`
--

CREATE TABLE `binhluanbaiviet` (
  `id` bigint(20) NOT NULL,
  `manguoidung` int(11) NOT NULL,
  `mabaiviet` bigint(20) NOT NULL,
  `thoigian` datetime NOT NULL,
  `noidung` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `danhmucbaiviet`
--

CREATE TABLE `danhmucbaiviet` (
  `id` int(11) NOT NULL,
  `tendanhmuc` varchar(30) NOT NULL,
  `mota` varchar(500) NOT NULL,
  `thutu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `danhmucsukien`
--

CREATE TABLE `danhmucsukien` (
  `id` int(11) NOT NULL,
  `tendanhmuc` varchar(30) NOT NULL,
  `thutu` int(11) NOT NULL,
  `mota` varchar(500) NOT NULL,
  `bidanh` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `danhmucsukien`
--

INSERT INTO `danhmucsukien` (`id`, `tendanhmuc`, `thutu`, `mota`, `bidanh`) VALUES
(1, 'Cuộc thi', 1, 'Cuộc thi học thuật (lập trình, marketing, khởi nghiệp...).\r\nCuộc thi tài năng (ca hát, nhảy múa, nhiếp ảnh...).\r\nHackathon, Code Camp.', 'cuoc-thi'),
(2, 'Hội thảo', 2, 'Hội nghị khoa học sinh viên, báo cáo nghiên cứu.\r\nLịch bảo vệ khóa luận, luận văn.\r\nHội thảo chuyên đề, diễn thuyết của khách mời', 'hoi-thao'),
(3, 'Khen thưởng', 3, 'Lễ Khai giảng, Lễ Tốt nghiệp.\r\nLễ Tuyên dương & Khen thưởng\r\nLễ trao học bổng.', 'khen-thuong'),
(4, 'Cộng đồng', 4, 'Các sự kiện mang ý nghĩa xã hội, đóng góp cho cộng đồng.\r\nChiến dịch Mùa hè xanh, Tiếp sức mùa thi.\r\nNgày hội hiến máu nhân đạo.\r\nCác chương trình từ thiện, quyên góp.', 'cong-dong');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `danhsachthamgia`
--

CREATE TABLE `danhsachthamgia` (
  `id` bigint(20) NOT NULL,
  `masukien` int(11) NOT NULL,
  `manguoidung` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khoa`
--

CREATE TABLE `khoa` (
  `id` int(11) NOT NULL,
  `tenkhoa` varchar(100) NOT NULL,
  `mota` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khoa`
--

INSERT INTO `khoa` (`id`, `tenkhoa`, `mota`) VALUES
(1, 'Trường Kinh tế', 'Trường Kinh tế'),
(2, 'Viện Kỹ thuật và Công nghệ', 'Viện Kỹ thuật và Công nghệ'),
(3, 'Trường Khoa học Xã hội và Nhân văn', 'Trường Khoa học Xã hội và Nhân văn'),
(4, 'Khoa Giáo dục Quốc phòng và An ninh', 'Khoa Giáo dục Quốc phòng và An ninh'),
(5, 'Khoa Sư phạm Ngoại ngữ ', 'Khoa Sư phạm Ngoại ngữ ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lienlac`
--

CREATE TABLE `lienlac` (
  `id` int(11) NOT NULL,
  `ten` varchar(50) NOT NULL,
  `email` varchar(30) NOT NULL,
  `sdt` varchar(11) NOT NULL,
  `tieude` varchar(100) NOT NULL,
  `noidung` varchar(500) NOT NULL,
  `thoigian` datetime NOT NULL,
  `daphanhoi` tinyint(1) NOT NULL,
  `manguoidung` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lop`
--

CREATE TABLE `lop` (
  `id` int(11) NOT NULL,
  `manganh` int(11) NOT NULL,
  `tenlop` varchar(100) NOT NULL,
  `khoaso` int(11) NOT NULL,
  `mota` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `lop`
--

INSERT INTO `lop` (`id`, `manganh`, `tenlop`, `khoaso`, `mota`) VALUES
(1, 4, '63K1 CNTT', 63, '63K1 CNTT'),
(2, 4, '63K2 CNTT', 63, '63K2 CNTT'),
(3, 4, '64K1 CNTT', 64, '64K1 CNTT'),
(4, 4, '64K2 CNTT', 64, '64K2 CNTT');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nganh`
--

CREATE TABLE `nganh` (
  `id` int(11) NOT NULL,
  `makhoa` int(11) NOT NULL,
  `tennganh` varchar(100) NOT NULL,
  `mota` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nganh`
--

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

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nguoidung`
--

CREATE TABLE `nguoidung` (
  `id` int(11) NOT NULL,
  `taikhoan` varchar(30) NOT NULL,
  `matkhau` varchar(200) NOT NULL,
  `hoten` varchar(60) NOT NULL,
  `malop` int(11) DEFAULT NULL,
  `sdt` varchar(11) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mavaitro` int(11) NOT NULL,
  `anh` varchar(255) NOT NULL,
  `hoatdong` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nguoidung`
--

INSERT INTO `nguoidung` (`id`, `taikhoan`, `matkhau`, `hoten`, `malop`, `sdt`, `email`, `mavaitro`, `anh`, `hoatdong`) VALUES
(1, '225748020110014', '7c222fb2927d828af22f592134e8932480637c0d', 'Tạ Quang Lộc', 2, '0373819702', 'quangloc@admin.com', 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMdBqlgrpFx60XH_CdP3DpEZ7oTmvQuF4i9A&s', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sukien`
--

CREATE TABLE `sukien` (
  `id` int(11) NOT NULL,
  `tensukien` varchar(300) NOT NULL,
  `noidung` longtext NOT NULL,
  `anh` varchar(500) NOT NULL,
  `manguoidung` int(11) NOT NULL,
  `thoigiantao` datetime NOT NULL,
  `thoigianbatdau` datetime NOT NULL,
  `thoigianketthuc` datetime DEFAULT NULL,
  `batbuoc` tinyint(1) NOT NULL,
  `thoigiansuagannhat` datetime DEFAULT NULL,
  `hienthi` tinyint(1) NOT NULL,
  `madanhmuc` int(11) NOT NULL,
  `mota` varchar(500) NOT NULL,
  `diachi` varchar(100) NOT NULL,
  `bidanh` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sukien`
--

INSERT INTO `sukien` (`id`, `tensukien`, `noidung`, `anh`, `manguoidung`, `thoigiantao`, `thoigianbatdau`, `thoigianketthuc`, `batbuoc`, `thoigiansuagannhat`, `hienthi`, `madanhmuc`, `mota`, `diachi`, `bidanh`) VALUES
(1, 'LỄ TUYÊN DƯƠNG SINH VIÊN ĐẠT ĐIỂM CAO TRONG KỲ TUYỂN SINH NĂM 2025, SINH VIÊN ĐẠT DANH HIỆU SINH VIÊN XUẤT SẮC NĂM HỌC 2024 - 2025', 'LỄ TUYÊN DƯƠNG SINH VIÊN ĐẠT ĐIỂM CAO TRONG KỲ TUYỂN SINH NĂM 2025, SINH VIÊN ĐẠT DANH HIỆU SINH VIÊN XUẤT SẮC NĂM HỌC 2024 - 2025', 'https://scontent.fhan3-2.fna.fbcdn.net/v/t39.30808-6/561748527_822212843719718_7498481503206955276_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=127cfc&_nc_eui2=AeE6R6ZFtvJxEUtz6HvurioDITu6RBvxNDchO7pEG_E0N813QvXYrOuXrv5JnkGj8PNClqEj_w0uOxgmP-hByYEB&_nc_ohc=4RszoxJvnSwQ7kNvwHGOhCo&_nc_oc=Adkq421FyNSw74ZHFyajmkcCQFeZyuKFdNSl742PpMe3WqEfiW9kezAoqFW2qSp5pbrrOhEL8kIA2hY1lm4pwEuV&_nc_zt=23&_nc_ht=scontent.fhan3-2.fna&_nc_gid=JKhQLLxqfyKnJ4WyNLBSJg&oh=00_AfeAr0jh6ijRuk39Fz44zLwPrtZ_X5ib_BXcbKSHlDqDCA&oe=68F091D3', 1, '2025-10-11 22:04:51', '2025-10-11 22:04:51', NULL, 1, NULL, 1, 3, 'LỄ TUYÊN DƯƠNG SINH VIÊN ĐẠT ĐIỂM CAO TRONG KỲ TUYỂN SINH NĂM 2025, SINH VIÊN ĐẠT DANH HIỆU SINH VIÊN XUẤT SẮC NĂM HỌC 2024 - 2025', 'Hội trường A', 'le-tuyen-duong-sinh-vien-dat-diem-cao-trong-ky-tuyen-sinh-nam-2025-sinh-vien-dat-danh-hieu-sinh-vien-xuat-sac-nam-hoc-2024-2025'),
(2, 'UNI CARE DAY CHÍNH THỨC TRỞ LẠI ĐẠI HỌC VINH 📢\r\n', '\r\nSau những ngày mưa bão khiến kế hoạch phải tạm hoãn, Uni Care Day quay trở lại ý nghĩa hơn với thông điệp “Ch\r\nKhông để sinh viên Đại học Vinh phải chờ thêm nữa, chương trình sẽ chính thức diễn ra:\r\n🗓 15 - 16/10/2025 | 08:00 - 17:00\r\n📍 Khuôn viên Trường Đại học Vinh\r\n\r\nĐến với Uni Care Day, bạn sẽ được chăm sóc xe hoàn toàn FREE:\r\n🛞 IRC Việt Nam: Kiểm tra & tư vấn lốp xe an toàn sau ngập nước\r\n⚡ GS Việt Nam: Test ắc quy miễn phí, khôi phục sức mạnh cho xe\r\n🛢 ENEOS: Thay nhớt xe ga & xe số\r\n🎁 Ngoài ra còn có:\r\n• Quà tặng cực xịn: mũ bảo hiểm, túi canvas, móc khoá và nhiều quà tặng hấp dẫn khác\r\n• Mini game nhận quà, khu ẩm thực miễn phí cực vui\r\n\r\nChỉ 500 suất/ngày nhanh tay đăng ký ngay để xe được “chữa lành” sau bão và rinh quà liền tay!\r\n\r\n👉 Đăng ký ngay tại đây: https://event.xesolutions.vn/.../uni-care-day.../register\r\n\r\n#UniCareDay2025 #ChamSocXeMienPhi #IRC #GS #ENEOS Ẩn bớtăm sóc xe - Sẻ chia sau bão” 💙', 'https://event.xesolutions.vn/web/image/event.event/24/image_logo', 1, '2025-10-11 22:07:08', '2025-10-31 03:07:08', NULL, 0, NULL, 1, 4, 'Đến với Uni Care Day, bạn sẽ được chăm sóc xe hoàn toàn FREE:\r\n🛞 IRC Việt Nam: Kiểm tra & tư vấn lốp xe an toàn sau ngập nước\r\n⚡ GS Việt Nam: Test ắc quy miễn phí, khôi phục sức mạnh cho xe\r\n🛢 ENEOS: Thay nhớt xe ga & xe số', 'Hội trường A', 'uni-care-day-chinh-thuc-tro-lai-dai-hoc-vinh');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `vaitro`
--

CREATE TABLE `vaitro` (
  `id` int(11) NOT NULL,
  `tenvaitro` varchar(30) NOT NULL,
  `mota` varchar(100) NOT NULL,
  `bidanh` varchar(20) NOT NULL,
  `quyentaosukien` tinyint(1) NOT NULL,
  `quyenduyetsukien` tinyint(1) NOT NULL,
  `quyenquanlybaiviet` tinyint(1) NOT NULL,
  `quyensuamenu` tinyint(1) NOT NULL,
  `quyenquanlynguoidung` tinyint(1) NOT NULL,
  `quyenthemsukien` tinyint(1) NOT NULL,
  `quyenhotrolienlac` tinyint(1) NOT NULL,
  `quyenhientrollenlac` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `vaitro`
--

INSERT INTO `vaitro` (`id`, `tenvaitro`, `mota`, `bidanh`, `quyentaosukien`, `quyenduyetsukien`, `quyenquanlybaiviet`, `quyensuamenu`, `quyenquanlynguoidung`, `quyenthemsukien`, `quyenhotrolienlac`, `quyenhientrollenlac`) VALUES
(1, 'Quản trị viên', 'toàn quyền', 'qtv', 1, 1, 1, 1, 1, 1, 1, b'0');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `baiviet`
--
ALTER TABLE `baiviet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `madanhmuc` (`madanhmuc`),
  ADD KEY `nguoitao` (`manguoidung`);

--
-- Chỉ mục cho bảng `binhluanbaiviet`
--
ALTER TABLE `binhluanbaiviet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKeu71a4nvq6cms4j0if3wpnkiy` (`mabaiviet`),
  ADD KEY `FK6bkc8po1yxfe9fliklm2brh4y` (`manguoidung`);

--
-- Chỉ mục cho bảng `danhmucbaiviet`
--
ALTER TABLE `danhmucbaiviet`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `danhmucsukien`
--
ALTER TABLE `danhmucsukien`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `danhsachthamgia`
--
ALTER TABLE `danhsachthamgia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `masukien` (`masukien`),
  ADD KEY `manguoidung` (`manguoidung`);

--
-- Chỉ mục cho bảng `khoa`
--
ALTER TABLE `khoa`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `lienlac`
--
ALTER TABLE `lienlac`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKm93cb0xy3838q0x9s9np87wd9` (`manguoidung`);

--
-- Chỉ mục cho bảng `lop`
--
ALTER TABLE `lop`
  ADD PRIMARY KEY (`id`),
  ADD KEY `manganh` (`manganh`);

--
-- Chỉ mục cho bảng `nganh`
--
ALTER TABLE `nganh`
  ADD PRIMARY KEY (`id`),
  ADD KEY `makhoa` (`makhoa`);

--
-- Chỉ mục cho bảng `nguoidung`
--
ALTER TABLE `nguoidung`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mavaitro` (`mavaitro`),
  ADD KEY `malop` (`malop`);

--
-- Chỉ mục cho bảng `sukien`
--
ALTER TABLE `sukien`
  ADD PRIMARY KEY (`id`),
  ADD KEY `madanhmuc` (`madanhmuc`),
  ADD KEY `manguoidung` (`manguoidung`);

--
-- Chỉ mục cho bảng `vaitro`
--
ALTER TABLE `vaitro`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `baiviet`
--
ALTER TABLE `baiviet`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `binhluanbaiviet`
--
ALTER TABLE `binhluanbaiviet`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `danhmucbaiviet`
--
ALTER TABLE `danhmucbaiviet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `danhmucsukien`
--
ALTER TABLE `danhmucsukien`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `danhsachthamgia`
--
ALTER TABLE `danhsachthamgia`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `khoa`
--
ALTER TABLE `khoa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `lienlac`
--
ALTER TABLE `lienlac`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `lop`
--
ALTER TABLE `lop`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `nganh`
--
ALTER TABLE `nganh`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `nguoidung`
--
ALTER TABLE `nguoidung`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `sukien`
--
ALTER TABLE `sukien`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `vaitro`
--
ALTER TABLE `vaitro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `baiviet`
--
ALTER TABLE `baiviet`
  ADD CONSTRAINT `baiviet_ibfk_1` FOREIGN KEY (`madanhmuc`) REFERENCES `danhmucbaiviet` (`id`),
  ADD CONSTRAINT `baiviet_ibfk_2` FOREIGN KEY (`manguoidung`) REFERENCES `nguoidung` (`id`);

--
-- Các ràng buộc cho bảng `binhluanbaiviet`
--
ALTER TABLE `binhluanbaiviet`
  ADD CONSTRAINT `FK6bkc8po1yxfe9fliklm2brh4y` FOREIGN KEY (`manguoidung`) REFERENCES `nguoidung` (`id`),
  ADD CONSTRAINT `FKeu71a4nvq6cms4j0if3wpnkiy` FOREIGN KEY (`mabaiviet`) REFERENCES `baiviet` (`id`);

--
-- Các ràng buộc cho bảng `danhsachthamgia`
--
ALTER TABLE `danhsachthamgia`
  ADD CONSTRAINT `danhsachthamgia_ibfk_1` FOREIGN KEY (`masukien`) REFERENCES `sukien` (`id`),
  ADD CONSTRAINT `danhsachthamgia_ibfk_2` FOREIGN KEY (`manguoidung`) REFERENCES `nguoidung` (`id`);

--
-- Các ràng buộc cho bảng `lienlac`
--
ALTER TABLE `lienlac`
  ADD CONSTRAINT `FKm93cb0xy3838q0x9s9np87wd9` FOREIGN KEY (`manguoidung`) REFERENCES `nguoidung` (`id`),
  ADD CONSTRAINT `lienlac_ibfk_1` FOREIGN KEY (`id`) REFERENCES `nguoidung` (`id`);

--
-- Các ràng buộc cho bảng `lop`
--
ALTER TABLE `lop`
  ADD CONSTRAINT `lop_ibfk_1` FOREIGN KEY (`manganh`) REFERENCES `nganh` (`id`);

--
-- Các ràng buộc cho bảng `nganh`
--
ALTER TABLE `nganh`
  ADD CONSTRAINT `nganh_ibfk_1` FOREIGN KEY (`makhoa`) REFERENCES `khoa` (`id`);

--
-- Các ràng buộc cho bảng `nguoidung`
--
ALTER TABLE `nguoidung`
  ADD CONSTRAINT `nguoidung_ibfk_1` FOREIGN KEY (`mavaitro`) REFERENCES `vaitro` (`id`),
  ADD CONSTRAINT `nguoidung_ibfk_2` FOREIGN KEY (`malop`) REFERENCES `lop` (`id`);

--
-- Các ràng buộc cho bảng `sukien`
--
ALTER TABLE `sukien`
  ADD CONSTRAINT `sukien_ibfk_1` FOREIGN KEY (`madanhmuc`) REFERENCES `danhmucsukien` (`id`),
  ADD CONSTRAINT `sukien_ibfk_2` FOREIGN KEY (`manguoidung`) REFERENCES `nguoidung` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
