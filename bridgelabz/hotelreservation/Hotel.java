package com.bridgelabz.hotelreservation;


public class Hotel {
	//variables
	private String hotelName;
	private int weekdayRegularCustRate;
	private int weekendRegularCustRate;
	private long totalRate;

	/**
	 * UC3
	 * @param hotelName
	 * @param weekdayRegularCustRate
	 * @param weekendRegularCustRate
	 */
	public Hotel(String hotelName, int weekdayRegularCustRate, int weekendRegularCustRate) {
		this.hotelName = hotelName;
		this.weekdayRegularCustRate = weekdayRegularCustRate;
		this.weekendRegularCustRate = weekendRegularCustRate;
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

	public void setTotalRate(long totalRate) {
		this.totalRate = totalRate;
	}
}
