package app.dao;

import java.util.List;

import app.entity.Worker;

public interface WorkerDAO {

	public List<Worker> getWorkers(int theId);

	public void saveWorker(Worker worker);

	public Worker getWorker(int theId);

	public void deleteWorker(int theId);

	public String getSumOfSalary(int theId);
	 
	
}
