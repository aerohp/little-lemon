package com.example.littlelemon

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun Profile(navController: NavHostController) {

    val activity = LocalContext.current as Activity
    val sp = activity.getSharedPreferences(Constants.SP_USER_DATA, Context.MODE_PRIVATE)

    var firstName = sp.getString(Constants.SP_KEY_FIRST_NAME, null)
    var lastName = sp.getString(Constants.SP_KEY_LAST_NAME, null)
    var email = sp.getString(Constants.SP_KEY_EMAIL, null)

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
            firstName?.let {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = it,
                    onValueChange = { firstName = it },
                    label = { Text(stringResource(R.string.first_name)) },
                    readOnly = true
                )
            }
            lastName?.let {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = it,
                    onValueChange = { lastName = it },
                    label = { Text(stringResource(R.string.last_name)) },
                    readOnly = true
                )
            }
            email?.let {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = it,
                    onValueChange = { email = it },
                    label = { Text(stringResource(R.string.email)) },
                    readOnly = true
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = {
                    sp.edit().clear().apply()
                    navController?.navigate(Onboarding.route)
                },
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(2.dp, LittleLemonColor.gold),
                colors = ButtonDefaults.buttonColors(contentColor = LittleLemonColor.yellow)
            ) {
                Text(
                    text = stringResource(id = R.string.logout),
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    Profile(rememberNavController())
}