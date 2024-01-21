package com.swapnil.BookMyShow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity(name = "booking")
public class Booking extends BaseModel{
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;

    @ManyToMany
    private List<ShowSeat> showSeats;

    @ManyToOne
    private User user;
    private Date bookedAt;

    @ManyToOne
    private Show show;
    private int amount;

    @OneToMany
    private List<Payment> payments;

}
