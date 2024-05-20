package com.sedat.cryptoapp.view.anim

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sedat.cryptoapp.view.anim.AnimExtension.enterAnim
import com.sedat.cryptoapp.view.anim.AnimExtension.exitAnim

object AnimExtension{
    val exitAnim = slideOutHorizontally (
        targetOffsetX = {1000},
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
    ) + fadeOut(animationSpec = tween(1000))

    val enterAnim = slideInHorizontally(
        initialOffsetX = {1000},
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
    ) //+ fadeIn(animationSpec = tween(3000))
}

fun NavGraphBuilder.navigateWithAnimation(
    route: String,
    arguments: List<NamedNavArgument> ?= null,
    exitTransition: ExitTransition = exitAnim,
    enterTransition: EnterTransition = enterAnim,
    content: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit)
){
    composable(
        route = route,
        exitTransition = {exitTransition},
        enterTransition = {enterTransition}
    ){
        content(this, it)
    }
}