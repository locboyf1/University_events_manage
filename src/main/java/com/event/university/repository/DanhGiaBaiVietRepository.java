package com.event.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.event.university.entity.BaiViet;
import com.event.university.entity.DanhGiaBaiViet;
import com.event.university.entity.NguoiDung;

public interface DanhGiaBaiVietRepository extends JpaRepository<DanhGiaBaiViet, Integer> {

    Boolean existsByNguoiDungAndBaiViet(NguoiDung nguoiDung, BaiViet baiViet);

    List<DanhGiaBaiViet> findByBaiViet(BaiViet baiViet);
    int countByBaiViet(BaiViet baiViet);
    @Query("SELECT AVG(d.soSao) FROM DanhGiaBaiViet d WHERE d.baiViet = :baiViet")
    Double tinhSaoTrungBinh(@Param("baiViet") BaiViet baiViet);

}

