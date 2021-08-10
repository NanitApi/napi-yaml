import napi.configurate.yaml.util.ConfigUtil;
import napi.configurate.yaml.Configuration;
import napi.configurate.yaml.source.ConfigSources;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

public class TestConfiguration {

    @Test
    public void testResource() throws IOException {
        Configuration conf = Configuration.builder()
                .source(ConfigSources.resource("test.yml", this)
                        .copyTo("E:\\"))
                .build();

        conf.reload();

        String name = conf.getNode("name").getString();
        System.out.println("Name " + name);
        assert "Vasya".equals(name);
    }

    @Test
    public void testMapping() throws IOException {
        Configuration conf = Configuration.builder()
                .source(ConfigSources.resource("test.yml", this))
                .build();

        conf.reload();

        Map<String, Object> map = ConfigUtil.mapValues(conf);

        System.out.println(map);
    }
}
