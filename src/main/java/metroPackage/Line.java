package metroPackage;

 class Line implements Comparable<Line> {
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

     @Override
     public int hashCode() {
        return lineName.hashCode();
     }

     @Override
     public boolean equals(Object obj) {
        Line line = (Line)obj;
         return lineName.equals(line.lineName);
     }

     @Override
     public int compareTo(Line o) {
        Double number1 = toDouble(getNumber());
        Double number2 = toDouble(o.getNumber());
         return number1.compareTo(number2) ;
     }

     private Double toDouble(String lineNumber){
        Double number = 0.0;
        String regNumber = "[0-9]+";
        if(lineNumber.matches(regNumber)){
            number = Double.parseDouble(lineNumber);
        }
        else {
            if(lineNumber.equalsIgnoreCase("11A")){
                number = 11.5;
            }
            else if(lineNumber.equalsIgnoreCase("D1")){
                number = 16d;
            } else if (lineNumber.equalsIgnoreCase("D2")){
                number = 17d;
            }
        }
        return number;
     }
 }
