package com.ctoutweb.aet.infra.repository;

import com.ctoutweb.aet.infra.repository.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameRepository extends JpaRepository<GameEntity, Integer> {
}
