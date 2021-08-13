import napi.configurate.yaml.conf.Configuration;
import napi.configurate.yaml.source.ConfigSources;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestConfiguration {

    @Test
    public void testResource() throws IOException {
        Configuration conf = Configuration.builder()
                .source(ConfigSources.resource("/test.yml", this)
                        .copyTo("E:\\"))
                .build();

        conf.reload();

        String name = conf.getNode("name").getString();
        System.out.println("Name " + name);
        assert "Vasya".equals(name);
    }
}
