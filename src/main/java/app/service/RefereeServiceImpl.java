package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dao.RefereeDAO;
import app.entity.Referee;

@Service
public class RefereeServiceImpl implements RefereeService {

	@Autowired
	private RefereeDAO refereeDAO;
	
	@Override
	@Transactional
	public List<Referee> getReferees() {
		return refereeDAO.getReferees();
	}

	@Override
	@Transactional
	public void saveReferee(Referee referee) {
		refereeDAO.saveReferee(referee);
	}

	@Override
	@Transactional
	public Referee getReferee(int theId) {
		
		return refereeDAO.getReferee(theId);
	}

	@Override
	@Transactional
	public void deleteReferee(int theId) {
		refereeDAO.deleteReferee(theId);
		
	}
	
	

}
