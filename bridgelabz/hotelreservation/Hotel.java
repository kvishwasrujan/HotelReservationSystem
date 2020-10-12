package com.bridgelabz.hotelreservation;

public class Hotel {
	// variables
	private String hotelName;
	private int weekdayRegularCustRate;
	private int weekendRegularCustRate;
	private long totalRate;
	private int rating;

	/**
	 * @param hotelName
	 * @param weekdayRegularCustRate
	 * @param weekendRegularCustRate
	 * @param rating
	 */
	public Hotel(String hotelName, int weekdayRegularCustRate, int weekendRegularCustRate, int rating) {
		this.hotelName = hotelName;
		this.weekdayRegularCustRate = weekdayRegularCustRate;
		this.weekendRegularCustRate = weekendRegularCustRate;
		this.rating = rating;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public int getWeekdayRegularCustRate() {
		return weekdayRegularCustRate;
	}

	public void setWeekdayRegularCustRate(int weekdayRegularCustRate) {
		this.weekdayRegularCustRate = weekdayRegularCustRate;
	}

	public int getWeekendRegularCustRate() {
		return weekendRegularCustRate;
	}

	public void setWeekendRegularCustRate(int weekendRegularCustRate) {
		this.weekendRegularCustRate = weekendRegularCustRate;
	}

	public long getTotalRate() {
		return totalRate;
	}
	@Override
	public String toString() {
		return "Hotel Name - "+ hotelName;
	}

	public void setTotalRate(long totalRate) {
		this.totalRate = totalRate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
