package com.krishna.coding.mf.repository;

import com.krishna.coding.mf.entity.MFData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFDataRepository extends JpaRepository<MFData, Long> {
}
