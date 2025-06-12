package com.example.sirenalert24.Repository;


import com.example.sirenalert24.Model.Siren;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SirenRepository extends JpaRepository<Siren, Long> {
}

