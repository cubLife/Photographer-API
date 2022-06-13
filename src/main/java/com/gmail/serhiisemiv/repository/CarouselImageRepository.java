package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.CarouselImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarouselImageRepository extends JpaRepository<CarouselImage, Integer> {
}
