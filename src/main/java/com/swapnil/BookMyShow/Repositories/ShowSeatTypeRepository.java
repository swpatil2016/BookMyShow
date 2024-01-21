package com.swapnil.BookMyShow.Repositories;

import com.swapnil.BookMyShow.models.Show;
import com.swapnil.BookMyShow.models.ShowSeat;
import com.swapnil.BookMyShow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {

    List<ShowSeatType> findAllByShow(Show show);
}
