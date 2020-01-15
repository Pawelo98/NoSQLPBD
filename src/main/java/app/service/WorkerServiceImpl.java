package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dao.WorkerDAO;
import app.entity.Building;
import app.entity.Worker;

@Service
public class WorkerServiceImpl implements WorkerService {
	
	@Autowired
	private WorkerDAO workerDAO;
	
	@Override
	@Transactional
	public List<Worker> getWorkers(int theId) {
		return workerDAO.getWorkers(theId);
	}

	@Override
	@Transactional
	public void saveWorker(Worker worker) {
		workerDAO.saveWorker(worker);
	}
	
	@Override
	@Transactional
	public Worker getWorker(int theId) {
		return workerDAO.getWorker(theId);
	}

	@Override
	@Transactional
	public void deleteWorker(int theId) {
		workerDAO.deleteWorker(theId);
		
	}

	@Override
	@Transactional
	public String getSumOfSalary(int theId) {
		return workerDAO.getSumOfSalary(theId);
		
	}
}
