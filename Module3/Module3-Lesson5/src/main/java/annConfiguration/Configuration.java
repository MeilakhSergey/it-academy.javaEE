package annConfiguration;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@org.springframework.context.annotation.Configuration
@Import(PersonConfiguration.class)
@EnableAspectJAutoProxy
public class Configuration {
}
