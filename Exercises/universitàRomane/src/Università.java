import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface Università {
    UniversitàRomane annotation() default UniversitàRomane.SAPIENZA;

}
enum UniversitàRomane{
    SAPIENZA,ROMA3;
}

@Università(annotation = UniversitàRomane.ROMA3)
class StudenteSapienza{

}
