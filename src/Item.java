abstract class Item {
    String name;
    int price;
    Item(String name, int price){
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName(String name) {
        return name;
    }
    public int getPrice(int price) {
        return price;
    }
    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

class EmptyItem extends Item {
    EmptyItem(){
        super("empty",-1);
    }
}

class Coffee extends Item {
    Coffee(String name, int price){
        super(name,price);
    }
    public static Coffee getDefaultCoffee(){
        return new Coffee("coffee",50);
    }
}

class Can extends Item {
    Can(String name, int price){
        super(name,price);
    }

    public static Can getDefaultCan(){
        return new Can("can",100);
    }
}
