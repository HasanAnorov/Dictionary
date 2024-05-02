package com.ierusalem.dictionary.features.landing.presentation

sealed interface LandingPageNavigation {
    data object Failure: LandingPageNavigation
}