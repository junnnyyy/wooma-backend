package dotolhee.daramhee.woomabackend.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    open val createdAt: LocalDateTime? = null

    @Column
    @UpdateTimestamp
    open val updatedAt: LocalDateTime? = null
}