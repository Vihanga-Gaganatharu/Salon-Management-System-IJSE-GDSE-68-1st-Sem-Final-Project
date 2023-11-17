drop database if exists salonManage;
create database if not exists salonManage;

use salonManage;

Create table loginHistory (
                              date DATE PRIMARY KEY,
                              Time Decimal(4)
);


CREATE TABLE Employee(
                         emp_id VARCHAR(10) PRIMARY KEY,
                         first_name VARCHAR(50),
                         last_name varchar(50),
                         email VARCHAR(100),
                         phone_number VARCHAR(20),
                         Nic VARCHAR(30),
                         job_Rank VARCHAR(50),
                         username varchar(30),
                         password varchar(30)
);

CREATE TABLE Salary(
                       Salary_Id VARCHAR(10) PRIMARY KEY,
                       Amount VARCHAR(10),
                       Date DATE,
                       Descrption VARCHAR(50),
                       emp_id VARCHAR(30),
                       FOREIGN KEY (emp_id) REFERENCES Employee(emp_id)
);

CREATE TABLE Attendance(
                           Attend_Id VARCHAR(10) PRIMARY KEY,
                           Month VARCHAR(10),
                           Date DATE,
                           Descrption VARCHAR(50),
                           Time decimal (4),
                           emp_id VARCHAR(30),
                           constraint FOREIGN KEY (emp_id) REFERENCES Employee(emp_id)
);

CREATE TABLE Customers (
                           c_id VARCHAR(10) PRIMARY KEY,
                           first_name VARCHAR(50),
                           nic VARCHAR(50),
                           address VARCHAR(100),
                           phone_number VARCHAR(20),
                           gender VARCHAR(10)
);



CREATE TABLE Booking(
                        B_Id VARCHAR(10) PRIMARY KEY,
                        Date DATE,
                        Time decimal (4),
                        emp_id VARCHAR(10),
                        c_id VARCHAR(10),
                        service VARCHAR(30),
                        constraint FOREIGN KEY (emp_id) REFERENCES Employee(emp_id) ON UPDATE CASCADE ON DELETE CASCADE ,
                        constraint FOREIGN KEY (c_id) REFERENCES Customers(c_id) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE service(
                        Service_Id VARCHAR(10) PRIMARY KEY,
                        Service_Name VARCHAR(30),
                        Service_Type VARCHAR(30),
                        Price VARCHAR(30),
                        Description VARCHAR(100)

);

CREATE TABLE BookingDetails(
                               Description VARCHAR(30),
                               Service_id VARCHAR(30),
                               B_id VARCHAR(30),
                               constraint FOREIGN KEY (Service_id) REFERENCES Service(Service_id) ,
                               constraint FOREIGN KEY (B_id) REFERENCES Booking(B_id)
);




CREATE TABLE Payments(
                         Pay_Id VARCHAR(10)PRIMARY KEY,
                         Date DATE,
                         Time decimal(4),
                         Amount VARCHAR(50),
                         Description VARCHAR(50),
                         Service_id VARCHAR(30),
                         constraint FOREIGN KEY (Service_id) REFERENCES Service(Service_id)
);


CREATE TABLE Product(
                        Product_Id VARCHAR(10)PRIMARY KEY,
                        GetDate DATE,
                        qty int(20),
                        Price decimal(4),
                        Buy_Item VARCHAR(50),
                        Rent_Item VARCHAR(50),
                        description text,
                        Product_Name VARCHAR(50)
);

CREATE TABLE ProductDetails(
                               Description VARCHAR(30),
                               c_id VARCHAR(30),
                               Product_id VARCHAR(30),
                               constraint FOREIGN KEY (Product_id) REFERENCES Product(Product_id),
                               constraint FOREIGN KEY (c_id) REFERENCES Customers(c_id)
);



