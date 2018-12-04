public class Lecturer {

    private String surname, name, secondName, sex, department, position;
    private String[] disciplines;

    public Lecturer(String surname, String name, String secondName, String sex,
                    String department, String position, String[] disciplines) {
        this.surname = surname;
        this.name = name;
        this.secondName = secondName;
        this.sex = sex;
        this.department = department;
        this.position = position;
        this.disciplines = disciplines;

        Main.logger.info("Новий лектор в базі");
    }
    @Override
    public String toString() {
        String discips = "";
        for (String d:disciplines) {
            discips += d + "\t";
        }
        discips = discips.substring(0, discips.length()-2);
        return surname + " " + name + " " + secondName + ", " + sex + ", кафедра: "
                + department + ", посада: " + position + ", предмети: " + discips;
    }
    public void print(){
        System.out.printf("%-11s", surname);
        System.out.printf("%-7s", name);
        System.out.printf("%-10s", secondName);
        System.out.printf("%-9s", sex);
        System.out.printf("%-22s", department);
        System.out.printf("%-10s", position);
        System.out.printf("%-18s", disciplines[0]);
        System.out.printf("%-18s", disciplines[1]);
        System.out.println();
    }
    //--------------------  Getters ----------------------
    public String getSurname() { return surname; }
    public String getName(){ return name; }
    public String getSecondName(){ return secondName; }
    public String getSex(){ return sex; }
    public String getDepartment(){ return department; }
    public String getPosition(){ return position; }
    public String[] getDisciplines(){ return disciplines; }

    //--------------------  Setters ----------------------
    public void setSurname(String surname){ this.surname = surname; }
    public void setName(String name){ this.name = name; }
    public void setSecondName(String secondName){ this.secondName = secondName; }
    public void setDepartment(String department){ this.department = department; }
    public void setPosition(String position){ this.position = position; }
    public void setDisciplines(String[] disciplines){ this.disciplines = disciplines; }
    public void setSex(String sex){
        if(sex.equals("чоловік") || sex.equals("жінка"))
            this.sex = sex;
        else
            System.out.println("Невірна стать");
    }
}
