import java.util.ArrayList;
import java.util.Arrays;

public class TrainBus extends Bus {
    private final int teacherArea, studentArea, wifiRange;
    private final ArrayList<String> courseList;

    public TrainBus(String name, int basePrice, int wifiRange, int lev, Ministry mny, int teacherArea, int studentArea, String courseList) {
        super(name, teacherArea + studentArea, basePrice, lev, mny);
        this.tripTypes += ",TRAINING";
        this.teacherArea = teacherArea;
        this.studentArea = studentArea;
        this.wifiRange = wifiRange;
        this.courseList = new ArrayList<>(Arrays.stream(courseList.split(" ")).toList());
    }

    public double getTeacherArea() {
        return this.teacherArea;
    }

    public double getStudentArea() {
        return this.studentArea;
    }

    public int getBasePrice() {
        return 2 * super.getBasePrice();
    }

    public int getEstimate(String type, int numPersons, int level) {
        return 5 * teacherArea * super.getEstimate(type, numPersons, level) / courseList.size();
    }
}
