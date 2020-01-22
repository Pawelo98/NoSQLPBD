package app.dao;

import java.util.List;

import app.entity.Building;

public interface BuildingDAO {
	
	public List<Building> getBuildings(int theId);

	public void saveBuilding(Building building);

	public Building getBuilding(int theId);

	public void deleteBuilding(int theId);

	public void addBuilding(Building building);
}
