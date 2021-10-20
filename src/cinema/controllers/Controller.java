package cinema.controllers;

import cinema.cinema.Cinema;
import cinema.cinema.Seat;
import cinema.ticket.ReturnedTicket;
import cinema.ticket.Ticket;
import cinema.ticket.TokenStringWrapper;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
public class Controller {

    Cinema cinema = new Cinema(9, 9);

    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinema;
    }

    @PostMapping("/purchase")
    public Ticket purchaseSeat(@RequestBody Seat seat) {
        return cinema.purchaseSeat(seat);
    }

    @PostMapping("/return")
    public ReturnedTicket returnTicket(@RequestBody TokenStringWrapper tokenStringWrapper) {
        return new ReturnedTicket(cinema.returnTicket(UUID.fromString(tokenStringWrapper.getToken())).getTicket());
    }

    @PostMapping("/stats")
    public Map<String, Integer> getStatistic(@Nullable @RequestParam String password) {
        return cinema.getStatistic(password);
    }
}