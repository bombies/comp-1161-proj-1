import java.util.Arrays;
import java.util.List;

public class SportBus extends Bus {
    private final List<String> sportList;
    private double competitorArea;
    private double spectatorArea;
    
    public SportBus(String name, int basePrice, int lev, Ministry mny, int competitorArea, int spectatorArea, String sportList) {
        super(name, competitorArea + spectatorArea, basePrice, lev, mny);
        this.tripTypes += ",SPORT";
        this.sportList = Arrays.stream(sportList.split(" ")).toList();
        this.competitorArea = competitorArea;
        this.spectatorArea = spectatorArea;
    }

    public double getCompetitorArea() {
        return this.competitorArea;
    }

    public double getSpectatorArea() {
        return this.spectatorArea;
    }

    public int getBasePrice() {
        return 3 * super.getBasePrice();
    }

    public int getEstimate(String type, int numPersons, int level) {
        return (int)Math.floor(this.getBasePrice() * 10 * (competitorArea + spectatorArea) / competitorArea);
    }
}
