import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ToDoList {
    private static final ArrayList<String> toDo = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Path temp = createTempFile();
        fillingListOfTasks(temp.toFile());

        while (true) {
            String num = printMenu();
            switch (num) {
                case "1": // adding new task to temp and list
                    addNewTask(temp);
                    clearConsole();
                    break;
                case "2": // deleting the task with entered index
                    boolean answer = deleteTask();
                    if (answer) {
                        clearConsole();
                        break;
                    } else continue;

                case "3": // shows all tasks
                    clearConsole();
                    showAllTasks();
                    break;
                case "4": // deleting all the tasks from list and temp file
                    System.out.println("Are you sure you want to delete all the tasks?".toUpperCase());
                    System.out.print("Print 'y' to agree or 'n' to disagree: ".toUpperCase());
                    Scanner scanner = new Scanner(System.in);
                    String print = scanner.nextLine();
                    if (print.equals("y")) {
                        deleteAllTasksFromTempFile(temp.toFile());
                        deleteAllTasksFromList();
                        clearConsole();
                        break;
                    } else if (print.equals("n")) {
                        clearConsole();
                        break;
                    } else {
                        System.out.println("WRONG ANSWER!");
                        clearConsole();
                        continue;
                    }
                case "5": // exit from program
                    clearConsole();
                    System.exit(0);
                    break;
                default: // if entered "no-case" value
                    clearConsole();
                    System.out.println("NO SUCH COMMAND! PLEASE TRY AGAIN.");
                    System.out.println();
            }
        }
    }

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
            Files.delete(temp.toPath());
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

    private static Path createTempFile() throws IOException {
        final String dir = System.getProperty("user.dir");
        Path temp;
        // check if file already exists
        if (Files.notExists(Path.of(Path.of(dir) + File.separator + "temp.tmp"))) {
            temp = Files.createFile(Path.of(dir + File.separator + "temp.tmp"));
            return temp;
        } else {
            temp = Path.of("temp.tmp");
            return temp;
        }
    }

    private static String printMenu() {
        System.out.println("********** MENU ***********");
        System.out.println("*                         *");
        System.out.println("*   1. ADD TASK           *");
        System.out.println("*   2. DELETE TASK        *");
        System.out.println("*   3. SHOW ALL TASKS     *");
        System.out.println("*   4. CLEAR ALL TASKS    *");
        System.out.println("*   5. CLOSE THE PROGRAM  *");
        System.out.println("*                         *");
        System.out.println("***************************");
        System.out.print("ENTER YOUR CHOOSE: ");

        Scanner ip = new Scanner(System.in);

        return ip.nextLine();
    }

    private static void addNewTask(Path temp) {
        Scanner sc = new Scanner(System.in);
        System.out.print("ENTER TASK: ");
        String task = sc.nextLine();
        toDo.add(task);
        save(task, temp.toFile());
    }

    private static boolean deleteTask() {
        System.out.print("ENTER THE NUMBER OF THE TASK TO DELETE: ");
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        if (number > toDo.size()) {
            System.out.println("NO TASK WITH THAT NUMBER");
            return false;
        } else {
            toDo.remove(number - 1); // for deleting tasks from "0", not from "1"
            return true;
        }
    }

    private static void showAllTasks() {
        if (toDo.isEmpty()) {
            System.out.println(System.lineSeparator() + "STILL EMPTY!".toUpperCase() + System.lineSeparator());
        } else {
            System.out.println("YOUR TASKS:");
            System.out.println();
            for (int i = 0; i < toDo.size(); i++) {
                System.out.println(i + 1 + ")" + " " + toDo.get(i)); // showing index of task from "1" instead of "0"
                System.out.println();
            }
        }
    }

    private static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
