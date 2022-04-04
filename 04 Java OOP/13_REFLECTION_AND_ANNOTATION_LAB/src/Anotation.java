import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // кога да имам достъп до анотацията
@Target(ElementType.METHOD) // къде мога да използвам анотация, "НАД МЕТОДИ" само
public @interface Anotation {
    String name() default "unknown"; // задаване на дефолтна стойност на анотацията - съобщение
}
