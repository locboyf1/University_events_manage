package com.event.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.university.entity.Khoa;

public interface KhoaRepository extends JpaRepository<Khoa, Integer> {
}