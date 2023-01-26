public class Card {
    private String color;
    private boolean visible;
    private String name;

    public Card(boolean visibility, String color, String resource) {
        this.visible = visibility;
        this.color = color;
        this.name = resource;
    }

    public void setVisibility(boolean value) {
        this.visible = value;
    }

    public String getColor() {
        return this.color;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public String getName() {
        return this.name;
    }
}
