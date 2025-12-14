package com.event.university.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.event.university.entity.BaiViet;
import com.event.university.entity.DanhMucBaiViet;
import com.event.university.entity.NguoiDung;
import com.event.university.repository.BaiVietRepository;
import com.event.university.utillities.Functions;
import com.event.university.utillities.ImageProcess;

@Service
public class BaiVietService {
	@Autowired
	BaiVietRepository baiVietRepository;

	@Autowired
	NguoiDungService nguoiDungService;

	public Page<BaiViet> findByKeyword(String keyword, Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("ngayTao").descending());
		return baiVietRepository.findByKeyword(keyword, pageable);
	}

	public BaiViet findById(Integer id) {
		return baiVietRepository.findById(id).orElse(null);
	}

	public List<BaiViet> findAll() {
		return baiVietRepository.findAll();
	}

	public List<BaiViet> findByNgayTaoAfter(LocalDateTime time) {
		return baiVietRepository.findByNgayTaoAfter(time);
	}

	public List<BaiViet> findByHienThiTrueOrderByNgayTaoDesc() {
		return baiVietRepository.findByHienThiTrueOrderByNgayTaoDesc();
	}

	public List<BaiViet> findByOrderNgayTaoDesc() {
		return baiVietRepository.findByOrderByNgayTaoDesc();
	}

	public void deleteById(Integer id) {
		baiVietRepository.deleteById(id);
	}

	public void create(BaiViet baiViet, MultipartFile fileAnh, NguoiDung nguoiDung) throws IOException {
		byte[] anhBase64 = fileAnh.getBytes();
		String kieuAnh = fileAnh.getContentType();
		baiViet.setAnh(ImageProcess.convertImage2String(anhBase64, kieuAnh));
		baiViet.setBiDanh(Functions.convertStringToAlias(baiViet.getTieuDe()));
		baiViet.setNgayTao(LocalDateTime.now());
		baiViet.setNguoiDung(nguoiDungService.findById(nguoiDung.getId()));

		baiVietRepository.save(baiViet);
	}

	public void update(BaiViet baiViet, MultipartFile fileAnh) throws IOException {
		BaiViet baiVietDB = baiVietRepository.findById(baiViet.getId()).orElse(null);
		baiViet.setNgayTao(baiVietDB.getNgayTao());
		baiViet.setNguoiDung(baiVietDB.getNguoiDung());
		baiViet.setDanhMucBaiViet(baiVietDB.getDanhMucBaiViet());
		baiViet.setBiDanh(Functions.convertStringToAlias(baiViet.getTieuDe()));
		if (fileAnh.isEmpty()) {

			baiViet.setAnh(baiVietDB.getAnh());
		} else {
			byte[] anhBase64 = fileAnh.getBytes();
			String kieuAnh = fileAnh.getContentType();
			baiViet.setAnh(ImageProcess.convertImage2String(anhBase64, kieuAnh));
		}

		baiVietRepository.save(baiViet);

	}

	public void show(BaiViet baiViet) {
		baiViet.setHienThi(!baiViet.getHienThi());
		baiVietRepository.save(baiViet);
	}

    public List<BaiViet> layTopBaiVietNhieuBinhLuan(int soLuong) {
        Pageable pageable = PageRequest.of(0, soLuong);
        return baiVietRepository.findTopBaiVietNhieuBinhLuan(pageable);
    }
    public List<BaiViet> layCacBaiVietLienQuan(DanhMucBaiViet danhMucBaiViet, Integer idBaiViet) {
		return baiVietRepository.findTop5ByDanhMucBaiVietAndIdNotOrderByNgayTaoDesc(danhMucBaiViet, idBaiViet);
	}
}
