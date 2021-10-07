
import java.util.*;

public class ToDoList {

    public static void main(String[] args) {
        ToDoListDB.connectToDB();

        while (true) {
            String num = printMenu();
            switch (num) {

                case "1" -> { // adding new task to temp and list

                    System.out.print("Enter task:");

                    ToDoListDB.addTask(new Scanner(System.in).nextLine());
                }

                case "2" -> { // deleting the task with entered index
                    System.out.print("Enter task to delete:");
                    ToDoListDB.deleteTask(new Scanner(System.in).nextLine());
                }

                case "3" -> // shows all tasks
                        ToDoListDB.showAllTasks();

                case "4" -> // deleting all the tasks from list and temp file
                        ToDoListDB.deleteAllTasks();

                case "5" -> {

                    System.out.print("Task to edit: ");
                    String oldTask = new Scanner(System.in).nextLine();
                    System.out.print("New task: ");
                    String newTask = new Scanner(System.in).nextLine();
                    ToDoListDB.editTask(oldTask, newTask);

                }

                case "6" -> // exit from program
                        System.exit(0);

                default -> { // if entered "no-case" value
                    System.out.println("NO SUCH COMMAND! PLEASE TRY AGAIN.");
                }
            }
        }
    }

    private static String printMenu() {
        System.out.println("********** MENU ***********");
        System.out.println("*                         *");
        System.out.println("*   1. ADD TASK           *");
        System.out.println("*   2. DELETE TASK        *");
        System.out.println("*   3. SHOW ALL TASKS     *");
        System.out.println("*   4. CLEAR ALL TASKS    *");
        System.out.println("*   5. EDIT TASK          *");
        System.out.println("*   6. CLOSE THE PROGRAM  *");
        System.out.println("*                         *");
        System.out.println("***************************");
        System.out.print("ENTER YOUR CHOOSE: ");

        Scanner ip = new Scanner(System.in);

        return ip.nextLine();
    }
}
