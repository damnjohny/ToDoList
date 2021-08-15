import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ArrayList<String> toDo = new ArrayList<>();

    private static void fillingListOfTasks(File temp) { // reading saved tasks from temp file and adding them to our tasks list
        if (temp.length() > 0) {
            try {
                List<String> savedTasks = Files.readAllLines(temp.toPath());
                toDo.addAll(savedTasks);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void deleteAllTasksFromList() {
        toDo.clear();
    }

    private static void deleteAllTasksFromTempFile(File temp) {
        try {
            Files.readAllLines(temp.toPath()).clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void save(String line, File temp) {
        try {
            Files.write(temp.toPath(), Collections.singleton(line), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        final String dir = System.getProperty("user.dir");
        Path temp;
        // check if file already exists
        if (Files.notExists(Path.of(Path.of(dir) + File.separator + "temp.tmp"))) {
            temp = Files.createFile(Path.of(dir + File.separator + "temp.tmp"));
        } else {
            temp = Path.of("temp.tmp");
        }

        fillingListOfTasks(temp.toFile());

        System.out.println("1. Add task");
        System.out.println("2. Delete task");
        System.out.println("3. Show tasks");
        System.out.println("4. Clear all tasks");
        System.out.println("5. Close the program");

        while (true) {
            System.out.println("Enter ur choice");
            Scanner ip = new Scanner(System.in);
            int num = ip.nextInt();
            ip.close();
            switch (num) {
                case 1: // adding new task
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter task");
                    String task = sc.nextLine();
                    toDo.add(task);
                    save(task, temp.toFile());
                    break;
                case 2: // deleting the task with entered index
                    System.out.println("Enter number of task");
                    Scanner scan = new Scanner(System.in);
                    int number = scan.nextInt();
                    if (number > toDo.size()) {
                        System.out.println("No task with that number");
                        continue;
                    } else {
                        toDo.remove(number - 1); // for deleting tasks from "0", not from "1"
                        break;
                    }
                case 3: // shows all tasks
                    if (toDo.isEmpty()) {
                        System.out.println("Still empty!");
                    } else {
                        for (int i = 0; i < toDo.size(); i++) {
                            System.out.println(i + 1 + "." + " " + toDo.get(i)); // showing index of task from "1" instead of "0"
                            System.out.println();
                        }
                    }
                    break;
                case 4: // deleting all the tasks from list and temp file
                    System.out.println("Are you sure you want to delete all the tasks?");
                    System.out.println("Print 'y' to agree or 'n' to disagree");
                    Scanner scanner = new Scanner(System.in);
                    String answer = scanner.nextLine();
                    if (answer.equals("y")) {
                        deleteAllTasksFromList();
                        deleteAllTasksFromTempFile(temp.toFile());
                        break;
                    } else if (answer.equals("n")) {
                        break;
                    } else {
                        System.out.println("Wrong answer!");
                        continue;
                    }
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter proper choice");
                    System.out.println("1. Add task");
                    System.out.println("2. Delete task");
                    System.out.println("3. Show tasks");
                    System.out.println("4. Clear all tasks");
                    System.out.println("5. Close the program");
            }
        }
    }
}
