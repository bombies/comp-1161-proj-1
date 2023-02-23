public class PartyBus extends TrainingBus {
    private final int barArea, foodArea, securityCrew;

    public PartyBus(String name, int basePrice, int lev, int wifiRange, Ministry mny, int competitorArea, int spectatorArea,
            String sportList, int barArea, int foodArea, int securityCrew) {
        super(name, basePrice, wifiRange, lev, mny, competitorArea, spectatorArea, sportList);
        this.tripTypes += ",PARTY";
        this.barArea = barArea;
        this.foodArea = foodArea;
        this.securityCrew = securityCrew;
    }

    @Override
    public int getEstimate(String type, int numPersons, int level) {
        return foodArea * super.getEstimate(type, numPersons, level) / (3 * barArea);
    }
    
}
