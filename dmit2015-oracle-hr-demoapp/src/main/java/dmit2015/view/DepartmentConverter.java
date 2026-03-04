package dmit2015.view;

import dmit2015.entity.Department;
import dmit2015.repository.HumanResourcesRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
@FacesConverter(value = "departmentConverter", managed = true)
public class DepartmentConverter implements Converter<Department> {

    @Inject
    private HumanResourcesRepository hrRepository;

    @Override
    public Department getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isBlank()) {
            try {
                Short departmentId = Short.parseShort(value);
                return hrRepository.departmentByDepartmentId(departmentId);
            } catch (NumberFormatException e) {
                throw new ConverterException(new
                        FacesMessage(FacesMessage.SEVERITY_ERROR,"Conversion Error","Not a valid department."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Department value) {
        if (value != null) {
            return value.getId().toString();
        } else {
            return null;
        }
    }
}
