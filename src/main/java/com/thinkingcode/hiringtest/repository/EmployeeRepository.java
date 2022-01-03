package com.thinkingcode.hiringtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.thinkingcode.hiringtest.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
