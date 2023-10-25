public class Customers {
    private String name;
    private Address address;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Customers(String name, Address address) {
        this.name = name;
        this.address = address;
    }
    public StringBuilder getFormattedCustomer()
    {
        StringBuilder res = new StringBuilder(name + "\n");
        res.append(address.getStreet() + "\n" +
                address.getCity() + ", " + address.getState() + " " + address.getZip());
        return res;
    }

}