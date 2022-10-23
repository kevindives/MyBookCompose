package com.magicworld.mybookpremium.model

 sealed class MyColors(val color: Long){
     object White: MyColors(0xFFFFFFFF)
     object AliceAzul: MyColors(0xFFF0F8FF)
     object RosaBrumosa: MyColors(0xFFFFE4E1)
     object Mocasin: MyColors(0xFFFFE4B5)
     object BlancoAntiguo: MyColors(0xFFFAEBD7)
     object RosaClaro: MyColors(0xFFFFB6C1)
     object Cardo: MyColors(0xFFD8BFD8)
     object Ciruela: MyColors(0xFFDDA0DD)
     object AzulCielo: MyColors(0xFF87CEEB)
     object AzulCieloClaro: MyColors(0xFF87CEFA)
     object AzulPalido: MyColors(0xFFB0E0E6)
     object AguaMarina: MyColors(0xFF7FFFD4)
     object CianClaro: MyColors(0xFFE0FFFF)
     object Agua: MyColors(0xFF00FFFF)
     object VerdePalido: MyColors(0xFF00FFFF)
     object VerdeParimaveraMedio: MyColors(0xFF00FA9A)
     object Caqui: MyColors(0xFFF0E68C)
     object OroPalido: MyColors(0xFFEEE8AA)
     object SalmonClaro: MyColors(0xFFFFA07A)
     object Coral: MyColors(0xFFFF7F50)
}

