package hotel.domain;

public class HotelRoom {
    private int id;
    private String name;
    private float price;
    private int bedsNo;
    private String type;

    public HotelRoom(int id, String name, float price, int bedsNo, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.bedsNo = bedsNo;
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setBedsNo(int bedsNo) {
        this.bedsNo = bedsNo;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getBedsNo() {
        return bedsNo;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "HotelRoom {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", bedsNo=" + bedsNo +
                ", type='" + type + '\'' +
                '}';
    }
}
