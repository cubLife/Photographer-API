package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.CarouselImage;
import com.gmail.serhiisemiv.modeles.CostumerFeedback;
import com.gmail.serhiisemiv.repository.CarouselImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CarouselImageService {
    private final CarouselImageRepository carouselImageRepository;
    private final Logger error = LoggerFactory.getLogger(this.getClass());
    private final Logger debug = LoggerFactory.getLogger(this.getClass());
    private final Logger info = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CarouselImageService(CarouselImageRepository carouselImageRepository) {
        this.carouselImageRepository = carouselImageRepository;
    }

    public void addCarouselImage(CarouselImage carouselImage) throws ServiceException {
        if (carouselImage == null) {
            error.error("Input parameter was null", new IllegalArgumentException("Input parameter can't be null"));
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            info.info("Start saving new carousel image id - {}");
            carouselImageRepository.save(carouselImage);
            debug.debug("Carousel image is saved id - {}");
        } catch (NumberFormatException e) {
            error.error("Cant save carousel image  - ", e);
            throw new ServiceException("Can't save carousel image");
        }
    }

    public CarouselImage findCarouselImageById(int id) {
        info.info("Start returned carousel image with id - {}", id);
        Optional<CarouselImage> carouselImage = carouselImageRepository.findById(id);
        error.error("Carousel image is not present", new ServiceException("Can't find carousel image with id - " + id));
        if (carouselImage.isEmpty()) {
            throw new ServiceException("Can't find carousel image with id");
        }
        debug.debug("Carousel image was returned - {}", carouselImage.get());
        return carouselImage.get();
    }

    public List<CarouselImage> findAllCarouselImages() throws ServiceException {
        info.info("Starting returning all carousel images");
        try {
            List<CarouselImage> carouselImages = carouselImageRepository.findAll();
            debug.debug("All costumer feedback was returned");
            return carouselImages;
        } catch (NullPointerException e) {
            error.error("Can't find any carousel images - " + e.getMessage(), e);
            throw new ServiceException("Can't find any carousel images");
        }
    }

    public void deleteCarouselImageById(int id) throws ServiceException {
        info.info("Starting delete carousel image with id - {}", id);
        try {
            carouselImageRepository.deleteById(id);
            debug.debug("Carousel image was deleted id - {}", id);
        } catch (NoSuchElementException e) {
            error.error("Can't remove carousel image with id - " + id, e);
            throw new ServiceException("Can't delete carousel image with id - "+id);
        }
    }

    public CarouselImage createNewCarouselImage(MultipartFile file) throws IOException {
        CarouselImage carouselImage = new CarouselImage();
        carouselImage.setImage(file.getBytes());
        return carouselImage;
    }

}
