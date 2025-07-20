package com.ctoutweb.aet.infra.repository;

import com.ctoutweb.aet.infra.repository.entity.GameImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameImageRepository extends JpaRepository<GameImageEntity, Long> {
}
