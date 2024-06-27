package ra.entity;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Student {
    //1. Fields/Attributes/Properties
    private String studentId;
    private String studentName;
    private int age;
    private int year;
    private boolean sex;
    private String address;
    private String phone;

    //2. Constructors
    public Student() {
    }

    public Student(String studentId, String studentName, int age, int year, boolean sex, String address, String phone) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.year = year;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }

    //3. Methods
    //3.1. Getter/Setter
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //3.2. Methods
    public void inputData(Scanner scanner, Student[] arrStudents, int currentIndex) {
        //1. Mã sinh viên gồm 5 ký tự, bắt đầu là SV, 3 ký tự sau phải là số, không được trùng lặp
        this.studentId = inputStudentId(scanner, arrStudents, currentIndex);
        //2. Tên sinh viên phải gồm từ 6-50 ký tự
        this.studentName = inputStudentName(scanner);
        //3. Tuổi sinh viên phải lớn hơn hoặc bằng 18
        this.age = inputAge(scanner);
        //4. Không nhập năm sinh mà phải tính toán
        //5. Giới tính chỉ nhận 1 trong 2 giá trị true-Nam, false-Nữ
        this.sex = inputSex(scanner);
        //6. Địa chỉ bắt buộc phải nhập
        this.address = inputAddress(scanner);
        //7. Số điện thoại gồm 10 số (0-9)
        this.phone = inputPhone(scanner);
    }

    public String inputStudentId(Scanner scanner, Student[] arrStudents, int currentIndex) {
        String studentIdRegex = "SV[\\d]{3}";
        System.out.println("Nhập vào mã sinh viên:");
        do {
            String studentId = scanner.nextLine();
            if (Pattern.matches(studentIdRegex, studentId)) {
                //Kiểm tra mã sinh viên có bị trùng hay không
                boolean isExist = false;//Chưa tồn tại mã sinh viên
                for (int i = 0; i < currentIndex; i++) {
                    if (arrStudents[i].getStudentId().equals(studentId)) {
                        isExist = true;//Đã tồn tại mã sinh viên
                        break;
                    }
                }
                if (isExist) {
                    //Trùng mã sinh viên
                    System.err.println("Mã sinh viên đã tồn tại, vui lòng nhâp lại");
                } else {
                    return studentId;
                }
            } else {
                System.err.println("Mã sinh viên không đúng định dạng, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputStudentName(Scanner scanner) {
        //String studentNameRegex = "[\\w]{6,50}";
        System.out.println("Nhập vào tên sinh viên:");
        do {
            String studentName = scanner.nextLine();
            if (studentName.length() >= 6 && studentName.length() <= 50) {
                return studentName;
            } else {
                System.err.println("Tên sinh viên gồm 6-50 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public int inputAge(Scanner scanner) {
        System.out.println("Nhập vào tuổi sinh viên:");
        do {
            int age = Integer.parseInt(scanner.nextLine());
            if (age >= 18) {
                return age;
            } else {
                System.err.println("Tuổi sinh viên phải lớn hơn hoặc bằng 18, vui lòng nhập lại");
            }
        } while (true);
    }

    public boolean inputSex(Scanner scanner) {
        System.out.println("Nhập vào giới tính sinh viên:");
        do {
            String sex = scanner.nextLine();
            if (sex.equals("true") || sex.equals("false")) {
                return Boolean.parseBoolean(sex);
            } else {
                System.err.println("Giới tính chỉ nhận giá trị true || false, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputAddress(Scanner scanner) {
        System.out.println("Nhập vào địa chỉ của bạn:");
        do {
            String address = scanner.nextLine();
            if (address.trim().length() == 0) {
                System.err.println("Vui lòng nhập địa chỉ sinh viên");
            } else {
                return address;
            }
        } while (true);
    }

    public String inputPhone(Scanner scanner) {
        String phoneRegex = "[\\d]{10}";
        System.out.println("Nhập vào số điện thoại sinh viên:");
        do {
            String phone = scanner.nextLine();
            if (Pattern.matches(phoneRegex, phone)) {
                return phone;
            } else {
                System.err.println("Số điện thoại không đúng định dạng, vui lòng nhập lại");
            }
        } while (true);
    }

    public void displayData() {
        System.out.printf("Mã SV: %s - Tên SV: %s - Tuổi: %d - Năm sinh: %d\n",
                this.studentId, this.studentName, this.age, this.year);
        System.out.printf("Giới tính: %s - Địa chỉ: %s - SĐT: %s\n",
                this.sex ? "Nam" : "Nữ", this.address, this.phone);
    }

    public void calYear() {
        this.year = 2024 - this.age;
    }
}
