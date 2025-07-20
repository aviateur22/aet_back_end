package com.ctoutweb.aet.infra.repository;

import com.ctoutweb.aet.infra.repository.entity.MemoryCardGameImageFamilyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemoryCardGameImageFamilyRepository extends JpaRepository<MemoryCardGameImageFamilyEntity, Long> {
}
