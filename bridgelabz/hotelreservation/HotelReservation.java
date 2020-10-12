package com.bridgelabz.hotelreservation;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class HotelReservation {
private List<Hotel> hotelList = new ArrayList<Hotel>();
	
	/**
	 * @param hotelName
	 * @param regularCustRate
	 * @return
	 */
	public boolean addHotel(String hotelName, int regularCustRate) {
		Hotel hotel = new Hotel(hotelName,regularCustRate);
		hotelList.add(hotel);
		return true;
	}
	
    public static void main( String[] args )
    {
    	Scanner sc = new Scanner(System.in);
    	HotelReservation hotelReservation = new HotelReservation();
    	
        System.out.println( "Welcome to Hotel Reservation System Program" );
        System.out.println("Add hotel,Enter hotel name:");
        String hotelName = sc.nextLine();
        System.out.println("Enter regular customer rate:");
        int regularCustRate = Integer.parseInt(sc.nextLine());
        hotelReservation.addHotel(hotelName,regularCustRate);
        sc.close();
    }
}
