package app.dao;

import java.util.List;

import app.entity.Referee;

public interface RefereeDAO {

	public List<Referee> getReferees();

	public void saveReferee(Referee referee);
	public void updateReferee(Referee referee);

	public Referee getReferee(int theId);

	public void deleteReferee(int theId);
	
	public Referee findRefereeById(int theId);
	public List<Referee> findAllReferees();

	//void addReferee(Referee referee);
}
