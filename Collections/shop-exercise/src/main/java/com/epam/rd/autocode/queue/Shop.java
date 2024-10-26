package com.epam.rd.autocode.queue;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    private final int cashBoxCount;
    private final List<CashBox> cashBoxes;

    public Shop(int count) {
        this.cashBoxCount = count;
        this.cashBoxes = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            cashBoxes.add(new CashBox(i));
        }
    }

    public int getCashBoxCount() {
        return cashBoxCount;
    }

    // Get total number of buyers across all cashboxes
    private static int getTotalBuyersCount(List<CashBox> cashBoxes) {
        int totalBuyers = 0;
        for (CashBox cashBox : cashBoxes) {
            totalBuyers += cashBox.getQueue().size();
        }
        return totalBuyers;
    }

    // Add buyer to the cashbox with the smallest queue
    public void addBuyer(Buyer buyer) {
        CashBox minQueueCashBox = null;
        int minQueueSize = Integer.MAX_VALUE;

        for (CashBox cashBox : cashBoxes) {
            if (cashBox.inState(CashBox.State.ENABLED) && cashBox.getQueue().size() < minQueueSize) {
                minQueueCashBox = cashBox;
                minQueueSize = cashBox.getQueue().size();
            }
        }

        if (minQueueCashBox != null) {
            minQueueCashBox.addLast(buyer);
        }
    }

    // Perform one tact: serve buyers and redistribute them if necessary
    public void tact() {
        List<Buyer> defectorBuyers = new ArrayList<>();

        // Serve buyers and move cashboxes from IS_CLOSING to DISABLED if necessary
        for (CashBox cashBox : cashBoxes) {
            if (!cashBox.getQueue().isEmpty()) {
                cashBox.serveBuyer();
                if (cashBox.inState(CashBox.State.IS_CLOSING) && cashBox.getQueue().isEmpty()) {
                    cashBox.setState(CashBox.State.DISABLED);
                }
            }
        }

        // Collect defectors (buyers leaving queues for balancing)
        for (CashBox cashBox : cashBoxes) {
            if (cashBox.inState(CashBox.State.ENABLED)) {
                int maxQueueSize = getMinMaxSize(cashBoxes)[1];
                while (cashBox.getQueue().size() > maxQueueSize) {
                    defectorBuyers.add(cashBox.removeLast());
                }
            }
        }

        // Redistribute defectors to balance queues
        for (CashBox cashBox : cashBoxes) {
            int minQueueSize = getMinMaxSize(cashBoxes)[0];
            while (cashBox.inState(CashBox.State.ENABLED) && cashBox.getQueue().size() < minQueueSize
                    && !defectorBuyers.isEmpty()) {
                cashBox.addLast(defectorBuyers.remove(0));
            }
        }
    }

    // Calculate the minimum and maximum queue sizes after balancing
    public static int[] getMinMaxSize(List<CashBox> cashBoxes) {
        int enabledCount = 0;
        for (CashBox cashBox : cashBoxes) {
            if (cashBox.inState(CashBox.State.ENABLED)) {
                enabledCount++;
            }
        }
        
        if (enabledCount == 0) return new int[]{0, 0};

        int totalBuyers = getTotalBuyersCount(cashBoxes);
        int minQueueSize = totalBuyers / enabledCount;
        int maxQueueSize = minQueueSize + (totalBuyers % enabledCount > 0 ? 1 : 0);

        return new int[]{minQueueSize, maxQueueSize};
    }

    // Set the state of a specific cashbox by number
    public void setCashBoxState(int cashBoxNumber, CashBox.State state) {
        CashBox cashBox = getCashBox(cashBoxNumber);
        if (cashBox != null) {
            cashBox.setState(state);
        }
    }

    // Get a cashbox by its number
    public CashBox getCashBox(int cashBoxNumber) {
        return (cashBoxNumber >= 0 && cashBoxNumber < cashBoxCount) ? cashBoxes.get(cashBoxNumber) : null;
    }

    // Print all cashboxes in the required format
    public void print() {
        for (CashBox cashBox : cashBoxes) {
            System.out.println(cashBox.toString());
        }
        System.out.println("==============");
    }
}
