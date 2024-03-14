package dotolhee.daramhee.setakfire.global.config

import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.PathItem
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.media.IntegerSchema
import io.swagger.v3.oas.models.parameters.QueryParameter
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    fun swaggerConfig(): OpenAPI {
        return OpenAPI()
            .components(Components())
            .info(
                Info()
                    .title("우리들 사이에 마이너스는 없어 API")
                    .description("우리들 사이에 마이너스는 없어 API")
                    .version("1.0.0")
            ).addServersItem(Server().url("http://localhost:8080/"))
            .path(
                "wooma", PathItem()
                    .get(
                        Operation()
                            .summary("Get an example")
                            .description("Get an example")
                            .operationId("getExample")
                            .addParametersItem(
                                QueryParameter()
                                    .name("id")
                                    .`in`(ParameterIn.QUERY.toString())
                                    .required(true)
                                    .schema(IntegerSchema())
                            )
                    )
            )
    }
}