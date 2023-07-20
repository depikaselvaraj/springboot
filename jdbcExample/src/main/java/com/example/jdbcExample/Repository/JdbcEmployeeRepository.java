package com.example.jdbcExample.Repository;

import com.example.jdbcExample.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcEmployeeRepository implements EmployeeRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Employee findById(long id) {
        Employee employee = jdbcTemplate.queryForObject("SELECT * FROM employee WHERE id=?",
                BeanPropertyRowMapper.newInstance(Employee.class), id);
        return employee;

    }

    @Override
    public Employee save(Employee employee) {
         jdbcTemplate.update("INSERT INTO employee (id,name,department,address,salary) VALUES(?,?,?,?,?)",
                new Object[] { employee.getId(), employee.getName(), employee.getDepartment(),employee.getAddress(),employee.getSalary() });
        Employee emp = jdbcTemplate.queryForObject("SELECT * FROM employee WHERE id=?",
                BeanPropertyRowMapper.newInstance(Employee.class), employee.getId());
       return emp;
    }

    @Override
    public String update(Employee employee) {
       return null;
    }

    @Override
    public List<Employee> findAll() {
        String query = "select * from employee";
        return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(Employee.class));
    }

    @Override
    public List<Employee> findByDepartmentOrAddress(String department, String address) {
        String query = "select * from employee where department =:department and address =:address";
        return namedParameterJdbcTemplate.query(query,
                new MapSqlParameterSource()
                .addValue("department", department)
                .addValue("address", address), new RowMapper<Employee>() {
                    @Override
                    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Employee em = new Employee();
                        em.setId(rs.getLong(1));
                        em.setName(rs.getString(2));
                        em.setDepartment(rs.getString(3));
                        em.setAddress(rs.getString(4));
                        em.setSalary(rs.getLong(5));
                        return em;
                    }
                });


    }

    @Override
    public List<Employee> getPage(int pageNo, int pageSize) {
        String query = "select * from employee  limit "+pageNo+" "+"offset "+pageSize;
        System.out.println(query);
     /* List<Employee> employee = jdbcTemplate.query(query, new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

                Employee em = new Employee();
                em.setId(rs.getLong(1));
                em.setName(rs.getString(2));
                em.setDepartment(rs.getString(3));
                em.setAddress(rs.getString(4));
                em.setSalary(rs.getLong(5));
                return em;
            }
        });
        return employee;*/
       return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(Employee.class));
    }
// bulk update with batch size
    @Override
    public List<Employee> BulkSave(List<Employee> employees) {
        List<Employee> emp = new ArrayList<>();
        int[][] update = jdbcTemplate.batchUpdate("INSERT INTO employee (id,name,department,address,salary) VALUES(?,?,?,?,?)",
                employees, 2, new ParameterizedPreparedStatementSetter<Employee>() {
                    @Override
                    public void setValues(PreparedStatement ps, Employee argument) throws SQLException {
                        ps.setLong(1,argument.getId());
                        ps.setString(2, argument.getName());
                        ps.setString(2, argument.getDepartment());
                        ps.setString(2, argument.getAddress());
                        ps.setLong(1,argument.getSalary());
                        Employee employee = new Employee();
                        employee.setId(argument.getId());
                        employee.setAddress(argument.getAddress());
                        employee.setDepartment(argument.getDepartment());
                        employee.setName(argument.getName());
                        employee.setSalary(argument.getSalary());
                        emp.add(employee);
                    }
                });
        System.out.println(update);
        return emp;
    }

    @Override
    public Employee BulSave(List<Employee> emp) {
        int [] update = jdbcTemplate.batchUpdate("INSERT INTO employee (id,name,department,address,salary) VALUES(?,?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1,emp.get(i).getId());
                        ps.setString(2, emp.get(i).getName());
                        ps.setString(3,emp.get(i).getAddress());
                        ps.setString(4, emp.get(i).getDepartment());
                        ps.setLong(5,emp.get(i).getSalary());
                    }

                    @Override
                    public int getBatchSize() {
                        return emp.size();
                    }
                });
        return null;
    }


}
