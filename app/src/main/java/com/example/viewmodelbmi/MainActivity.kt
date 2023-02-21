package com.example.viewmodelbmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodelbmi.ui.theme.ViewModelBMITheme

class BMIViewModel: ViewModel() {
    var weightInput by mutableStateOf("")
    var heightInput by mutableStateOf("")

    var resultValue: Float = 0.0f
        get() {
            return calculate()
        }

    private var weight: Int = 0
        get() {
            return weightInput.toIntOrNull() ?: 0
        }
    private var height: Float = 0.0f
        get() {
            return heightInput.toFloatOrNull() ?: 0.0f
        }

    private fun calculate(): Float {
        return if (weight > 0 && height > 0) {
            weight/(height * height)
        }
        else 0.0f
    }
    fun changeWeightInput(value: String) {
        weightInput = value
    }
    fun changeHeightInput(value: String) {
        heightInput = value
    }
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewModelBMITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BMI()
                }
            }
        }
    }
}

@Composable
fun Heading(title: String) {
    Text(
        text = title,
        fontSize = 22.sp,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
    )
}

@Composable
fun BMI(BMIViewModel: BMIViewModel = viewModel()) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Heading(title = stringResource(R.string.title))
        HeightField(heightInput = BMIViewModel.heightInput, onValueChange = {BMIViewModel.changeHeightInput(it)})
        WeightField(weightInput = BMIViewModel.weightInput, onValueChange = {BMIViewModel.changeWeightInput(it)})
        Text(
            text = stringResource(R.string.result, BMIViewModel.resultValue)
        )
    }
}

@Composable
fun HeightField(heightInput: String, onValueChange:(String) -> Unit) {
    OutlinedTextField(
        value = heightInput,
        onValueChange = onValueChange,
        label = {Text(text = stringResource(R.string.v1))},
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )
}
@Composable
fun WeightField(weightInput: String, onValueChange:(String) -> Unit) {
    OutlinedTextField(
        value = weightInput,
        onValueChange = onValueChange,
        label = {Text(text = stringResource(R.string.v2))},
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )
}











