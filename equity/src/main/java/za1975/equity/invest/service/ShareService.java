package za1975.equity.invest.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import za1975.equity.invest.entity.Share;

public interface ShareService {

	Page<Share> getAllSharesByUserId(String userId, Pageable pageable, Sort sort);

	Optional<Share> getShareDetailsById(Long id);

}
