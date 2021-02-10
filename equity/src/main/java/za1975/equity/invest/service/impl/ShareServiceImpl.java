package za1975.equity.invest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import za1975.equity.invest.entity.Share;
import za1975.equity.invest.repository.ShareRepositor;
import za1975.equity.invest.service.ShareService;

@Service
public class ShareServiceImpl implements ShareService {

	@Autowired
	private ShareRepositor shareRepositor;
	
	

	@Override
	public Page<Share> getAllSharesByUserId(String userId,Pageable pageable,Sort sort)
	{
		
		return shareRepositor.findAllByCreatedBy(userId,pageable);
	}



	@Override
	public Optional<Share> getShareDetailsById(Long id) {
		
		return shareRepositor.findById(id);
	}
}
