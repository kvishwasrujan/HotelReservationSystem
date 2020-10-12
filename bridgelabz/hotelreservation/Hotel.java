package com.bridgelabz.hotelreservation;

public class Hotel {
	// variables
	private String hotelName;
	private int weekdayRegularCustRate;
	private int weekendRegularCustRate;
	private int weekdayRewardCustRate;
	private int weekendRewardCustRate;
	private long totalRate;
	private int rating;

	/**
	 * @param hotelName
	 * @param weekdayRegularCustRate
	 * @param weekendRegularCustRate
	 * @param rating
	 * @param weekdayRewardCustRate
	 * @param weekendRewardCustRate
	 */
	public Hotel(String hotelName, int weekdayRegularCustRate, int weekendRegularCustRate, int rating,
			int weekdayRewardCustRate, int weekendRewardCustRate) {
		this.hotelName = hotelName;
		this.weekdayRegularCustRate = weekdayRegularCustRate;
		this.weekendRegularCustRate = weekendRegularCustRate;
		this.rating = rating;
		this.weekdayRewardCustRate = weekdayRewardCustRate;
		this.weekendRewardCustRate = weekendRewardCustRate;
	}

	public String getHotelName() {
		return hotelName;
	}

	@Override
	public String toString() {
		return "Hotel Name - " + hotelName;
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getWeekdayRewardCustRate() {
		return weekdayRewardCustRate;
	}

	public void setWeekdayRewardCustRate(int weekdayRewardCustRate) {
		this.weekdayRewardCustRate = weekdayRewardCustRate;
	}

	public int getWeekendRewardCustRate() {
		return weekendRewardCustRate;
	}

	public void setWeekendRewardCustRate(int weekendRewardCustRate) {
		this.weekendRewardCustRate = weekendRewardCustRate;
	}

}
