package com.ncedu.knownetimpl.repository;


import com.ncedu.knownetimpl.model.Cource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourceRepository extends JpaRepository<Cource, Long> {

    void deleteByName(String name);

    boolean existsByCource_name(String cource_name);

    List<Cource> findByCource_name(String cource_name);

    List<Cource> findByGroup(String group);
    
}
