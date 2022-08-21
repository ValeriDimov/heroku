package bg.softuni.timeforschool.config;

import bg.softuni.timeforschool.service.OfferService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TimeForSchoolMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    private final OfferService offerService;

    public TimeForSchoolMethodSecurityConfig(OfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new TimeForSchoolSecurityExpressionHandler(offerService);
    }
}
