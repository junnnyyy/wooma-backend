package dotolhee.daramhee.woomabackend.config

import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.jasypt.iv.RandomIvGenerator
import org.jasypt.salt.RandomSaltGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.ResourceUtils
import java.io.File


@Configuration
class JasyptConfig {
    /**
     * @NOTE https://github.com/ulisesbocchio/jasypt-spring-boot?tab=readme-ov-file
     * */
    @Bean("jasyptStringEncryptor")
    fun stringEncryptor(): StringEncryptor {

        val keyFile: File = ResourceUtils.getFile("classpath:key/secret.key")
        val key = keyFile.readText(Charsets.UTF_8)
        val encryptor = PooledPBEStringEncryptor()
        val config = SimpleStringPBEConfig()
        config.password = key
        config.algorithm = "PBEWithMD5AndDES"
        config.keyObtentionIterations = 1000
        config.poolSize = 1
        config.saltGenerator = RandomSaltGenerator()
        config.ivGenerator = RandomIvGenerator()
        config.stringOutputType = "base64"
        encryptor.setConfig(config)

        // 암복호화 테스트
        val encryptValue = encryptor.encrypt(" ")
        print("암호화 된 값 $encryptValue\n")
        val decryptValue = encryptor.decrypt(encryptValue)
        println("복호화 된 값 $decryptValue\n")
        return encryptor
    }
}