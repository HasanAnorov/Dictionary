package com.ierusalem.dictionary.features.landing.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.dictionary.R
import com.ierusalem.dictionary.theme.DictionaryTheme

@Composable
fun LandingScreen(
    onNavigate: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color(0xFF14037D))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Image(
                modifier = Modifier.size(88.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null
            )
            Text(
                text = "Dictionary",
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 72.dp),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp, bottom = 8.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onNavigate() },
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                ),
                border = BorderStroke(1.dp, Color.White),
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        content = {
                            Image(
                                modifier = Modifier
                                    .padding(start = 8.dp),
                                painter = painterResource(id = R.drawable.flag_for_uzbekistan),
                                contentDescription = null,
                            )
                            Text(
                                text = "Uzbek",
                                modifier = Modifier
                                    .padding(vertical = 16.dp, horizontal = 4.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "-",
                                modifier = Modifier
                                    .padding(vertical = 16.dp, horizontal = 4.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                            Image(
                                modifier = Modifier
                                    .padding(start = 8.dp),
                                painter = painterResource(id = R.drawable.flags_uk),
                                contentDescription = null,
                            )
                            Text(
                                text = "English",
                                modifier = Modifier
                                    .padding(vertical = 16.dp, horizontal = 4.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                    )
                },
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp, bottom = 8.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onNavigate() },
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                ),
                border = BorderStroke(1.dp, Color.White),
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        content = {
                            Image(
                                modifier = Modifier
                                    .padding(start = 8.dp),
                                painter = painterResource(id = R.drawable.flags_uk),
                                contentDescription = null,
                            )
                            Text(
                                text = "English",
                                modifier = Modifier
                                    .padding(vertical = 16.dp, horizontal = 4.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "-",
                                modifier = Modifier
                                    .padding(vertical = 16.dp, horizontal = 4.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                            Image(
                                modifier = Modifier
                                    .padding(start = 8.dp),
                                painter = painterResource(id = R.drawable.flag_for_uzbekistan),
                                contentDescription = null,
                            )
                            Text(
                                text = "Uzbek",
                                modifier = Modifier
                                    .padding(vertical = 16.dp, horizontal = 4.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                    )
                },
            )
        }
    )
}


@Preview
@Composable
private fun LandingScreenPreview() {
    DictionaryTheme {
        LandingScreen(
            onNavigate = {}
        )
    }
}

@Preview
@Composable
private fun LandingScreenPreviewDark() {
    DictionaryTheme(darkTheme = true) {
        LandingScreen(
            onNavigate = {}
        )
    }
}