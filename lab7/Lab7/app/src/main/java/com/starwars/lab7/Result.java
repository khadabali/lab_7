package com.starwars.lab7;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Result implements Serializable {

	@SerializedName("name")
	private String name;

	@SerializedName("height")
	private String height;

	@SerializedName("mass")
	private String mass;

	@SerializedName("hair_color")
	private String hairColor;

	@SerializedName("skin_color")
	private String skinColor;

	@SerializedName("eye_color")
	private String eyeColor;

	@SerializedName("birth_year")
	private String birthYear;

	@SerializedName("gender")
	private String gender;

	@SerializedName("homeworld")
	private String homeworld;

	@SerializedName("films")
	private List<String> films;

	@SerializedName("species")
	private List<Object> species;

	@SerializedName("vehicles")
	private List<String> vehicles;

	@SerializedName("starships")
	private List<String> starships;

	@SerializedName("created")
	private String created;

	@SerializedName("edited")
	private String edited;

	@SerializedName("url")
	private String url;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setHeight(String height){
		this.height = height;
	}

	public String getHeight(){
		return height;
	}

	public void setMass(String mass){
		this.mass = mass;
	}

	public String getMass(){
		return mass;
	}

	public void setHairColor(String hairColor){
		this.hairColor = hairColor;
	}

	public String getHairColor(){
		return hairColor;
	}

	public void setSkinColor(String skinColor){
		this.skinColor = skinColor;
	}

	public String getSkinColor(){
		return skinColor;
	}

	public void setEyeColor(String eyeColor){
		this.eyeColor = eyeColor;
	}

	public String getEyeColor(){
		return eyeColor;
	}

	public void setBirthYear(String birthYear){
		this.birthYear = birthYear;
	}

	public String getBirthYear(){
		return birthYear;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setHomeworld(String homeworld){
		this.homeworld = homeworld;
	}

	public String getHomeworld(){
		return homeworld;
	}

	public void setFilms(List<String> films){
		this.films = films;
	}

	public List<String> getFilms(){
		return films;
	}

	public void setSpecies(List<Object> species){
		this.species = species;
	}

	public List<Object> getSpecies(){
		return species;
	}

	public void setVehicles(List<String> vehicles){
		this.vehicles = vehicles;
	}

	public List<String> getVehicles(){
		return vehicles;
	}

	public void setStarships(List<String> starships){
		this.starships = starships;
	}

	public List<String> getStarships(){
		return starships;
	}

	public void setCreated(String created){
		this.created = created;
	}

	public String getCreated(){
		return created;
	}

	public void setEdited(String edited){
		this.edited = edited;
	}

	public String getEdited(){
		return edited;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}
}