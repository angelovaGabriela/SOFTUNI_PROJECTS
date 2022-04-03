import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class softUniCoursePlaning {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        List <String> lessons = Arrays
                .stream(scanner.nextLine().split(", "))
                .collect(Collectors.toList());

        String command = scanner.nextLine();

        while (!command.equals("course start")){

            String[] commandData = command.split(":");
            String toDo = commandData[0];
            String lessonTitle= commandData[1];

            switch (toDo){
                case "Add":
                    if(!lessons.contains(lessonTitle)){
                        lessons.add(lessonTitle);
                    }
                    break;
                case "Insert":
                    int givenIndex = Integer.parseInt(commandData[2]);
                    if (!lessons.contains(lessonTitle)){
                        lessons.add(givenIndex,lessonTitle);
                    }
                    break;
                case "Remove":
                    if(lessons.contains(lessonTitle)) {
                        lessons.remove(lessonTitle);
                    }
                        int indexLesson = lessons.indexOf(lessonTitle);
                        if(lessons.get(indexLesson + 1).equals(lessonTitle+"-Exercise")) {
                            lessons.remove(indexLesson + 1);
                        }
                    break;
                case "Swap":
                    String lessonTitle2 = command.split(":")[2];

                    if (lessons.contains(lessonTitle) && lessons.contains(lessonTitle2)){
                        int lesson1Index = lessons.indexOf(lessonTitle);
                        int lesson2Index = lessons.indexOf(lessonTitle2);

                        lessons.set(lesson1Index, lessonTitle2);
                        lessons.set(lesson2Index, lessonTitle);


                        String exOne = lessonTitle + "-Exercise";
                        String exTwo = lessonTitle2 + "-Exercise";

                        if (lessons.contains(exOne)) {
                            lessons.remove(lessons.indexOf(exOne));
                            lessons.add(lessons.indexOf(lessonTitle + 1), exOne);
                        }
                        if (lessons.contains(exTwo)) {
                            lessons.remove(lessons.indexOf(exTwo));
                            lessons.add(lessons.indexOf(lessonTitle2) + 1, exTwo);
                        }
                    }

                    break;
                case "Exercise":

                    String exercise = lessonTitle + "-Exercise";
                    int indexLessonTitle = lessons.indexOf(lessonTitle);
                    if(lessons.contains(lessonTitle)) {
                        //има ли упражнение
                        if(indexLessonTitle == lessons.size() - 1) {
                            lessons.add(indexLessonTitle + 1, exercise);
                        } else  if(!lessons.get(indexLessonTitle + 1).equals(exercise)) {
                            lessons.add(indexLessonTitle + 1, exercise);
                        }
                    } else {
                        lessons.add(lessonTitle);
                        lessons.add(exercise);
                    }
                    break;
            }



            command = scanner.nextLine();
        }
        printList(lessons);
    }



    private static void printList(List<String> elements) {
        int count = 1;
        for (String element : elements) {
            System.out.println(count + "." + element);
            count++;

        }
    }
}
