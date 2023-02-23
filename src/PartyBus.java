public class PartyBus extends SportBus {
    private final int barArea, foodArea, securityCrew;


    public PartyBus(String name, int basePrice, int lev, Ministry mny, int competitorArea, int spectatorArea,
            String sportList, int barArea, int foodArea, int securityCrew) {
        super(name, basePrice, lev, mny, competitorArea, spectatorArea, sportList);
        this.tripTypes += ",PARTY";
        this.barArea = barArea;
        this.foodArea = foodArea;
        this.securityCrew = securityCrew;
    }

    @Override
    public int getEstimate(String type, int numPersons, int level) {
        return (int)Math.floor(5 * Math.sqrt(foodArea) * super.getEstimate(type, numPersons, level) / barArea);
    }
    
}
