package cinema.cinema;

import cinema.statistic.Statistic;
import cinema.statistic.WrongPasswordException;
import cinema.ticket.Ticket;
import cinema.ticket.WrongTokenException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Cinema implements Iterable<Seat> {

    private int total_rows;
    private int total_columns;

    private String adminPassword = "super_secret";

    Statistic statistic;

    CopyOnWriteArrayList<Seat> seats;
    CopyOnWriteArrayList<Ticket> tickets;

    public Cinema(int total_rows, int total_columns) {
        this.seats = new CopyOnWriteArrayList<>();
        this.tickets = new CopyOnWriteArrayList<>();
        this.statistic = new Statistic(total_rows * total_columns);
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        for (int column = 1; column <= total_columns; column++)
            for (int row = 1; row <= total_rows; row++)
                this.seats.add(new Seat(row, column, row <= 4 ? 10 : 8));
    }

    public Map<String, Integer> getStatistic(String password) {
        if (adminPassword.equals(password)) return statistic.toMap();
        throw new WrongPasswordException("The password is wrong!");
    }

    public Ticket purchaseSeat(Seat seat) {
        if (seat.getColumn() > total_columns || seat.getColumn() < 1 || seat.getRow() > total_rows || seat.getRow() < 1)
            throw new IllegalSeatParamException("The number of a row or a column is out of bounds!");
        for (Seat s : seats) {
            if (s.equals(seat) && s.isAvailable()) {
                s.setAvailable(false);
                Ticket ticket = Ticket.generateTicket(s);
                tickets.add(ticket);
                statistic.ticketsIncrement();
                statistic.availableSeatsRemove(1);
                statistic.currentIncomeAdd(s.getPrice());
                return ticket;
            }
        }
        throw new IllegalSeatParamException("The ticket has been already purchased!");
    }

    public Ticket returnTicket(UUID token) {
        for (Ticket ticket : tickets) {
            if (ticket.equalsToken(token)) {
                tickets.remove(ticket);
                ticket.getTicket().setAvailable(true);
                statistic.ticketsDecrement();
                statistic.availableSeatsAdd(1);
                statistic.currentIncomeRemove(ticket.getTicket().getPrice());
                return ticket;
            }
        }
        throw new WrongTokenException("Wrong token!");
    }

    public Iterator<Seat> iterator() {
        return seats.iterator();
    }

    public List<Seat> getAvailable_seats() {
        return seats.stream().filter(Seat::isAvailable).collect(Collectors.toList());
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }
}