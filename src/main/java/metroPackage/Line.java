package metroPackage;

 class Line {
    private String lineName;

    public Line(String lineName) {
        this.lineName = lineName;
    }
    public Line(){

    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    @Override
    public String toString() {
        return getLineName();
    }
}
