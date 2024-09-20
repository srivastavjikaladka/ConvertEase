package com.example.unitconverter.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun unitConverter(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }

    fun convertUnits(){
        // ?: elvis operator :if the value goes null then it displays
        // the defalt value 0.0

        val inputValueDouble = inputValue.toDoubleOrNull()?:0.0
        val result = (inputValueDouble * conversionFactor.value/oConversionFactor.value).roundToInt()
        outputValue = result.toString()

    }
    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        //Column will stacked below each other UI elements
        Text("Unit Converter")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value =inputValue , onValueChange ={inputValue=it},
            label = { Text(text = "Enter value") })

        // Places elements horizontally

        Row {
            val context = LocalContext.current
            Box{
                //Input button
                Button(onClick = {iExpanded = true
                    Toast.makeText(context,"Select Input Unit",
                        Toast.LENGTH_LONG).show()
                }) {
                    Text(text = "Select")
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription ="Arrow Down" )

                }


                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false }

                ) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        iExpanded=false
                        inputUnit = "Centimeters"
                        conversionFactor.value = 0.01
                        convertUnits()

                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        iExpanded=false
                        inputUnit = "Meters"
                        conversionFactor.value = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        iExpanded=false
                        inputUnit = "Feets"
                        conversionFactor.value = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters ") }, onClick = {
                        iExpanded=false
                        inputUnit = "Millimeters"
                        conversionFactor.value = 0.001
                        convertUnits()
                    })

                }

            }
            Spacer(modifier = Modifier.width(32.dp))

            Box{
                //output button
                Button(onClick = {oExpanded = true
                    Toast.makeText(context
                        ,"Select Output Unit",
                        Toast.LENGTH_LONG).show()
                }) {
                    Text(text = "Select")
                    Text(text = outputUnit)

                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false })
                {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        oExpanded=false
                        outputUnit = "Centimeters"
                        oConversionFactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        oExpanded=false
                        outputUnit = "Meters"
                        oConversionFactor.value = 1.00
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        oExpanded=false
                        outputUnit = "Feet"
                        oConversionFactor.value = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                        oExpanded=false
                        outputUnit = "Millimeters"
                        oConversionFactor.value = 0.001
                        convertUnits()
                    })
                }

            }

        }
        Text(text = "Result :$outputValue")

    }
}