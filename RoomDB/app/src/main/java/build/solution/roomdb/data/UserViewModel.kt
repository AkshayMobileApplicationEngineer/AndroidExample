package build.solution.roomdb.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao: UserDao = UserDatabase.getDatabase(application).userDao()
    private val repository: UserRepository
    var getAllUsers: LiveData<List<User>>

    init {
        repository = UserRepository(userDao)
        getAllUsers = repository.getAllUsers
    }

    fun addUser(user: User) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)  // Assuming you have this method in your repository
        }
    }
}
