package com.example.sirenalert24.Repository;


import com.example.sirenalert24.Model.Fire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FireRepository extends JpaRepository<Fire, Long> {
    List<Fire> findByStatus(String status);
}
