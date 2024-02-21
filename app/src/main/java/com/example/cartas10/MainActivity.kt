package com.example.cartas10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cartas10.ui.theme.Cartas10Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Cartas10Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Gray // Fondo gris
                ) {
                    FlippableCardList()
                }
            }
        }
    }
}

@Composable
fun FlippableCardList() {
    LazyColumn {
        items(10) { index ->
            FlippableCardWithImageAndText("Texto Arriba $index", index)
        }
    }
}
@Composable
fun FlippableCardWithImageAndText(frontText: String, index: Int) {
    var isFlipped by remember { mutableStateOf(false) }

    val rotationY by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(300), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable { isFlipped = !isFlipped }
            .graphicsLayer(rotationY = rotationY)
    ) {
        if (isFlipped) {
            BackContent()
        } else {
            // Mueve el fondo verde aquí para que esté presente en la parte delantera
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color(0xFF006400)) // Verde oscuro
            ) {
                FrontContent(frontText, index)
            }
        }
    }
}

@Composable
fun FrontContent(text: String, index: Int) {
    val cardTitle = when (index) {
        0 -> "Legolas"
        1 -> "Galadriel, reina de los elfos"
        2 -> "Arqueros de Sacellum"
        3 -> "Perfecta arrogante"
        4 -> "Lathril, Espada de los elfos"
        5 -> "Gilanra, Llamador de wirewood"
        6 -> "Sueños desenfrenados"
        7 -> "Elfo hoja solar"
        8 -> "Elfos de Llanowar"
        9 -> "Elfa llamaclanes"
        else -> "Título predeterminado"
    }

    val cardDescription = when (index) {
        0 -> "Criatura - Arquero elfo. Hacen 2 puntos de daño a la criatura atacante o bloqueadora objetivo."
        1 -> "Criatura Legendaria - Noble elfo. Al comienzo del combate en tu turno, si otro Elfo entró en el campo de batalla bajo tu control este turno, comenzando contigo cada jugador vota dominio o ruleta."
        2 -> "Criatura - Arquero elfo. Hacen 2 puntos de daño a la criatura atacante o bloqueadora objetivo."
        3 -> "Criatura - Guerrero elfo"
        4 -> "Criatura Legendaria - Noble elfo"
        5 -> "Criatura Legendaria - Druida elfo"
        6 -> "Conjuro"
        7 -> "Criatura - Guerrero elfo"
        8 -> "Invocar - Elfos"
        9 -> "Criatura - Druida elfo"
        else -> "Descripción "
    }

    val titleFont = Font(R.font.kodemono, FontWeight.Bold)
    val descriptionFont = Font(R.font.anta_regular, FontWeight.Normal)

    val titleStyle = LocalTextStyle.current.copy(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontFamily = titleFont.toFontFamily()
    )

    val descriptionStyle = LocalTextStyle.current.copy(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontFamily = descriptionFont.toFontFamily()
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = titleStyle.toSpanStyle()) {
                    append(cardTitle)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(id = getImageResourceId(index)),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(300.dp)
                .clip(shape = MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = descriptionStyle.toSpanStyle()) {
                    append(cardDescription)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}

@Composable
fun BackContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Gray) // Fondo gris
    ) {
        // Reemplaza el Text con una Image
        Image(
            painter = painterResource(id = R.drawable.magic), // Reemplaza "magic" con el nombre correcto de tu recurso de imagen
            contentDescription = null, // Puedes proporcionar una descripción si es necesario
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize() // La imagen ocupará todo el espacio disponible
        )
    }
}



@Composable
fun getImageResourceId(index: Int): Int {
    return when (index) {
        0 -> R.drawable.legolas
        1 -> R.drawable.galadriel
        2 -> R.drawable.elfa
        3 -> R.drawable.perfecta_arrogante
        4 -> R.drawable.lathril
        5 -> R.drawable.gilandra
        6 -> R.drawable.conjuro
        7 -> R.drawable.elfohoja
        8 -> R.drawable.llanowarl
        9 -> R.drawable.elfallamaclanes
        else -> R.drawable.ic_launcher_foreground
    }
}

@Preview(showBackground = true)
@Composable
fun FlippableCardWithImageAndTextPreview() {
    Cartas10Theme {
        FlippableCardList()
    }
}
