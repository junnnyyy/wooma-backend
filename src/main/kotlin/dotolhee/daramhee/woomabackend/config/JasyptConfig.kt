package dotolhee.daramhee.woomabackend.config

import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.jasypt.iv.RandomIvGenerator
import org.jasypt.salt.RandomSaltGenerator
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.ResourceUtils
import java.io.File
import java.io.IOException


@Configuration
class JasyptConfig(
    @Value("\${jasypt.encryptor.algorithm}")
    private val algorithm: String,
    @Value("\${jasypt.encryptor.pool-size}")
    private val poolSize: Int,
    @Value("\${jasypt.encryptor.string-output-type}")
    private val stringOutputType: String?,
    @Value("\${jasypt.encryptor.key-obtention-iterations}")
    private val keyObtentionIterations: Int,
) {
    /**
     * @NOTE https://github.com/ulisesbocchio/jasypt-spring-boot?tab=readme-ov-file
     * */
    @Bean("jasyptStringEncryptor")
    fun stringEncryptor(): StringEncryptor {

        val encryptor = PooledPBEStringEncryptor()
        val config = SimpleStringPBEConfig()
        config.password = getJasyptEncryptorPassword()
        config.algorithm = algorithm
        config.keyObtentionIterations = keyObtentionIterations
        config.poolSize = poolSize
        config.saltGenerator = RandomSaltGenerator()
        config.ivGenerator = RandomIvGenerator()
        config.stringOutputType = stringOutputType
        encryptor.setConfig(config)

        // 암복호화 테스트
        val encryptValue = encryptor.encrypt(" ")
        print("암호화 된 값 $encryptValue\n")
        val decryptValue = encryptor.decrypt(encryptValue)
        println("복호화 된 값 $decryptValue\n")
        return encryptor
    }

    private fun getJasyptEncryptorPassword(): String {
        return try {
            val keyFile: File = ResourceUtils.getFile("classpath:key/secret.key")
            keyFile.readText(Charsets.UTF_8)
        } catch (e: IOException) {
            throw RuntimeException("!!========!! Jasypt key file이 존재하지 않습니다. !!========!!")
        }
    }
}