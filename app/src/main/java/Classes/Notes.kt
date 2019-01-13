package Classes

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(
    tableName = "notes"
)

class Notes ( val title:String, val content:String) {
    @PrimaryKey(autoGenerate=true)
    var id: Long = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Notes

        if (title != other.title) return false
        if (content != other.content) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }
}