package com.event.university.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.event.university.entity.BaiViet;

public interface BaiVietRepository extends JpaRepository<BaiViet, Integer> {

	public Optional<BaiViet> findById(Integer id);

	public List<BaiViet> findByOrderByNgayTaoDesc();

	public List<BaiViet> findByHienThiTrueOrderByNgayTaoDesc();

	@Query("SELECT bv FROM BaiViet bv WHERE (bv.tieuDe LIKE CONCAT('%', :keyword, '%' ) OR bv.moTa LIKE CONCAT('%', :keyword, '%' ) OR bv.noiDung LIKE CONCAT('%', :keyword, '%' )) AND bv.hienThi = true")
	public Page<BaiViet> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
	
}
