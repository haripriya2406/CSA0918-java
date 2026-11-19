import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Student {
    private final String regNo;
    private final String name;
    Student(String regNo, String name) { this.regNo = regNo; this.name = name; }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student) o;
        return Objects.equals(regNo, s.regNo);
    }
    // TODO 1 completed: equal registration numbers produce equal hash codes.
    @Override public int hashCode() { return Objects.hash(regNo); }
    @Override public String toString() { return regNo + " " + name; }
}

public class Attendance {
    public static void main(String[] args) {
        Set<Student> present = new HashSet<>();
        present.add(new Student("192311001", "Arun"));
        present.add(new Student("192311002", "Divya"));
        present.add(new Student("192311001", "Arun"));
        System.out.println("Unique students: " + present.size());

        // A third test: immutable identity fields prevent hash-bucket corruption.
        Student sameId = new Student("192311003", "Kavi");
        present.add(sameId);
        System.out.println("After adding Kavi: " + present.size());
        System.out.println("Contains equal Kavi: " + present.contains(new Student("192311003", "Different display name")));
    }
}
