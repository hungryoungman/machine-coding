package com.ricky.service;

import com.ricky.Ride;
import com.ricky.SearchType;
import java.util.Comparator;

public class ComparatorService {
  public static Comparator<Ride> getComparatorFor(SearchType searchType) {
    if (SearchType.FASTEST.equals(searchType)) {
      return getFastestComparator();
    } else if (SearchType.EARLIEST.equals(searchType)) {
      return getEarliestComparator();
    }
    return null;
  }

  private static Comparator<Ride> getEarliestComparator() {
    return new Comparator<Ride>() {
      @Override
      public int compare(Ride first, Ride second) {
        Long firstTime = first.getStartTime() + first.getDuration();
        Long secondTime = second.getStartTime() + second.getDuration();
        return (int) (firstTime - secondTime);
      }
    };
  }

  private static Comparator<Ride> getFastestComparator() {
    return new Comparator<Ride>() {
      @Override
      public int compare(Ride first, Ride second) {
        Long firstTime = first.getDuration();
        Long secondTime = second.getDuration();
        return (int) (firstTime - secondTime);
      }
    };
  }
}
