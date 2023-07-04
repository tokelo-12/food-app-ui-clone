```kotlin
package com.example.basiclayoutcodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.*





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicLayoutcodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier){
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier
            .heightIn(min = 56.dp) //min height allows text-field to grow in size when user increases font size
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        },
        colors = TextFieldDefaults.textFieldColors(

        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))},
    )
}


@Composable
fun AlignYourBodyElement(
    modifier: Modifier = Modifier,
    @DrawableRes drawable: Int,
    @StringRes text: Int,
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape),
            //Scales picture correctly
            contentScale = ContentScale.Crop,
        )
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.paddingFromBaseline(
                top = 24.dp, bottom = 8.dp
            )
        )
    }
}

@Composable
fun AlignYourBodyRow(modifier: Modifier = Modifier){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier,
    ){
//        For each item in the provided alignYourBodyData, you emit a AlignYourBodyElement composable.
        items(alignYourBodyData){ item ->
            AlignYourBodyElement(drawable = item.drawable, text = item.text )
        }
    }
}

@Composable
fun FavouriteCollectionCard (
    modifier: Modifier = Modifier,
    @DrawableRes drawable: Int,
    @StringRes text: Int,
){
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier,
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(192.dp),

            ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(56.dp),

                )

            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier.height(120.dp),
        //horizontal padding for children
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(favoriteCollectionsData) { item ->
            FavouriteCollectionCard(
                modifier = Modifier
                    .height(56.dp),
                item.drawable, item.text)
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Column(modifier){
        Text(
            text = stringResource(title).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.labelLarge,
            modifier = modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)

        )
        content()
    }

}



@Composable
fun HomeScreen(modifier: Modifier = Modifier){
    Column(
        modifier
            .verticalScroll(rememberScrollState()) //adds vertical scrolling
            .padding(vertical = 16.dp)
    ){
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favourite_collections){
            FavoriteCollectionsGrid()
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}






    private val alignYourBodyData = listOf(
        R.drawable.ab1_inversions to R.string.ab1_inversions,
        R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
        R.drawable.ab3_stretching to R.string.ab3_stretching,
        R.drawable.ab4_tabata to R.string.ab4_tabata,
        R.drawable.ab5_hiit to R.string.ab5_hiit,
        R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
    ).map { DrawableStringPair(it.first, it.second) }

    private val favoriteCollectionsData = listOf(
        R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
        R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
        R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
        R.drawable.fc4_self_massage to R.string.fc4_self_massage,
        R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
        R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
    ).map { DrawableStringPair(it.first, it.second) }



    private data class DrawableStringPair(
        @DrawableRes val drawable: Int,
        @StringRes val text: Int
    )

    @Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
    @Composable
    fun ScreenContentPreview() {
        BasicLayoutcodelabTheme { HomeScreen() }
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
    @Composable
    fun HomeSectionPreview() {
        BasicLayoutcodelabTheme {
            HomeSection(R.string.align_your_body){
                AlignYourBodyRow()
            }
        }
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
    @Composable
    fun FavoriteCollectionsGridPreview() {
        BasicLayoutcodelabTheme { FavoriteCollectionsGrid() }
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
    @Composable
    fun AlignYourBodyRowPreview() {
        BasicLayoutcodelabTheme { AlignYourBodyRow() }
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
    @Composable
    fun FavouriteCollectionCardPreview() {
        BasicLayoutcodelabTheme {
            FavouriteCollectionCard(
                modifier = Modifier.padding(4.dp),
                drawable = R.drawable.fc2_nature_meditations,
                text = R.string.fc2_nature_meditations,
            )
        }
    }




    @Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
    @Composable
    fun SearchBarPreview(){
        BasicLayoutcodelabTheme{
            SearchBar()
        }
    }
    @Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
    @Composable
    fun AlignYourBodyElementPreview() {
        BasicLayoutcodelabTheme {
            AlignYourBodyElement(
                modifier = Modifier.padding(8.dp),
                drawable = R.drawable.ab1_inversions,
                text = R.string.ab1_inversions
            )
        }
    }





```