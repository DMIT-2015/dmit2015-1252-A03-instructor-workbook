package dmit2015.repository;

import dmit2015.entity.Department;
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
}
