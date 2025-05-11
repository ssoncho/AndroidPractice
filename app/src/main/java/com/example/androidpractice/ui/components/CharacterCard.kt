package com.example.androidpractice.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.androidpractice.R
import com.example.androidpractice.characterList.data.mock.CharactersData
import com.example.androidpractice.characterList.domain.entity.CharacterEntity
import com.example.androidpractice.ui.theme.LocalDefaultImage
import com.example.androidpractice.ui.theme.Spacing

@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    character: CharacterEntity?
) {
    character ?: run { return FullScreenMessage(stringResource(R.string.character_not_found)) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.large)
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(8.dp)),
            model = character.image ?: LocalDefaultImage.current,
            contentDescription = null
        )

        Text(
            text = character.name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = Spacing.small)
        )

        Text(
            text = stringResource(R.string.biography_title),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = Spacing.medium)
        )

        Box(
            modifier = Modifier
                .padding(top = Spacing.small)
        ){
            Column {
                OptionalText("Species", character.species)
                OptionalText("Gender", character.gender)
                OptionalText("Born", character.born)
                OptionalText("Died", character.died)
                OptionalText("Blood status", character.bloodStatus)
                OptionalText("Marital status", character.maritalStatus)
                OptionalText("Nationality", character.nationality)
            }
        }

        Text(
            text = stringResource(R.string.magical_facts_title),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = Spacing.medium)
        )

        FactsConstraintLayout(
            patronus = character.patronus,
            boggart = character.boggart,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            textStyle = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CharacterCardPreview() = CharacterCard(character = CharactersData.characters.first())