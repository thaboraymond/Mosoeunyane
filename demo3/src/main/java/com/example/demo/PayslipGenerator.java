package com.example.demo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PayslipGenerator {

    public void generatePayslip(int employeeId, double grossSalary, double deductions, double netSalary) {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "INSERT INTO payslips (employee_id, gross_salary, deductions, net_salary, payslip_date) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, employeeId);
                stmt.setDouble(2, grossSalary);
                stmt.setDouble(3, deductions);
                stmt.setDouble(4, netSalary);
                stmt.setDate(5, new Date(System.currentTimeMillis()));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
