package pen;

public class Pen {
    private String color = "BLUE";
    private int InkCounter = 100;

    public Pen(){
    }

    public Pen(String color){
        this.color=color;
    }

    public Pen(String color, int InkCounter){
        this.color=color;
        this.InkCounter=InkCounter;
    }

    public String getColor (){
        return color;
    }

    public String write(String wordtoWrite){
        return wordtoWrite;
    }
}