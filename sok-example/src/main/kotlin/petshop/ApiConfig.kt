package petshop


import com.maiboroda.swagger.Api
import com.maiboroda.swagger.Version
import io.swagger.models.Swagger
import io.swagger.util.Yaml
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File

@Configuration
open class ApiConfig {

    @Bean
    open fun getPetShopApi():Api {
        val petShop10 = getPetShopSwagger()
        return Api("petshop", mapOf(Version(1,0) to petShop10))
    }

    private fun getPetShopSwagger(): Swagger {
        val file = javaClass.classLoader.getResource("petshop.yml").file
        return Yaml.mapper().readValue(File(file), Swagger::class.java)
    }
}