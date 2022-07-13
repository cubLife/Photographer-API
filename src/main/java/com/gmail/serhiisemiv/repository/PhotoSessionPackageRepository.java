package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.PhotoSessionPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoSessionPackageRepository extends JpaRepository<PhotoSessionPackage, Integer> {
}
