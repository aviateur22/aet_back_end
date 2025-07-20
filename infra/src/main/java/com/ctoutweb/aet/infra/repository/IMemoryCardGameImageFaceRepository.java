package com.ctoutweb.aet.infra.repository;

import com.ctoutweb.aet.infra.repository.entity.MemoryCardGameImageFaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemoryCardGameImageFaceRepository extends JpaRepository<MemoryCardGameImageFaceEntity, Integer> {
}
