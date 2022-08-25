# Adyen Android Assignment

This repository contains the coding challenge for candidates applying for an Android role at Adyen.
Please keep your Git history intact as we are interested in your development process as well as the end result.
Feel free to make changes to any code in this repository.

Your task is to create an app using the NASA Astronomy Picture Of the Day (APOD) API.
We have set up some basic networking code; you still need to register for an [API key](https://api.nasa.gov/) 
and add it to `app/local.gradle` - see `local.gradle.example` for details. 
You can verify your API key works by running the `PlanetaryServiceTest`.

## Features:
- [DONE] Create a list screen of APODs ![](screens/List Screen.png):
  * Sanitise list to ONLY show images - NOT videos. - DONE
- [DONE] Implement ordering for APODs ![](screens/Reorder Dialog.png): 
  * Order by title - ascending.
  * Order by date - descending.
- [DONE] Show a details screen with more info about a single APOD ![](screens/Detail Screen.png).
- [DONE] Implement error screens:
  * Api error ![DONE](screens/Error screen.png).

n.b The resources folder has been updated with the appropriate colors and icons.
  
## Development Tasks:
Tips for candidates to think about.

- [DONE] App to function in both portrait and landscape orientations:
   * Is the UI still functional - is state persisted?
   * Ensure unnecessary network calls are not made.
- [DONE] Unit tests.

## Grading:
We are interested in:
* How the features work - user functionality.
* How they are implemented - architecture & styling.
* And why implementation decisions were taken.

**OPTIONAL**
* Allow user to save/persist APODs that they like and pin them to the top of the summary list screen. [DONE]
* The UI/UX is not a strict requirement, feel free to make it pixel perfect with animations if you please. [could be better]

## Solution Steps:
step 1:
* add dependency injection (hilt)
* refactor old network service and model to clean arch.
* fix unit tests after refactoring
* add skeleton code/SDKs to be used later

step 2:
* design UI for Planet List and Planet Item
* implement PlanetListActivity & view model 
* handle data and adapter
* handle progress & error 

step 3:
* implement Planet Details screen and logic
* implement error screen 
* implement sort
* unit tests for ordering 

step 4:
* code clean up
* add favourite planets
* save/persist APODs
* update favourite list in main activity
