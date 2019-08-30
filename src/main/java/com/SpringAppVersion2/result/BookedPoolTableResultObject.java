package com.SpringAppVersion2.result;

public class BookedPoolTableResultObject extends PoolTableResultObject {
  String bookingDate;

  public int startTime;

  public int endTime;

  public BookedPoolTableResultObject() { }

  public int getStartTime() { return startTime; }

  public void setStartTime(int startTime) { this.startTime = startTime; }

  public int getEndTime() { return endTime; }

  public void setEndTime(int endTime) { this.endTime = endTime; }

  public String getBookingDate() { return bookingDate; }

  public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }

}
