
package gesthiper;

public class Produtos {
    
    private String id;

    public Produtos() {
    }

    public Produtos(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Produtos{" + "id=" + id + '}';
    }
    
    
}
