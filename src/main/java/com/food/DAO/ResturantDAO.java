package com.food.DAO;

import java.util.List;

import com.food.model.Resturant;

public interface ResturantDAO {
	void addResturant(Resturant resturant);
	Resturant getResturant(int resurantId);
	void UpdateResturant(Resturant resturant);
	void DeleteResturant(int resturantId);
	List <Resturant> getAllResturant();

}
