package com.example.unitconverter

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UnitConverter() {
    var inputValue by remember {mutableStateOf("")}
    var outputValue by remember {mutableStateOf("")}
    var iUnit by remember {mutableStateOf("Meter")}
    var oUnit by remember {mutableStateOf("Meter")}
    var iExpanded by remember {mutableStateOf(false)}
    var oExpanded by remember {mutableStateOf(false)}
    val conversionFactor= remember { mutableStateOf(1.00) }
    val oconversionFactor= remember { mutableStateOf(1.00) }


//    val Customstyle= androidx.compose.ui.text.TextStyle(
//        fontFamily = FontFamily.Default,
//        fontSize = 32.sp ,
//        color = Color.Red
//    )

    fun convertUnits()
    {
        val inputValuedouble=inputValue.toDoubleOrNull()?:0.0
        val result=(inputValuedouble * conversionFactor.value * 100.0/oconversionFactor.value).roundToInt()/100.0
        outputValue=result.toString()
    }


    Column(modifier = Modifier.fillMaxSize(),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
        ) {
        //Here each element will be stacked in one column
        Text(text = "UNIT CONVERTER", style =  MaterialTheme.typography.headlineLarge)//Customstyle)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue=it
            convertUnits()
        },label={ Text(text = "Enter a value")})
        Spacer(modifier = Modifier.height(16.dp))

    Row {
            //Here the element will be stacked in one row
        //Input Box
        Box{
            //Input Button
            Button(onClick = {iExpanded=true})
            {
                Text(iUnit)
            Icon(Icons.Default.ArrowDropDown,contentDescription = "Dropdown Arrow")}
            DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false })
            {
                DropdownMenuItem(text = { Text("Centimeter")},
                    onClick = {
                        iExpanded=false
                        iUnit="Centimeter"
                        conversionFactor.value=0.01
                        convertUnits()
                    })
                DropdownMenuItem(text = { Text("Meter")},
                    onClick = {
                        iExpanded=false
                        iUnit="Meter"
                        conversionFactor.value=1.00
                        convertUnits()
                    })
                DropdownMenuItem(text = { Text("Millimeter")},
                    onClick = {
                        iExpanded=false
                        iUnit="Millimeter"
                        conversionFactor.value=0.001
                        convertUnits()
                    })
                DropdownMenuItem(text = { Text("Feet")},
                    onClick = {
                        iExpanded=false
                        iUnit="Centimeter"
                        conversionFactor.value=0.3048
                        convertUnits()
                    })
                
            }
        }
        Spacer(modifier = Modifier.width(16.dp))

        //Output Box
        Box{
            //Output Button
            Button(onClick = { oExpanded=true }) {Text(oUnit)
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop Down Arrow")
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false })
                {
                    DropdownMenuItem(text = { Text("Meter")},
                        onClick = {
                            oExpanded=false
                            oUnit="Meter"
                            oconversionFactor.value=1.0
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Centimeter")},
                        onClick = {
                            oExpanded=false
                            oUnit="Centimeter"
                            oconversionFactor.value=0.01
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Millimeter")},
                        onClick = {
                            oExpanded=false
                            oUnit="Millimeter"
                            oconversionFactor.value=0.001
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Feet")},
                        onClick = {
                            oExpanded=false
                            oUnit="Feet"
                            oconversionFactor.value=0.3048
                            convertUnits()
                        })

                }
            }
        }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputValue $oUnit", style = MaterialTheme.typography.headlineMedium)
      }

}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview()
{
    UnitConverter()
}