package com.project.leadmanagement.repositories;

import com.project.leadmanagement.dto.KAMInfoDTO;
import com.project.leadmanagement.models.KAM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;

public interface KAMRepository extends JpaRepository<KAM, Long> {
    @Query("SELECT new com.project.leadmanagement.dto.KAMInfoDTO(" +
            "k.id, k.name, k.email, " +
            "(SELECT r.id FROM Restaurant r WHERE r.kam.id = k.id)) " +
            "FROM KAM k " +
            "WHERE k.id = :kamId")

    Optional<KAMInfoDTO> getKAMInfoById(@Param("kamId") Long kamId);

}

