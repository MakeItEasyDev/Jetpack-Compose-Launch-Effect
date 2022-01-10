package com.jetpack.launcheffect

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.jetpack.launcheffect.ui.theme.LaunchEffectTheme
import kotlinx.coroutines.delay
import java.lang.Exception

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchEffectTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "Launch / Side Effect",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            )
                        }
                    ) {
                        LaunchEffectScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun LaunchEffectScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var timeDuration by remember { mutableStateOf(1000L) }

        Button(
            onClick = { timeDuration -= 1000 }
        ) {
            Text(
                text = "-1 Second"
            )
        }

        Text(
            text = timeDuration.toString()
        )

        Button(
            onClick = { timeDuration += 1000 }
        ) {
            Text(
                text = "+1 Second"
            )
        }

        Timer(timeDuration = timeDuration)
    }
}

@Composable
fun Timer(timeDuration: Long) {
    val context = LocalContext.current

    LaunchedEffect(key1 = timeDuration, block = {
        try {
            startTime(timeDuration) {
                Toast.makeText(context, "Timer Ended", Toast.LENGTH_SHORT).show()
            }
        } catch (exp: Exception) {
            Toast.makeText(context, "Timer canceled", Toast.LENGTH_SHORT).show()
        }
    })
}

suspend fun startTime(time: Long, onTimerEnd: () -> Unit) {
    delay(timeMillis = time)
    onTimerEnd()
}

























