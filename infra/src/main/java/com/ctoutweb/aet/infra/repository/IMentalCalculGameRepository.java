package com.ctoutweb.aet.infra.repository;

import com.ctoutweb.aet.infra.repository.entity.MentalCalculGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMentalCalculGameRepository extends JpaRepository<MentalCalculGameEntity, Long> {
}
