package metroPackage;

 public class Station {
    private String line;
    private String name;
    private boolean hasConnection;
    private float depth;
    private String date;

    public Station(String name, String line,String date, float depth , boolean hasConnection) {
        this.line = line;
        this.name = name;
        this.hasConnection = hasConnection;
        this.depth = depth;
        this.date = date;
    }
    public Station(){

    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getHasConnection() {
        return hasConnection;
    }

    public void setHasConnection(boolean hasConnection) {
        this.hasConnection = hasConnection;
    }

     public float getDepth() {
         return depth;
     }

     public void setDepth(float depth) {
         this.depth = depth;
     }

     public String getDate() {
         return date;
     }

     public void setDate(String date) {
         this.date = date;
     }

     @Override
    public String toString() {
        return  "Name: " + getName() + "\n"
                + "Line: " + getLine() + "\n"
                + "Connection: " + getHasConnection() + "\n"
                + "Depth: " + getDepth() + "\n"
                + "Date: " + getDate() + "\n\n";
    }
}
