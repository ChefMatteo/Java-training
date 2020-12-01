import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> grades = new ArrayList<>();
        grades.add(73);
        grades.add(67);
        grades.add(38);
        grades.add(33);

        List<Integer> gradesBounded = new ArrayList<>();
        for (Integer grade : grades) {
            if(grade%5 >= 3 && grade > 40){
                gradesBounded.add(grade-(grade%5)+5);
                System.out.println(grade-(grade%5)+5);
            }
            else{
                gradesBounded.add(grade);
                System.out.println(grade);
            }
        }
    }
}
