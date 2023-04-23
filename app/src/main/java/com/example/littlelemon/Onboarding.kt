package com.example.littlelemon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.LittleLemonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.littlelemonimgtxt_nobg),
                contentDescription = stringResource(R.string.app_logo),
                modifier = Modifier
                    .fillMaxWidth(0.5F)
                    .padding(horizontal = 20.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(LittleLemonColor.green),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.onboarding_description),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = stringResource(R.string.personal_information),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text(stringResource(R.string.first_name)) }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                value = lastName,
                onValueChange = { lastName = it},
                label = { Text(stringResource(R.string.last_name)) }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                value = email,
                onValueChange = { email = it},
                label = { Text(stringResource(R.string.email)) }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = { },
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(2.dp, LittleLemonColor.gold),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = LittleLemonColor.yellow)
            ) {
                Text(
                    text = stringResource(id = R.string.register),
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
fun OnboardingPreview() {
    Onboarding()
}
