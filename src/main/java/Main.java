import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Main {

    private static String[] surnames = { "Іващенко", "Коваленко", "Терещенко"};
    private static String[] names_male = { "Іван", "Петро"};
    private static String[] names_female = { "Марія", "Юлія"};
    private static String[] secondNames_male = { "Петрович", "Юрійович"};
    private static String[] secondNames_female = { "Петрівна", "Юріївна"};
    private static String[] sexs = { "чоловік", "жінка"};
    private static String[] departments = { "інформаційних систем", "мережевих технологій"};
    private static String[] positions = { "доцент", "професор", "кандидат"};
    private static String[] disciplines_arr = { "програмування", "алгебра", "фізика", "вища математика", "філософія", "-"};

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info("Додаток запущений");

        int lecturersCount = initLecturersCount();
        Lecturer[] lecturers = initLecturers(lecturersCount);

        show(lecturers);

        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println();
            System.out.println("1. Отримати список викладачів заданої кафедри.\n" +
                               "2. Отримати список викладачів, що викладають задану дисципліну.\n" +
                               "3. Отримати список викладачів-жінок, що займають посаду доцента.\n" +
                               "4. Вихід");

            int command = initCommand();
            //in.nextLine();
            switch (command){
                case 1: {
                    System.out.println("Введіть кафедру: ");
                    String depart = in.nextLine();
                    System.out.println();
                    showDeps(lecturers, depart);
                    break;
                }
                case 2: {
                    System.out.println("Введіть дисципліну: ");
                    String disc = in.nextLine();
                    System.out.println();
                    showDisc(lecturers, disc);
                    break;
                }
                case 3: {
                    System.out.println();
                    showDoc(lecturers);
                    break;
                }
                case 4: {
                    logger.info("Додаток припинив роботу");
                    System.exit(1);
                    break;
                }
                default: {
                    System.out.println("Нема такого пункту меню");
                    break;
                }
            }
        }
    }

    private static int initCommand() {
        Scanner in = new Scanner(System.in);
        try {
            int command = in.nextInt();
            return command;
        }
        catch (InputMismatchException ex) {
            System.out.println("Потрібно ввести число\n");
            logger.error("Введено невірні дані");
            return -1;
        }
    }

    private static int initLecturersCount() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введіть кількість лекторів (<1000): ");
        try {
            int lecturersCount = in.nextInt();
            if(lecturersCount < 0)
                throw new LecturersCountException(lecturersCount);
            else if(lecturersCount > 1000)
                throw new LecturersCountException(lecturersCount);
            return lecturersCount;
        }
        catch (LecturersCountException ex) {
            System.out.println(ex.getMessage());
            logger.error("Введено невірні дані");
            return initLecturersCount();
        }
        catch (InputMismatchException ex) {
            System.out.println("Потрібно ввести число\n");
            logger.error("Введено невірні дані");
            return initLecturersCount();
        }
    }

    private static Lecturer[] initLecturers(int lecturersCount){
        String surname, name, secondName, sex, department, position;
        String[] disciplines;
        Lecturer[] lecturers = new Lecturer[lecturersCount];

        for (int i = 0; i < lecturersCount; i++) {
            sex = sexs[new Random().nextInt(sexs.length)];
            surname = surnames[new Random().nextInt(surnames.length)];
            department = departments[new Random().nextInt(departments.length)];
            position = positions[new Random().nextInt(positions.length)];
            disciplines = new String[2];
            disciplines[0] = disciplines_arr[new Random().nextInt(disciplines_arr.length)];
            do {
                disciplines[1] = disciplines_arr[new Random().nextInt(disciplines_arr.length)];
            } while(disciplines[0].equals(disciplines[1]));
            if (sex.equals("чоловік")) {
                name = names_male[new Random().nextInt(names_male.length)];
                secondName = secondNames_male[new Random().nextInt(secondNames_male.length)];
            } else {
                name = names_female[new Random().nextInt(names_female.length)];
                secondName = secondNames_female[new Random().nextInt(secondNames_female.length)];
            }
            lecturers[i] = new Lecturer(surname, name, secondName, sex, department, position, disciplines);
        }
        logger.info("Список лекторів проініціалізован");
        return lecturers;
    }
    private static void show(Lecturer[] lecturers){
        for (Lecturer l:lecturers) {
            l.print();
        }
    }
    private static void showDeps(Lecturer[] lecturers, String department){
        int cnt = 0;
        for (Lecturer l:lecturers) {
            if(!l.getDepartment().equals(department)) continue;
            l.print();
            cnt++;
        }
        if(cnt == 0) System.out.println("Нічого не знайдено");
    }
    private static void showDisc(Lecturer[] lecturers, String discipline){
        int cnt = 0;
        for (Lecturer l:lecturers) {
            if(l.getDisciplines()[0].equals(discipline) || l.getDisciplines()[1].equals(discipline)) {
                l.print();
                cnt++;
            }
        }
        if(cnt == 0) System.out.println("Нічого не знайдено");
    }
    private static void showDoc(Lecturer[] lecturers){
        int cnt = 0;
        for (Lecturer l:lecturers) {
            if(l.getSex().equals("жінка") && l.getPosition().equals("доцент")) {
                cnt++;
                l.print();
            }
        }
        if(cnt == 0) System.out.println("Нічого не знайдено");
    }
}
