public class LecturersCountException extends Exception{

    private int input;
    public int getInput() { return input; }
    public LecturersCountException(int input) {
        super("Кількість викладачів не може бути " + input + "\n");
        this.input = input;
    }
}
