package za1975.equity.invest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za1975.equity.invest.entity.Share;

@Repository
public interface ShareRepositor extends JpaRepository<Share, Long> {

}
