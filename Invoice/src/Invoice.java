import java.util.ArrayList;

public class Invoice {
    private Customers customer;
    private double amountDue;
    private ArrayList<LineItems> itemList = new ArrayList<>();

    public Invoice(Customers customer) {
        this.customer = customer;
    }
    public void calcProduct(Products product, int prodQuantity)
    {
        LineItems totalItem = new LineItems(product, prodQuantity);
        itemList.add(totalItem);
    }

    public String formatDisplay()
    {
        String res = String.format("%30s", "INVOICE\n")
                + customer.getFormattedCustomer() +
                "\n==================================================\n";

        res += "Item\t\t\tQty\tPrice\tTotal\n";

        for (LineItems item : itemList)
        {
            res += item.formattedLineItem() + "\n";
        }

        res += "==================================================\n" +
                String.format("Amount Due:\t\t\t\t$%.2f", getAmountDue()) + "\n";

        return res;
    }

    public double getAmountDue()
    {
        double amountDue = 0;
        for (LineItems item : itemList) {
            amountDue += item.calLineTotal();
        }
        return amountDue;
    }
    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }
}