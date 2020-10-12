package com.bridgelabz.hotelreservation;
import java.util.ArrayList;
import java.text.ParseException;
import java.util.Comparator;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.List;
public class HotelReservation {
private List<Hotel> hotelList = new ArrayList<Hotel>();
	
	/**
	 * @param hotelName
	 * @param weekdayRegularCustRate, int weekendRegularCustRate
	 * @return true if hotel is added
	 */
	public boolean addHotel(String hotelName, int weekdayRegularCustRate,int weekendRegularCustRate) {
		Hotel hotel = new Hotel(hotelName,weekdayRegularCustRate,weekendRegularCustRate);
		hotelList.add(hotel);
		return true;
	}
	
	/**
	 * @param start
	 * @param end
	 * @return Cheapest hotel (UC2)
	 */
	public Hotel findCheapestHotel(String start, String end) {
		Date startDate=null;
        Date endDate = null;
        try {
			startDate = new SimpleDateFormat("dd-MMM-yyyy").parse(start);
			endDate = new SimpleDateFormat("dd-MMM-yyyy").parse(end); 
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
        long noOfDays = 1+(endDate.getTime()- startDate.getTime())/1000/60/60/24;
        
        Hotel cheapestHotel = hotelList.stream().sorted(Comparator.comparing(Hotel::getWeekdayRegularCustRate)).findFirst().orElse(null);
        long totalRate = noOfDays*cheapestHotel.getWeekdayRegularCustRate();
        cheapestHotel.setTotalRate(totalRate);
        
		return cheapestHotel;
	}
	
    /**
     * UC3
     * @param args
     */
    public static void main( String[] args )
    {
    	Scanner sc = new Scanner(System.in);
    	HotelReservation hotelReservation = new HotelReservation();
    	sc.close();
        System.out.println( "Welcome to Hotel Reservation System Program" );
        
        hotelReservation.addHotel("Lakewood", 110, 90);
        hotelReservation.addHotel("Bridgewood", 160, 60);
        hotelReservation.addHotel("Ridgewood", 220, 150);
        
        System.out.println("Do you want to add a Hotel?(Y/N)");
        char choice = sc.nextLine().charAt(0);
        while(choice=='Y') {
	        System.out.println("Add a hotel \nEnter hotel name:");
	        String hotelName = sc.nextLine();
	        System.out.println("Enter weekday regular customer rate:");
	        int weekdayRegularCustRate = Integer.parseInt(sc.nextLine());
	        System.out.println("Enter weekend regular customer rate:");
	        int weekendRegularCustRate = Integer.parseInt(sc.nextLine());
	        hotelReservation.addHotel(hotelName,weekdayRegularCustRate,weekendRegularCustRate);
	        System.out.println("Do you want to add another Hotel?(Y/N)");
	        choice = sc.nextLine().charAt(0);
        }
        
        System.out.println("Enter date range to find hotel in format(dd-MMM-yyyy)");
        System.out.println("Enter start date");
        String start = sc.nextLine();
        System.out.println("Enter end date");
        String end = sc.nextLine();
        Hotel cheapestHotel = hotelReservation.findCheapestHotel(start,end);
        System.out.println(cheapestHotel.getHotelName()+", Total rates :$"+cheapestHotel.getTotalRate());
    }
}
