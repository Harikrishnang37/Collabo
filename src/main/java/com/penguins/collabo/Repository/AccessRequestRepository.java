package com.penguins.collabo.Repository;

import com.penguins.collabo.models.AccessRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessRequestRepository extends JpaRepository<AccessRequest, Integer> {
    Optional<AccessRequest> findById(Integer id);
}
