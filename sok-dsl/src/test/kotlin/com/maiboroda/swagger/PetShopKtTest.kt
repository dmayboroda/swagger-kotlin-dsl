package com.maiboroda.swagger

import io.swagger.models.Swagger
import io.swagger.util.Yaml
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test
import java.io.File

class PetShopKtTest {

    @Test
    fun should_load_pet_shop_yml() {
        val file = javaClass.classLoader.getResource("petshop.yml").file
        val swagger = Yaml.mapper().readValue(File(file), Swagger::class.java)

        Assert.assertThat(swagger.paths["/pet"]?.post?.parameters?.first()?.`in`, Matchers.equalTo("body"))
    }

}