package dotolhee.daramhee.woomabackend

import io.kotest.core.spec.style.AnnotationSpec
import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.jasypt.iv.RandomIvGenerator
import org.jasypt.salt.RandomSaltGenerator


/**
 * application.yml 용 jasypt 암호화 키 생성시 이용
 * getJasyptEncryptor() 함수에 각 환경별 암호화 키를 전달하여 생성
 */
class JasyptTest : AnnotationSpec() {
    @Test
    fun encryptionTest() {
        val encryptor = getJasyptEncryptor("test_password") // <-- 여기에 암호화 키 입력 - 배포 안되게 주의

        // 암호화 할 텍스트
        val text = "test"
        val encryptedText = encryptor.encrypt(text)
        println(encryptedText)
        val decryptedText = encryptor.decrypt(encryptedText)
        println(decryptedText)

    }
}


/**
 * Jasypt 암호화 객체 생성
 * Spring Bean 주입 부분을 참고하여 제작
 */
fun getJasyptEncryptor(password: String): StringEncryptor {
    val encryptor = PooledPBEStringEncryptor()
    val config = SimpleStringPBEConfig()

    config.password = password
    config.algorithm = "PBEWITHHMACSHA512ANDAES_256"
    config.stringOutputType = "base64"
    config.keyObtentionIterations = 1000
    config.saltGenerator = RandomSaltGenerator()
    config.ivGenerator = RandomIvGenerator()
    config.poolSize = 1

    encryptor.setConfig(config)

    return encryptor
}
