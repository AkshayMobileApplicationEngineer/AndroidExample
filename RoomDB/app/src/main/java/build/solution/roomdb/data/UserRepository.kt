package build.solution.roomdb.data

import androidx.lifecycle.LiveData

class UserRepository (private val userDao: UserDao) {
    val getAllUsers:LiveData<List<User>> = UserDao.getAllUsers()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}