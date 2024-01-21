package com.swapnil.BookMyShow.Repositories;

import com.swapnil.BookMyShow.models.Show;
import com.swapnil.BookMyShow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    List<ShowSeat> findAllById(Iterable<Long> longs);

    ShowSeat save(ShowSeat showSeat); // method to save the changes in DB // 6 point of bookingService

}
