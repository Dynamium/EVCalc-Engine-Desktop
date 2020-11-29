@file:Suppress("FunctionName")

package org.dynamium.evcalc.gui

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun MainView() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter),
            text = "EVCalc Engine"
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp),
            ) {
                val textFieldRiderWeight = remember { mutableStateOf(TextFieldValue()) }

                OutlinedTextField(
                    value = textFieldRiderWeight.value,
                    onValueChange = { textFieldRiderWeight.value = it },
                    label = { Text(text = "Вес райдера") }
                )
                OutlinedTextField(
                    value = textFieldRiderWeight.value,
                    onValueChange = { textFieldRiderWeight.value = it },
                    label = { Text(text = "Емкость батареи") }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp)
            ) {
                val textFieldRiderWeight = remember { mutableStateOf(TextFieldValue()) }

                OutlinedTextField(
                    value = textFieldRiderWeight.value,
                    onValueChange = { textFieldRiderWeight.value = it },
                    label = { Text(text = "Вес райдера") }
                )
                OutlinedTextField(
                    value = textFieldRiderWeight.value,
                    onValueChange = { textFieldRiderWeight.value = it },
                    label = { Text(text = "Размер батареи") }
                )
            }
        }
    }
}