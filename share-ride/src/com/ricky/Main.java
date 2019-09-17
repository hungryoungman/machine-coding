package com.ricky;

import com.ricky.service.RideService;

/**
 * Design offerRide and shareRide mechanism on basis of fastest and earliest ride
 *
 * <p>Create a application that will add user and add their vehicle, there will be two categories
 * one who is offering ride and other who is selecting ride.
 *
 * <p>In offer ride(user_name, vehicle_no, origin, destination, start_time, duration)
 *
 * <p>select_ride(user_name, origin, destination, parameter)
 *
 * <p>parameter is fastest ride which will be on shortest duration and earliest ride will be lesser
 * start_time+duration.
 */
public class Main {
  public static void main(String[] args) {
    RideService rideService = RideService.getInstance();
    System.out.println(rideService.selectRide(1, 0, 1, SearchType.FASTEST));
    rideService.offerRide(1, 2, 0, 1, 1L, 5L);
    rideService.offerRide(2, 3, 0, 1, 3L, 2L);
    rideService.offerRide(3, 3, 0, 1, 1L, 2L);
    System.out.println(rideService.selectRide(2, 0, 1, SearchType.FASTEST));
    System.out.println(rideService.selectRide(2, 0, 1, SearchType.EARLIEST));
  }
}
