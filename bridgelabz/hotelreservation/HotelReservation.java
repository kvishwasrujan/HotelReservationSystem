package com.bridgelabz.hotelreservation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class HotelReservation {
/**
 * Hotel stored in List
 */
private List<Hotel> hotelList = new ArrayList<Hotel>();
	
	/**
	 * @param hotelName
	 * @param regularCustRate
	 * @return true if hotel is added
	 */
	public boolean addHotel(String hotelName, int regularCustRate) {
		Hotel hotel = new Hotel(hotelName,regularCustRate);
		hotelList.add(hotel);
		return true;
	}
	
	/**
	 * UC2
	 * @param start
	 * @param end
	 * @return Cheapest hotel 
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
        
        Hotel cheapestHotel = hotelList.stream().sorted(Comparator.comparing(Hotel::getRegularCustRate)).findFirst().orElse(null);
        long totalRate = noOfDays*cheapestHotel.getRegularCustRate();
        cheapestHotel.setTotalRate(totalRate);
        
		return cheapestHotel;
	}
	
    /**
     * @param args
     */
    public static void main( String[] args )
    {
    	Scanner sc = new Scanner(System.in);
    	HotelReservation hotelReservation = new HotelReservation();
    	sc.close();
    	
        System.out.println( "Welcome to Hotel Reservation" );
        
        hotelReservation.addHotel("Lakewood", 110);
        hotelReservation.addHotel("Bridgewood", 160);
        hotelReservation.addHotel("Ridgewood", 220);
        
        System.out.println("Do you want to add a Hotel?(Y/N)");
        char choice = sc.nextLine().charAt(0);
        while(choice=='Y') {
	        System.out.println("Add a hotel \nEnter hotel name:");
	        String hotelName = sc.nextLine();
	        System.out.println("Enter regular customer rate:");
	        int regularCustRate = Integer.parseInt(sc.nextLine());
	        hotelReservation.addHotel(hotelName,regularCustRate);
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
