# MVI-Architecture-Pattern-New
Sample App using MVI Architecture pattern

MVI- Model View Intent

Model View Intent(MVI) is a reactive architecture pattern where the model is updated based on some interactions from user or the system and the view is updated based on states emitted from the model.


What is the state of the app?

In reactive programming, an app reacts to a change, such as the value of a variable or a button click in your UI. When an app reacts to a change, it transitions to a new state. The new state may appear as a UI change with a progress bar, a new list of movies or a different screen.

1. Model as State :- UI might have different states. Like Error state, Data state, Loading State.
In MVI, models are containers of state. Every time a new immutable model is created, it will be observed by View.
Models both hold data and represent the state of the app.

2. View, same as other architecture patterns, is the interface for displaying/rendering information to the end user by listening to the outputs of the model or state.

3. Intent (≠Android Intents) is an action or event from either the end user interacting with the user Interface or the system with a desired result.



Why MVI:

Well, one of the integral part of MVVM architecture is the ViewModel.
View can observe multiple observable properties from ViewModel for state changes which causes state overlapping issues (sometimes two different states are shown unexpectedly, both error and loader state).

MVI pattern solves the state problem by adding the actual ‘model’ layer which is observed by view for state changes. As this model is an immutable and single source of truth for the current view state, overlapping of states will not happen.

You could create a Model that represents a state Like this:

sealed class MovieState {
  object LoadingState : MovieState()
  data class DataState(val data: List<Movie>) : MovieState()
  data class ErrorState(val data: String) : MovieState()
  data class ConfirmationState(val movie: Movie) : MovieState()
  object FinishState : MovieState()
}

When you create Models this way, you no longer have to manage the state in multiple places such as in the views, presenters or ViewModel. The Model will indicate when your app should display a progress bar, an error message or a list of items.


One of the core concepts of MVI is Unidirectional Data flow. Some of the benefits of unidirectional flow are:
1. It solves the State Problem.
2. It enforces Single Responsibility Principle.

Hence, MVI provides a more decoupled architecture pattern.


