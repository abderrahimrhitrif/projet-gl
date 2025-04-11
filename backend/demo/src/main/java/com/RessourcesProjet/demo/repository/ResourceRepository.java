package com.RessourcesProjet.demo.repository;

import com.RessourcesProjet.demo.entity.Resource;
import com.RessourcesProjet.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
