package com.event.university.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.university.entity.DanhSachThamGia;
import com.event.university.entity.NguoiDung;
import com.event.university.entity.SuKien;

@Repository
public interface DanhSachThamGiaRepository extends JpaRepository<DanhSachThamGia, Integer> {
    
    // 🔹 Lấy danh sách người tham gia theo mã sự kiện
    List<DanhSachThamGia> findBySuKien_Id(Integer suKienId);
    
    Optional<DanhSachThamGia> findBySuKienAndNguoiDung(SuKien suKien, NguoiDung nguoiDung);
    
    void deleteBySuKien_IdAndNguoiDung_Id( Integer nguoiDungId,Integer suKienId);
}
