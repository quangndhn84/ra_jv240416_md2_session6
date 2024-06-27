package ra.run;

import ra.entity.Student;

import java.util.Scanner;

public class StudentManagement {
    //Mảng lưu trữ các sinh viên đang quản lý
    Student[] arrStudents = new Student[100];
    //Chỉ số nhỏ nhất chưa lưu sinh viên
    int currentIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagement studentManagement = new StudentManagement();
        //Hiển thị menu và thực hiện các chức năng theo menu
        do {
            System.out.println("******************MENU********************");
            System.out.println("1. Danh sách sinh viên");
            System.out.println("2. Thêm mới các sinh viên");
            System.out.println("3. Cập nhật thông tin sinh viên");
            System.out.println("4. Xóa thông tin sinh viên");
            System.out.println("5. Tính năm sinh cho các sinh viên");
            System.out.println("6. Tìm kiếm sinh viên theo tên sinh viên");
            System.out.println("7. Sắp xếp sinh viên theo năm sinh tăng dần");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    studentManagement.displayListStudent();
                    break;
                case 2:
                    studentManagement.inputListStudent(scanner);
                    break;
                case 3:
                    studentManagement.updateStudent(scanner, studentManagement);
                    break;
                case 4:
                    studentManagement.deleteStudetn(scanner, studentManagement);
                    break;
                case 5:
                    studentManagement.calYearOfListStudent();
                    break;
                case 6:
                    studentManagement.searchStudentByName(scanner);
                    break;
                case 7:
                    studentManagement.sortStudentByYearASC();
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-8");
            }
        } while (true);
    }

    public void displayListStudent() {
        for (int i = 0; i < currentIndex; i++) {
            arrStudents[i].displayData();
        }
    }

    public void inputListStudent(Scanner scanner) {
        System.out.println("Nhập vào số sinh viên cần nhập thông tin:");
        int numberOfStudents = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfStudents; i++) {
            //Nhập thông tin cho 1 sinh viên
            //1. Khởi tạo phần tử currentIndex là 1 đối tượng sinh viên
            arrStudents[currentIndex] = new Student();
            //2. Nhập thông tin cho phần tử currentIndex
            arrStudents[currentIndex].inputData(scanner, arrStudents, currentIndex);
            //3. Tăng currentIndex
            currentIndex++;
        }
    }

    public void updateStudent(Scanner scanner, StudentManagement studentManagement) {
        System.out.println("Nhập vào mã sinh viên cần cập nhật thông tin:");
        String studentId = scanner.nextLine();
        //Kiểm tra mã sinh viên có tồn tại không
        int indexUpdate = studentManagement.getIndexByStudentId(studentId);
        if (indexUpdate >= 0) {
            //Có tồn tại sinh viên --> cập nhật thông tin sinh viên
            boolean isExit = true;
            do {
                System.out.println("1. Cập nhật tên sinh viên");
                System.out.println("2. Cập nhật tuổi sinh viên");
                System.out.println("3. Cập nhật giới tính sinh viên");
                System.out.println("4. Cập nhật địa chỉ sinh viên");
                System.out.println("5. Cập nhật số điện thoại sinh viên");
                System.out.println("6. Thoát");
                System.out.print("Lựa chọn của bạn:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Nhập vào tên sinh viên cần cập nhật:");
                        arrStudents[indexUpdate].setStudentName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Nhập vào tuổi sinh viên cần cập nhật:");
                        arrStudents[indexUpdate].setAge(Integer.parseInt(scanner.nextLine()));
                        //Tính lại năm sinh
                        arrStudents[indexUpdate].calYear();
                        break;
                    case 3:
                        System.out.println("Nhập vào giới tính sinh viên:");
                        arrStudents[indexUpdate].setSex(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    case 4:
                        System.out.println("Nhập vào địa chỉ sinh viên cần cập nhật:");
                        arrStudents[indexUpdate].setAddress(scanner.nextLine());
                        break;
                    case 5:
                        System.out.println("Nhập vào số điện thoại sinh viên cần cập nhật:");
                        arrStudents[indexUpdate].setPhone(scanner.nextLine());
                        break;
                    case 6:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-6");
                }
            } while (isExit);

        } else {
            //Không tồn tại sinh viên có mã là studentId
            System.err.println("Không tồn tại mã sinh viên");
        }
    }

    public int getIndexByStudentId(String studentId) {
        for (int i = 0; i < currentIndex; i++) {
            if (arrStudents[i].getStudentId().equals(studentId)) {
                return i;
            }
        }
        return -1;
    }

    public void deleteStudetn(Scanner scanner, StudentManagement studentManagement) {
        System.out.println("Nhập vào mã sinh viên cần xóa:");
        String studentId = scanner.nextLine();
        int indexDelete = studentManagement.getIndexByStudentId(studentId);
        if (indexDelete >= 0) {
            //Thực hiện xóa
            for (int i = indexDelete; i < currentIndex - 1; i++) {
                arrStudents[i] = arrStudents[i + 1];
            }
            //gán phần tử cuối cùng là null
            arrStudents[currentIndex - 1] = null;
            //Giảm currentIndex 1 đơn vị
            currentIndex--;
        } else {
            System.err.println("Mã sinh viên không tồn tại");
        }
    }

    public void calYearOfListStudent() {
        for (int i = 0; i < currentIndex; i++) {
            arrStudents[i].calYear();
        }
    }

    public void searchStudentByName(Scanner scanner) {
        System.out.println("Nhập vào tên sinh viên cần tìm:");
        String studentNameSearch = scanner.nextLine();
        int cntStudent = 0;
        System.out.println("Các sinh viên tìm được:");
        for (int i = 0; i < currentIndex; i++) {
            if (arrStudents[i].getStudentName().toLowerCase().contains(studentNameSearch.toLowerCase())) {
                arrStudents[i].displayData();
                cntStudent++;
            }
        }
        System.out.printf("Tìm thấy %d sinh viên phù hợp\n", cntStudent);
    }

    public void sortStudentByYearASC() {
        for (int i = 0; i < currentIndex - 1; i++) {
            for (int j = i + 1; j < currentIndex; j++) {
                if (arrStudents[i].getYear() > arrStudents[j].getYear()) {
                    Student temp = arrStudents[i];
                    arrStudents[i] = arrStudents[j];
                    arrStudents[j] = temp;
                }
            }
        }
        System.out.println("Đã sắp xếp xong các sinh viên theo tuổi tăng dần");
    }
}
