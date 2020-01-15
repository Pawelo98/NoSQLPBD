package app.service;

import java.util.List;

import app.entity.Referee;

public interface RefereeService {
	
	public List<Referee> getReferees();

	public void saveReferee(Referee ref);
	
	public Referee getReferee(int theId);

	public void deleteReferee(int theId);
}
