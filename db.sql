-- Active: 1680145558078@@127.0.0.1@3306@quanlysinhvien
drop DATABASE IF EXISTS quanlysinhvien;
create DATABASE quanlysinhvien;
use quanlysinhvien;

CREATE TABLE adminaccount (
admin_id VARCHAR(10) NOT NULL,
username varchar(50) DEFAULT NULL,
password varchar(50) DEFAULT NULL,
PRIMARY KEY (admin_id),
UNIQUE KEY username (username)
);

-- Tạo bảng courses
CREATE TABLE courses (
course_id VARCHAR(10) NOT NULL,
course_name varchar(50) DEFAULT NULL,
course_credit int DEFAULT NULL,
PRIMARY KEY (course_id)
);

-- Tạo bảng grades
CREATE TABLE grades (
student_id varchar(10) NOT NULL,
course_id VARCHAR(10) NOT NULL,
attendance_grade float DEFAULT NULL,
midterm_grade float DEFAULT NULL,
final_grade float DEFAULT NULL,
PRIMARY KEY (student_id, course_id),
KEY course_id (course_id),
CONSTRAINT grades_ibfk_1 FOREIGN KEY (student_id) REFERENCES students (student_id) ON DELETE CASCADE,
CONSTRAINT grades_ibfk_2 FOREIGN KEY (course_id) REFERENCES courses (course_id) ON DELETE CASCADE
);

-- Tạo bảng studentaccount
CREATE TABLE studentaccount (
student_id varchar(10) NOT NULL,
username varchar(50) DEFAULT NULL,
password varchar(50) DEFAULT NULL,
PRIMARY KEY (student_id),
UNIQUE KEY username (username)
);

-- Tạo bảng students
CREATE TABLE students (
student_id varchar(10) NOT NULL,
name varchar(50) DEFAULT NULL,
address varchar(100) DEFAULT NULL,
phone_number varchar(20) DEFAULT NULL,
email varchar(50) DEFAULT NULL,
date_of_birth date DEFAULT NULL,
gender varchar(10) DEFAULT NULL,
PRIMARY KEY (student_id)
);

CREATE TABLE pending (
  id INT AUTO_INCREMENT,
  student_id VARCHAR(10) NOT NULL,
  name VARCHAR(50) DEFAULT NULL,
  address VARCHAR(100) DEFAULT NULL,
  phone_number VARCHAR(20) DEFAULT NULL,
  email VARCHAR(50) DEFAULT NULL,
  date_of_birth DATE DEFAULT NULL,
  gender VARCHAR(10) DEFAULT NULL,
  created_at TIMESTAMP DEFAULT NOW(),
  PRIMARY KEY (id)
);


-- Thêm foreign key vào bảng studentaccount để kết nối với bảng students
ALTER TABLE studentaccount ADD FOREIGN KEY (student_id) REFERENCES students (student_id) ON DELETE CASCADE;

-- Thêm foreign key vào bảng grades để kết nối với bảng students và bảng courses


ALTER TABLE grades ADD FOREIGN KEY (course_id) REFERENCES courses (course_id) ON DELETE CASCADE ON UPDATE CASCADE;




-- Cấp quyền cho admin
GRANT SELECT, UPDATE, INSERT, DELETE ON students TO adminaccount;
GRANT SELECT, UPDATE, INSERT, DELETE ON studentaccount TO adminaccount;
GRANT SELECT, UPDATE, INSERT, DELETE ON grades TO adminaccount;

-- Cấp quyền cho sinh viên
GRANT SELECT, UPDATE ON students TO studentaccount;
GRANT SELECT ON grades TO studentaccount;


-- in ra du lieu cua tat ca cac bang
SELECT * FROM adminaccount;
SELECT * FROM courses;
SELECT * FROM grades;
SELECT * FROM studentaccount;
SELECT * FROM students;

-- viet query cap nhat student_id cua bang students thanh 21010001
UPDATE students SET student_id = '21010001' WHERE student_id = '988';

-- xoa du lieu cua tat ca cac bang
DELETE FROM students;
DELETE FROM courses;
DELETE from adminaccount;
DELETE FROM studentaccount;
DELETE FROM grades;

-- them du lieu 

INSERT INTO adminaccount (admin_id, username, password) VALUES ('1', 'admin', 'admin');
INSERT INTO studentaccount (student_id, username, password) VALUES ('2', 'student', 'student');

INSERT INTO students (student_id, name, address, phone_number, email, date_of_birth, gender)
VALUES ('21010001', 'Nguyen Van A', '123 ABC Street, Hanoi', '0987654321', '21010001@st.phenikaa-uni.edu.vn', '2001-01-01', 'Male');

INSERT INTO students (student_id, name, address, phone_number, email, date_of_birth, gender)
VALUES ('21010002', 'Tran Thi B', '456 XYZ Street, Hanoi', '0123456789', '21010002@st.phenikaa-uni.edu.vn', '2002-02-02', 'Female');

INSERT INTO courses (course_id, course_name, course_credit)
VALUES ('CSC101', 'Introduction to Computer Science', 3);

INSERT INTO courses (course_id, course_name, course_credit)
VALUES ('MAT102', 'Calculus I', 4);

INSERT INTO courses (course_id, course_name, course_credit)
VALUES ('CSC202', 'Data Structures and Algorithms', 3);


INSERT INTO grades (student_id, course_id, attendance_grade, midterm_grade, final_grade)
VALUES ('21010001', 'CSC101', 8.5, 7.0, 9.0);

INSERT INTO grades VALUES("21010001", "MAT102", 9.0, 8.0, 7.5);
INSERT INTO grades (student_id, course_id, attendance_grade, midterm_grade, final_grade)
VALUES ('21010002', 'MAT102', 9.0, 8.0, 7.5);

INSERT INTO studentaccount (student_id, username, password)
VALUES ('21010001', '1', '1');

INSERT INTO studentaccount (student_id, username, password)
VALUES ('21010002', 'tranthib', 'password456');


-- tao bang pending gom cac truong du lieu nhu bang student nhung khong co rang buoc




SELECT attendance_grade, midterm_grade, final_grade
FROM grades
WHERE student_id = '21010001' AND course_id = 'CSC101';

-- Kiem tra diem cua mot sinh vien
SELECT s.student_id, s.name, c.course_name, g.attendance_grade, g.midterm_grade, g.final_grade
FROM grades g
JOIN students s ON g.student_id = s.student_id
JOIN courses c ON g.course_id = c.course_id
WHERE s.student_id = '21010001';

-- kiem tra khoa hoc co bao nhieu sinh vien 
SELECT g.student_id, s.name, c.course_name, g.course_id, c.course_credit
FROM grades g
JOIN students s ON g.student_id = s.student_id
JOIN courses c ON g.course_id = c.course_id;


SELECT COUNT(*) as total
FROM grades g
WHERE g.course_id = 'CSC101';

DELETE from courses where course_id = 'CSC101';

SELECT student_id FROM studentaccount WHERE username = '1';

SELECT * FROM students WHERE student_id = '21010001';

SELECT courses.course_id, courses.course_name, grades.attendance_grade, grades.midterm_grade, grades.final_grade
FROM students
JOIN grades ON students.student_id = grades.student_id
JOIN courses ON grades.course_id = courses.course_id
WHERE students.student_id = '21010001';



SELECT 
    courses.course_id, 
    courses.course_name, 
    grades.attendance_grade, 
    grades.midterm_grade, 
    grades.final_grade, 
    ROUND((grades.attendance_grade * 0.1) + (grades.midterm_grade * 0.4) + (grades.final_grade * 0.5),2) as total_grade
FROM students
JOIN grades ON students.student_id = grades.student_id
JOIN courses ON grades.course_id = courses.course_id;

SELECT course_id, course_name, course_credit
FROM courses
WHERE course_id NOT IN (
    SELECT course_id
    FROM grades
    WHERE student_id = '21010001'
);

SELECT courses.course_id, courses.course_name, grades.attendance_grade, grades.midterm_grade, grades.final_grade, ROUND(( grades.attendance_grade * 0.1)+( grades.midterm_grade * 0.4)+(grades.final_grade * 0.5),2) as total_grade FROM students JOIN grades ON students.student_id = grades.student_id JOIN courses ON grades.course_id = courses.course_id WHERE students.student_id = '21010001';

-- xoa du lieu o ca 2 bang la student va studentaccount co studenid = 1
 DELETE FROM students WHERE student_id = '1';
 DELETE FROM studentaccount WHERE student_id = '1';


 


drop TABLE pending;

SELECT * from  pending;

delete from pending WHERE student_id = '21010001';

DELETE FROM pending;

--them id vao bang pending tu dong tang 1 cho moi ban ghi



SELECT name, address, phone_number, email, date_of_birth, gender, created_at 
FROM pending 
WHERE student_id = '21010001';
