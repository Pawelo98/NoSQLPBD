package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dao.BuildingDAO;
import app.entity.Building;

@Service
public class BuildingServiceImpl implements BuildingService {
	
	@Autowired
	private BuildingDAO buildingDAO;

	@Override
	@Transactional
	public List<Building> getBuildings(int theId) {
		return buildingDAO.getBuildings(theId);
	}

	@Override
	@Transactional
	public void saveBuilding(Building building) {
		buildingDAO.saveBuilding(building);
	}
	
	@Override
	@Transactional
	public void addBuilding(Building building) {
		buildingDAO.addBuilding(building);
	}

	@Override
	@Transactional
	public Building getBuilding(int theId) {
		return buildingDAO.getBuilding(theId);
	}

	@Override
	@Transactional
	public void deleteBuilding(int theId) {
		buildingDAO.deleteBuilding(theId);
		
	}

	

}
