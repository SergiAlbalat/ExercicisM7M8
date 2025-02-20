package cat.itb.m78.exercices.settings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class CountViewsViewModel : ViewModel(){
    private val settings: Settings = Settings()
    val count: Int? = settings["count"]
    init {
        if(count == null){
            settings["count"] = 2
        }else{
            settings["count"] = count+1
        }
    }
}

@Composable
fun CountViewsApp(){
    val viewModel = viewModel{CountViewsViewModel()}
    CountViewsView(viewModel.count)
}

@Composable
fun CountViewsView(
    count: Int?
){
    Text("You have opened this app ${count.toString()} times")
}