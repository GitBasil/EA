package FinalPractice.classes;

import java.util.ArrayList;
import java.util.List;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Component
@ConfigurationProperties(prefix = "myapp.properties")
@Validated
public class ApplicationProperties {
    private String applicationName;
    @NotBlank
    private String version;
    private String serverUrl;
    private String serverName;
    private List<String> countries = new ArrayList<>();
    @Valid
    private User user = new User();

    public String getApplicationName() {
        return this.applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getServerUrl() {
        return this.serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getServerName() {
        return this.serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public List<String> getCountries() {
        return this.countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "{" +
            " applicationName='" + getApplicationName() + "'" +
            ", version='" + getVersion() + "'" +
            ", serverUrl='" + getServerUrl() + "'" +
            ", serverName='" + getServerName() + "'" +
            ", countries='" + getCountries() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }

    class User {
        private String firstname;
        private String lastname;
        @Email
        private String username;
        @Size(min=8,max=16)
        private String password;

        public String getFirstname() {
            return firstname;
        }
        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }
        public String getLastname() {
            return lastname;
        }
        public void setLastname(String lastname) {
            this.lastname = lastname;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String toString() {
            return "{" +
                " Firstname='" + getFirstname() + "'" +
                ", Lastname='" + getLastname() + "'" +
                ", Password='" + getPassword() + "'" +
                ", Username='" + getUsername() + "'" +
                "}";
        }
    }
}
