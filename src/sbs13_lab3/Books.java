package sbs13_lab3;

public class Books {

    int id;            
    String name;  
    String publisher;

    public Books() {
    }

    public Books(String name, String publisher) {
        this.name = name;
        this.publisher = publisher;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }
    
    @Override
    public String toString() {
        return String.format("Название = %s, Издатель = %s", name, publisher);
    }
}
