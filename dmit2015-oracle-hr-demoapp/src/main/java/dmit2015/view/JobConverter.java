package dmit2015.view;

import dmit2015.entity.Job;
import dmit2015.repository.HumanResourcesRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.ToString;

@Named
@ApplicationScoped
@FacesConverter(managed = true, value = "jobConverter")
public class JobConverter implements Converter<Job> {

    @Inject
    private HumanResourcesRepository hrRepository;

    @Override
    public Job getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        String jobId = s;
        return hrRepository.jobByJobId(jobId);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Job job) {
        return job.getJobId();
    }
}
