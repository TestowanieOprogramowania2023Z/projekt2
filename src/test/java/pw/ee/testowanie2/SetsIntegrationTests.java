package pw.ee.testowanie2;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "pw.ee.testowanie2",
        plugin = {"json:target/cucumber.json"}
)
public class SetsIntegrationTests {
}