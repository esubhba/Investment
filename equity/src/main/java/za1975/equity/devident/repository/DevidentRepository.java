package za1975.equity.devident.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za1975.equity.devident.entity.Devident;

@Repository
public interface DevidentRepository extends JpaRepository<Devident, Long> {

}
