package cinema.statistic;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Statistic {
    private AtomicInteger currentIncome;
    private AtomicInteger numberOfAvailableSeats;
    private AtomicInteger numberOfPurchasedTickets;

    public Statistic() {}

    public Statistic(int numberOfSeats) {
        currentIncome = new AtomicInteger(0);
        numberOfAvailableSeats = new AtomicInteger(numberOfSeats);
        numberOfPurchasedTickets = new AtomicInteger(0);
    }

    public Map<String, Integer> toMap() {
        return Map.of(
                "current_income", currentIncome.get(),
                "number_of_available_seats", numberOfAvailableSeats.get(),
                "number_of_purchased_tickets", numberOfPurchasedTickets.get()
        );
    }

    public int currentIncomeAdd(int income) {
        return currentIncome.addAndGet(income);
    }

    public int currentIncomeRemove(int income) {
        return currentIncomeAdd(-income);
    }

    public int availableSeatsAdd(int number) {
        return numberOfAvailableSeats.addAndGet(number);
    }

    public int availableSeatsRemove(int number) {
        return availableSeatsAdd(-number);
    }

    public int ticketsIncrement() {
        return numberOfPurchasedTickets.incrementAndGet();
    }

    public int ticketsDecrement() {
        return numberOfPurchasedTickets.decrementAndGet();
    }
}
