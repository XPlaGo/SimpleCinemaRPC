package cinema.ticket;

import cinema.cinema.Seat;

import java.util.UUID;

public class Ticket {

    private UUID token;
    private Seat ticket;

    public Ticket() {}

    public Ticket(UUID token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public String getToken() {
        return token.toString();
    }

    public Seat getTicket() {
        return ticket;
    }

    public static Ticket generateTicket(Seat seat) {
        UUID token = UUID.randomUUID();
        return new Ticket(token, seat);
    }

    public boolean equalsToken(UUID otherToken) {
        return token.equals(otherToken);
    }
}