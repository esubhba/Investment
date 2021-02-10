package za1975.equity.invest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import za1975.equity.invest.entity.StockDetails;
import za1975.equity.invest.model.StockSummery;

@Repository
public interface StockRepository extends JpaRepository<StockDetails, Long> {

	
	@Query("select new za1975.equity.invest.model.StockSummery("
			+ "sh.code,"
			+ "sh.exchangeName,"
			+ "sum(s1.quantity)-COALESCE(sum(s2.quantity),0),"
			+ "avg(s1.price),"
			+ "sum(s1.quantity),"
			+ "((sum(COALESCE(s2.quantity,0))*avg(COALESCE(s2.price,0)))-(sum(s1.quantity)*avg(s1.price)))"
			+ ") from StockDetails s1 "
			+ "inner join s1.share sh "
			+ "left join sh.stocks s2 with s2.type='Sell' and s2.type!=s1.type"
			+ " where sh.id = :shareId group by sh.code, sh.exchangeName")
	public StockSummery findSummeryDetails(@Param("shareId")Long shareId);

	public Page<StockDetails> findAllByShareId(Long shareId, Pageable pageable);
}
