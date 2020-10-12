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
	private List<Hotel> hotelList1 = new ArrayList<Hotel>();
private List<Hotel> hotelList = new ArrayList<Hotel>();
	
	
	/**
	 * @param hotelName
	 * @param weekdayRegularCustRate
	 * @param weekendRegularCustRate
	 * @param rating
	 * @return
	 */
	public boolean addHotel(String hotelName, int weekdayRegularCustRate,int weekendRegularCustRate,int rating) {
		Hotel hotel = new Hotel(hotelName,weekdayRegularCustRate,weekendRegularCustRate,rating);
		hotelList1.add(hotel);
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
	 * @return noOfDays between start and end date
	 */
	public long getNoOfDays(String startD,String endD) {
		Date startDate=null;
        Date endDate = null;
        startDate = parseDate(startD);
        endDate = parseDate(endD); 
        if(startDate.getTime()<endDate.getTime()) {
	        long noOfDays = 1+(endDate.getTime()- startDate.getTime())/1000/60/60/24;
			return noOfDays;
        }
        else 
        	return 0;
	}
	
	/**
	 * @param startD
	 * @param endD
	 * @return noOfWeekdays between start and end date
	 */
	public long getNoOfWeekdays(String startD, String endD) {
		Date startDate=null;
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
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++noOfWeekdays;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()); 
        return noOfWeekdays;
        }
        else 
        	return 0;
	}
	
	/**
	 * @param noOfWeekdays
	 * @param noOfWeekends
	 * Sets total rate for each hotel
	 */
	public void setTotalRateForHotels(long noOfWeekdays,long noOfWeekends) {
		for(Hotel hotel: hotelList1) {
        	long totalRate = noOfWeekdays*hotel.getWeekdayRegularCustRate()+noOfWeekends*hotel.getWeekendRegularCustRate();
        	hotel.setTotalRate(totalRate);
		}
	}
	
	/**
	 * @param start
	 * @param end
	 * @return Cheapest best rated hotel (UC6)
	 */
	public Hotel findCheapestBestRatedHotel(String startD, String endD) {
		long noOfWeekdays = getNoOfWeekdays(startD, endD);
		long noOfDays = getNoOfDays(startD, endD);
        long noOfWeekends = noOfDays - noOfWeekdays;
        
        if(noOfDays>0){
        	setTotalRateForHotels(noOfWeekdays,noOfWeekends);
	        List<Hotel> sortedHotelList = hotelList1.stream().sorted(Comparator.comparing(Hotel::getTotalRate)).collect(Collectors.toList());
	        
	        Hotel cheapestHotel = sortedHotelList.get(0);
	        long cheapestRate= sortedHotelList.get(0).getTotalRate();
	        for(Hotel hotel:sortedHotelList) {
	        	if(hotel.getTotalRate()<=cheapestRate) {
	        		if(hotel.getRating()>cheapestHotel.getRating())
	        			cheapestHotel = hotel;
	        	}
	        	else 
	        		break;
	        }
			return cheapestHotel;
	    }
        else
        	return null;
	}

	/**
	 * @param start
	 * @param end
	 * @return Best rated hotel
	 */
	public Hotel findBestRatedHotel(String startD, String endD) {
		long noOfWeekdays = getNoOfWeekdays(startD, endD);
		long noOfDays = getNoOfDays(startD, endD);
        long noOfWeekends = noOfDays - noOfWeekdays;
        
        if(noOfDays>0){
        	setTotalRateForHotels(noOfWeekdays,noOfWeekends);
	        List<Hotel> sortedHotelList = hotelList1.stream().sorted(Comparator.comparing(Hotel::getRating).reversed()).collect(Collectors.toList());
	        Hotel bestRatedHotel = sortedHotelList.get(0);
	        int bestHotelRating= sortedHotelList.get(0).getRating();
	        for(Hotel hotel:sortedHotelList) {
	        	if(hotel.getRating()>=bestHotelRating) {
	        		if(hotel.getTotalRate()<bestRatedHotel.getTotalRate())
	        			bestRatedHotel = hotel;
	        	}
	        	else 
	        		break;
	        }
			return bestRatedHotel;
	    }
        else
        	return null;
	}
	
    /**
     * @param args
     */
    public static void main( String[] args )
    {
    	Scanner sc = new Scanner(System.in);
    	HotelReservation hotelReservation = new HotelReservation();
    	sc.close();
    	
        System.out.println( "Welcome to Hotel Reservation System Program" );
        
        hotelReservation.addHotel("Lakewood", 110, 90, 3);
        hotelReservation.addHotel("Bridgewood", 160, 60, 4);
        hotelReservation.addHotel("Ridgewood", 220, 150, 5);
        
        
        System.out.println("Do you want to add a Hotel?(Y/N)");
        char addChoice = sc.nextLine().charAt(0);
        while(addChoice=='Y') {
	        System.out.println("Add a hotel \nEnter hotel name:");
	        String hotelName = sc.nextLine();
	        System.out.println("Enter weekday regular customer rate:");
	        int weekdayRegularCustRate = Integer.parseInt(sc.nextLine());
	        System.out.println("Enter weekend regular customer rate:");
	        int weekendRegularCustRate = Integer.parseInt(sc.nextLine());
	        System.out.println("Enter Rating");
	        int rating = Integer.parseInt(sc.nextLine());
	        hotelReservation.addHotel(hotelName,weekdayRegularCustRate,weekendRegularCustRate,rating);
	        System.out.println("Do you want to add another Hotel?(Y/N)");
	        addChoice = sc.nextLine().charAt(0);
        }
        System.out.println("Enter your choice: )Find the cheapest best rated hotel \n2) Find the best rated hotel ");
        int choice = Integer.parseInt(sc.nextLine());
        System.out.println("Enter date range to find hotel in format(dd-MMM-yyyy)");
        System.out.println("Enter start date");
        String start = sc.nextLine();
        System.out.println("Enter end date");
        String end = sc.nextLine();
        
        switch(choice) {
	        case 1:
		        Hotel cheapestHotel = hotelReservation.findCheapestBestRatedHotel(start,end);
		        if(cheapestHotel!=null)
		        	System.out.println(cheapestHotel.getHotelName()+", Rating: "+cheapestHotel.getRating()+", Total rate :$"+cheapestHotel.getTotalRate());
		        else 
		        	System.out.println("Improper dates entered");
		        break;
	        case 2:
	        	Hotel bestRatedHotel = hotelReservation.findBestRatedHotel(start,end);
	        	if(bestRatedHotel!=null)
		        	System.out.println(bestRatedHotel.getHotelName()+", Rating: "+bestRatedHotel.getRating()+", Total rate :$"+bestRatedHotel.getTotalRate());
		        else 
		        	System.out.println("Improper dates");
		        break;
	   }
    }
}
