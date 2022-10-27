package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographerRepository extends JpaRepository<Photographer, Integer> {

    @Modifying(clearAutomatically = true)
    @Query("update Photographer p set p.aboutMyself= :aboutMyself where p.id= :id")
    void updateAboutMySelf(@Param(value = "aboutMyself") String aboutMySelf, @Param(value = "id") int id);

    @Modifying(clearAutomatically = true)
    @Query("update Photographer p set p.email= :email where p.id= :id")
    void updateEmail(@Param(value = "email") String email, @Param(value = "id") int id);

    @Modifying(clearAutomatically = true)
    @Query("update Photographer p set p.phone= :phone where p.id= :id")
    void updatePhone(@Param(value = "phone") String aboutMySelf, @Param(value = "id") int id);
}
