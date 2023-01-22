package com.example.projektJAVA.repository;

import com.example.projektJAVA.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumsRepository extends JpaRepository<Album, Long> {
}
