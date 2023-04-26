package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonColor
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun Home(navController: NavHostController, database: AppDatabase) {

    Column {
        TopHeader(navController)
        UpperPanel(database)
    }
}

@Composable
fun TopHeader(navController: NavHostController) {
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
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Cart",
                modifier = Modifier
                    .fillMaxWidth(0.22F)
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}

@Composable
fun UpperPanel(database: AppDatabase) {
    val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())

    var orderMenuItems by remember { mutableStateOf(false) }

    var menuItems = if(orderMenuItems) databaseMenuItems.sortedBy { it.title } else databaseMenuItems

    Column(
        modifier = Modifier
            .background(LittleLemonColor.green)
            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.title),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = LittleLemonColor.yellow
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth(0.6f)) {
                Text(
                    text = stringResource(id = R.string.location),
                    style = MaterialTheme.typography.h1,
                    color = LittleLemonColor.cloud
                )
                Text(
                    text = stringResource(id = R.string.description),
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(top = 10.dp, end = 5.dp),
                    color = LittleLemonColor.cloud
                )
            }
            Image(
                painter = painterResource(id = R.drawable.hero),
                contentDescription = stringResource(R.string.hero_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(150.dp).clip(RoundedCornerShape(10.dp))
            )
        }

        var searchPhrase by remember { mutableStateOf("") }
        TextField(
            modifier = Modifier
                .fillMaxWidth().padding(top = 16.dp),
            singleLine = true,
            value = searchPhrase,
            onValueChange = { searchPhrase = it},
            label = { Text(stringResource(R.string.search_label)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = stringResource(R.string.search_label)
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Red)
        )

        if(searchPhrase.isNotEmpty()) {
            menuItems = databaseMenuItems.filter { it.title.contains(searchPhrase, true) }
        }
    }
    LowerPanel(menuItems)
}

@Composable
fun LowerPanel(menuItems: List<MenuItemRoom> = listOf()) {
    Column {
        LazyColumn {
            items(
                items = menuItems,
                itemContent = { menuItem ->
                    MenuItem(menuItem)
                }
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(menuItem: MenuItemRoom) {
    Card {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = menuItem.title,
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = menuItem.description,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(0.dp, 5.dp, 0.dp, 5.dp)
                )
                Text(
                    text = "${menuItem.price}",
                    style = MaterialTheme.typography.body2
                )
            }
            GlideImage(
                model = menuItem.image,
                contentDescription = menuItem.title,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxSize(),
                contentScale = ContentScale.Fit,
            )
        }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        thickness = 1.dp,
        color = LittleLemonColor.yellow
    )
}