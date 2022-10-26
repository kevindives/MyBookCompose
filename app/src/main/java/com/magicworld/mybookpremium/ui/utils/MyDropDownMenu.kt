package com.magicworld.mybookpremium.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.magicworld.mybookpremium.model.MyColors.*

@Composable
fun MyDropDownMenu(
    showMenu: Boolean,
    onColorSelected: (Long) -> Unit,
    onDismissMenu: () -> Unit
) {
    val optionColors = getColorsButton()

    Column(Modifier.fillMaxWidth()) {
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { onDismissMenu() },
            modifier = Modifier.fillMaxWidth()
        ) {

            DropdownMenuItem(onClick = { }) {
                Text(text = "color" , Modifier.padding(8.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    optionColors.forEach { color ->
                        Icon(
                            imageVector = Icons.Default.Circle,
                            contentDescription = "",
                            tint = Color(color),
                            modifier = Modifier
                                .padding(8.dp)
                                .size(30.dp)
                                .clickable {
                                    onColorSelected(color)
                                    onDismissMenu()
                                }
                        )
                    }
                }
            }
        }
    }

}

fun getColorsButton(): List<Long> {
    return listOf(
        AliceAzul.color,
        RosaBrumosa.color,
        Mocasin.color,
        BlancoAntiguo.color,
        RosaClaro.color,
        Cardo.color,
        Ciruela.color,
        AzulCielo.color,
        AzulCieloClaro.color,
        AzulPalido.color,
        AguaMarina.color,
        CianClaro.color,
        Agua.color,
        VerdePalido.color,
        VerdeParimaveraMedio.color,
        Caqui.color,
        OroPalido.color,
        SalmonClaro.color,
        Coral.color

    )
}


