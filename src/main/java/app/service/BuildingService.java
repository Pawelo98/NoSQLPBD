package app.service;

import java.util.List;

import app.entity.Building;

public interface BuildingService {
	public List<Building> getBuildings(int theId);

	public void saveBuilding(Building building);
	public void addBuilding(Building building);

	public Building getBuilding(int theId);

	public void deleteBuilding(int theId);
}
