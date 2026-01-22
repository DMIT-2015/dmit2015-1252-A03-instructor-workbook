package dmit2015.faces;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Messages;

/**
 * View-scoped backing bean: lives across postbacks on the SAME view.
 * Destroyed when navigating away to a different view.
 */
@Named("signInView")
@ViewScoped // Survives postbacks (including AJAX) on this view; Serializable required
public class SignInView implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(SignInView.class.getName());

    // Declare read/write properties (field + getter + setter) for each form field
    @NotBlank(message = "Username is required.")
    @Getter
    @Setter
    private String username;

    @NotBlank(message = "Password is required.")
    @Getter
    @Setter
    private String password;

    @PostConstruct // Runs after @Inject fields are initialized (once per view instance)
    public void init() {
        // Initialize view state (avoid heavy I/O here)
        // Example: preload data for this view
        // selectedSignIn = new SignIn();
    }

    public String onSignIn() {
        try {
            if (username.equalsIgnoreCase("user2015") && password.equals("Password2015")) {
                // Forward navigation to the next page
//                return "/exercises/lotto-number-generator";
                // Redirect navigation to the next page
                return "/exercises/lotto-number-generator?faces-redirect=true";
            } else {
                Messages.addGlobalInfo("Incorrect username or password");
            }
        } catch (Exception ex) {
            handleException(ex,"Sign in exception");
        }
        return null;
    }

    public void onClear() {
        // Reset view state

        // selectedSignIn = null;
    }

    /**
     * Log server-side and show a concise root-cause chain in the UI.
     * Assumes the page includes <p:messages id="error" />.
     */
    protected void handleException(Throwable ex, String userMessage) {
        LOG.log(Level.SEVERE, userMessage != null ? userMessage : "Unhandled error", ex);

        StringBuilder details = new StringBuilder();
        Throwable t = ex;
        while (t != null) {
            String msg = t.getMessage();
            if (msg != null && !msg.isBlank()) {
                details.append(t.getClass().getSimpleName())
                        .append(": ")
                        .append(msg);
                if (t.getCause() != null) details.append("  Caused by: ");
            }
            t = t.getCause();
        }

        try {
            Messages.create(userMessage != null ? userMessage : "An unexpected error occurred.")
                    .detail(details.toString())
                    .error()
                    .add("messages");
        } catch (Throwable ignored) {
            // No FacesContext available; skip UI message safely.
        }
    }
}