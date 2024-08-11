# BreathTaker
A mobile app for Android that aims to help you learn and maintain proper breathing. The motivation for the app was the fact that most people do not breathe properly. This may be due to, among other things, bad habits, stress, and a lack of conscious thought about breathing.

![UI Image](https://github.com/Wojw99/breath-taker/blob/main/breathtaker-screenshots.png)

## Technical 
The app was written with the use of following libraries/functionalities:
- Kotlin
- Jetpack Compose
- DataStore
- Coroutines
- Retrofit
- Clean Architecture + MVVM
- Custom dependency injection

## Calculations
Five variables are necessary for the correct course of the exercise: totaltime (exercise length), exhale (exhalation length), inhale (inhalation length) and inhale_pause, as well as exhale_pause, the length of the air hold after inhalation and exhalation.
The breath hold lengths have been defined in advance. On the other hand, the values ​​of the first three variables are calculated per user using breathing models proposed by the app authors. To calculate the values, it is necessary to obtain Respiratory Rate, IBW and Tidal
Volume. The current progress of the inhalation or exhalation phase is calculated using the Breath Function. More informations about calculations can be found in the project documentation (located in the main directory of the repository). 
