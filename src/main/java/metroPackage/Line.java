package metroPackage;

 class Line {
    private String lineName;
    private String number;

    public Line(String lineName, String number) {
        this.lineName = lineName;
        this.number = number;
    }
    public Line(){

    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

     public String getNumber() {
         return number;
     }

     public void setNumber(String number) {
         this.number = number;
     }

     @Override
    public String toString() {
        return getLineName() + " " + getNumber();
    }
}
