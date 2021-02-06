package za1975.equity.invest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za1975.equity.invest.entity.Share;

@Repository
public interface ShareRepositor extends JpaRepository<Share, Long> {

	Page<Share> findAllByCreatedBy(String userId, Pageable pageable, Sort sort);
    
}
