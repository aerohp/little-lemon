package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Colors
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Home(navController: NavHostController) {
    Column() {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),) {
            Image(
                painter = painterResource(id = R.drawable.littlelemonimgtxt_nobg),
                contentDescription = stringResource(R.string.app_logo),
                modifier = Modifier
                    .fillMaxWidth(0.5F)
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
            IconButton(
                onClick = {
                    navController?.navigate(Profile.route)
                },
                modifier = Modifier.align(Alignment.CenterEnd)) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Cart",
                    modifier = Modifier
                        .fillMaxWidth(0.22F)
                        .padding(horizontal = 20.dp).align(Alignment.CenterEnd)

                )
            }
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    Home(rememberNavController())
}
