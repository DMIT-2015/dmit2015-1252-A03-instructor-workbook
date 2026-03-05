package dmit2015.repository;

import dmit2015.entity.Department;
import dmit2015.entity.Employee;
import dmit2015.entity.Job;
import jakarta.data.repository.Find;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;

import java.util.List;

@Repository
public interface HumanResourcesRepository {
    @Query("""
select d
 from Department d
 where lower(d.departmentName) like lower(?1)
 order by d.departmentName
""")
    List<Department> departmentsBy(String partialDepartmentName);

    @Query("""
select e
 from Employee e join fetch e.department join fetch e.manager join fetch e.job
 where e.department.id = ?1
""")
    List<Employee> employeesByDepartmentId(Short deptId);

    @Find
    Department departmentByDepartmentId(Short id);

    @Query("""
select j
 from Job j
 where lower(j.jobTitle) like lower(?1)
""")
    List<Job> jobsBy(String partialJobTitle);

    @Find
    Job jobByJobId(String jobId);

    @Query("""
select e
 from Employee e join fetch e.department join fetch e.manager join fetch e.job
 where e.job.jobId = ?1
""")
    List<Employee> employeesByJobId(String jobId);

}
