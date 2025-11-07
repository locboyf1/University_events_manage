package com.event.university.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.event.university.entity.NguoiDung;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {

	List<NguoiDung> findByHoatDongTrue();

	Optional<NguoiDung> findByTaiKhoan(String taiKhoan);

	List<NguoiDung> findAllByTaiKhoan(String taiKhoan);

	Optional<NguoiDung> findByHoTen(String hoTen);

	Optional<NguoiDung> findById(String string);

	@Query("SELECT nd FROM NguoiDung nd " + "WHERE nd.hoatDong = true " + "AND nd.vaiTro.biDanh = 'sinhvien'")
	List<NguoiDung> findHoatDongSinhVien();

}
