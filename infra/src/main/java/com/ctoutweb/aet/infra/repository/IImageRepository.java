package com.ctoutweb.aet.infra.repository;

import com.ctoutweb.aet.infra.repository.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IImageRepository extends JpaRepository<ImageEntity, Long> {
  public Optional<ImageEntity> findFirstByRandomName(String randmnName);
}
