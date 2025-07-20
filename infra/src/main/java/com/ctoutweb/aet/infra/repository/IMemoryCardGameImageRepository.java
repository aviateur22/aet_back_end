package com.ctoutweb.aet.infra.repository;

import com.ctoutweb.aet.infra.repository.entity.ImageEntity;
import com.ctoutweb.aet.infra.repository.entity.MemoryCardGameImageEntity;
import com.ctoutweb.aet.infra.repository.entity.MemoryCardGameImageFaceEntity;
import com.ctoutweb.aet.infra.repository.entity.MemoryCardGameImageFamilyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMemoryCardGameImageRepository extends JpaRepository<MemoryCardGameImageEntity, Long> {

  @Query(value = """ 
        SELECT m.image FROM MemoryCardGameImageEntity m 
        WHERE m.memoryCardGameImageFamily = :family 
        and m.memoryCardGameImageFace = :cardFace""")
  public List<ImageEntity> findImageByFaceAndFamilyList(@Param("cardFace") MemoryCardGameImageFaceEntity cardFace, @Param("family") MemoryCardGameImageFamilyEntity family);

  @Query(value = """ 
        SELECT m.image FROM MemoryCardGameImageEntity m
        where m.memoryCardGameImageFace = :cardFace""")
  public List<ImageEntity> findImageByFaceList(@Param("cardFace") MemoryCardGameImageFaceEntity cardFace);

}
