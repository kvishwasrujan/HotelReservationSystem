package com.bridgelabz.hotelreservation;

import java.util.ArrayList;
import java.text.ParseException;
import java.util.Comparator;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.util.stream.Collectors;

public class HotelReservation {
	private List<Hotel> hotelList = new ArrayList<Hotel>();

	/**
	 * @param hotelName
	 * @param weekdayRegularCustRate
	 * @param weekendRegularCustRate
	 * @param rating
	 * @param weekdayRewardCustRate
	 * @param weekendRewardCustRate
	 * @return
	 */
	public boolean addHotel(String hotelName, int weekdayRegularCustRate, int weekendRegularCustRate, int rating,
			int weekdayRewardCustRate, int weekendRewardCustRate) {
		Hotel hotel = new Hotel(hotelName, weekdayRegularCustRate, weekendRegularCustRate, rating,
				weekdayRewardCustRate, weekendRewardCustRate);
		hotelList.add(hotel);
		return true;
	}

	/**
	 * @param String date
	 * @return Date date
	 */
	public Date parseDate(String date) {
		try {
			return new SimpleDateFormat("dd-MMM-yyyy").parse(date);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * @param startD
	 * @param endD
	 * @return
	 */
	public long getNoOfDays(String startD, String endD) {
		Date startDate = null;
		Date endDate = null;
		startDate = parseDate(startD);
		endDate = parseDate(endD);
		if (startDate.getTime() < endDate.getTime()) {
			long noOfDays = 1 + (endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24;
			return noOfDays;
		} else
			return 0;
	}

	/**
	 * @param startD
	 * @param endD
	 * @return noOfWeekdays between start and end date
	 */
	public long getNoOfWeekdays(String startD, String endD) {
		Date startDate = null;
		Date endDate = null;
		startDate = parseDate(startD);
		endDate = parseDate(endD);
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		long noOfWeekdays = 0;
		if (startCal.getTimeInMillis() < endCal.getTimeInMillis()) {
			do {
				if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
						&& startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
					++noOfWeekdays;
				}
				startCal.add(Calendar.DAY_OF_MONTH, 1);
			} while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());
			return noOfWeekdays;
		} else
			return 0;
	}

	/**
	 * @param noOfWeekdays
	 * @param noOfWeekends Sets total rate for each hotel
	 */
	public void setTotalRateForHotels(long noOfWeekdays, long noOfWeekends, Customer customer) {
		if (customer.getCustomerType().equals("regular")) {
			for (Hotel hotel : hotelList) {
				long totalRate = noOfWeekdays * hotel.getWeekdayRegularCustRate()
						+ noOfWeekends * hotel.getWeekendRegularCustRate();
				hotel.setTotalRate(totalRate);
			}
		} else if (customer.getCustomerType().equals("reward")) {
			for (Hotel hotel : hotelList) {
				long totalRate = noOfWeekdays * hotel.getWeekdayRewardCustRate()
						+ noOfWeekends * hotel.getWeekendRewardCustRate();
				hotel.setTotalRate(totalRate);
			}
		}
	}

	/**
	 * @param startD
	 * @param endD
	 * @param customer
	 * @return
	 */
	public Hotel findCheapestBestRatedHotel(String startD, String endD, Customer customer) {
		long noOfWeekdays = getNoOfWeekdays(startD, endD);
		long noOfDays = getNoOfDays(startD, endD);
		long noOfWeekends = noOfDays - noOfWeekdays;

		if (noOfDays > 0) {
			setTotalRateForHotels(noOfWeekdays, noOfWeekends, customer);
			List<Hotel> sortedHotelList = hotelList.stream().sorted(Comparator.comparing(Hotel::getTotalRate))
					.collect(Collectors.toList());

			Hotel cheapestHotel = sortedHotelList.get(0);
			long cheapestRate = sortedHotelList.get(0).getTotalRate();
			for (Hotel hotel : sortedHotelList) {
				if (hotel.getTotalRate() <= cheapestRate) {
					if (hotel.getRating() > cheapestHotel.getRating())
						cheapestHotel = hotel;
				} else
					break;
			}
			return cheapestHotel;
		} else
			return null;
	}

	/**
	 * @param start
	 * @param end
	 * @return Best rated hotel
	 */
	public Hotel findBestRatedHotel(String startD, String endD, Customer customer) {
		long noOfWeekdays = getNoOfWeekdays(startD, endD);
		long noOfDays = getNoOfDays(startD, endD);
		long noOfWeekends = noOfDays - noOfWeekdays;

		if (noOfDays > 0) {
			setTotalRateForHotels(noOfWeekdays, noOfWeekends, customer);
			List<Hotel> sortedHotelList = hotelList.stream().sorted(Comparator.comparing(Hotel::getRating).reversed())
					.collect(Collectors.toList());
			Hotel bestRatedHotel = sortedHotelList.get(0);
			int bestHotelRating = sortedHotelList.get(0).getRating();
			for (Hotel hotel : sortedHotelList) {
				if (hotel.getRating() >= bestHotelRating) {
					if (hotel.getTotalRate() < bestRatedHotel.getTotalRate())
						bestRatedHotel = hotel;
				} else
					break;
			}
			return bestRatedHotel;
		} else
			return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HotelReservation hotelReservation = new HotelReservation();
		sc.close();
		Customer customer = new Customer();
		customer.setCustomerType("regular");

		System.out.println("Welcome to Hotel Reservation System Program");
		System.out.println("Enter 1 if you are a regular customer \nEnter 2 if you are reward customer");
		if (Integer.parseInt(sc.nextLine()) == 1) {
			customer.setCustomerType("regular");
		} else
			customer.setCustomerType("reward");

		hotelReservation.addHotel("Lakewood", 110, 90, 3, 80, 80);
		hotelReservation.addHotel("Bridgewood", 160, 60, 4, 110, 50);
		hotelReservation.addHotel("Ridgewood", 220, 150, 5, 100, 40);
		// initialize

		System.out.println("Do you want to add a Hotel?(Y/N)");
		char addChoice = sc.nextLine().charAt(0);
		while (addChoice == 'Y') {
			System.out.println("Add a hotel \nEnter hotel name:");
			String hotelName = sc.nextLine();
			System.out.println("Enter weekday regular customer rate:");
			int weekdayRegularCustRate = Integer.parseInt(sc.nextLine());
			System.out.println("Enter weekend regular customer rate:");
			int weekendRegularCustRate = Integer.parseInt(sc.nextLine());
			System.out.println("Enter Rating");
			int rating = Integer.parseInt(sc.nextLine());
			System.out.println("Enter weekday reward customer rate:");
			int weekdayRewardCustRate = Integer.parseInt(sc.nextLine());
			System.out.println("Enter weekend reward customer rate");
			int weekendRewardCustRate = Integer.parseInt(sc.nextLine());
			hotelReservation.addHotel(hotelName, weekdayRegularCustRate, weekendRegularCustRate, rating,
					weekdayRewardCustRate, weekendRewardCustRate);
			System.out.println("Do you want to add another Hotel?(Y/N)");
			addChoice = sc.nextLine().charAt(0);
		}
		System.out.println("Enter your choice: \n1)Find the cheapest best rated hotel \n2) Find the best rated hotel ");
		int choice = Integer.parseInt(sc.nextLine());
		System.out.println("Enter date range to find hotel in format(dd-MMM-yyyy)");
		System.out.println("Enter start date");
		String start = sc.nextLine();
		System.out.println("Enter end date");
		String end = sc.nextLine();

		switch (choice) {
		case 1:
			Hotel cheapestHotel = hotelReservation.findCheapestBestRatedHotel(start, end, customer);
			if (cheapestHotel != null)
				System.out.println(cheapestHotel.getHotelName() + ", Rating: " + cheapestHotel.getRating()
						+ ", Total rate :$" + cheapestHotel.getTotalRate());
			else
				System.out.println("Improper dates entered");
			break;
		case 2:
			Hotel bestRatedHotel = hotelReservation.findBestRatedHotel(start, end, customer);
			if (bestRatedHotel != null)
				System.out.println(bestRatedHotel.getHotelName() + ", Rating: " + bestRatedHotel.getRating()
						+ ", Total rate :$" + bestRatedHotel.getTotalRate());
			else
				System.out.println("Improper dates");
			break;
		}
	}
}
