package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dao.AuthorityDAO;
import app.entity.Authority;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	
	@Autowired
	AuthorityDAO authorityDAO;

	@Override
	@Transactional
	public void saveAuthority(Authority auth) {
		authorityDAO.saveAuthority(auth);
	}

}
