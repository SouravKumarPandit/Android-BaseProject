
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vrlocal.android.baseproject.ui.widgets.login.data.User

//@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUser(): LiveData<User>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

//    @Delete
//    suspend fun removeUser(user: User)


}
