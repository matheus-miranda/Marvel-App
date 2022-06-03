# Marvel App

Multi-module app that allows you to fetch and favorite characters from the Marvel API. Still in progress.

![Screenshot](https://user-images.githubusercontent.com/15269393/156079318-b171050f-62e8-412a-a18f-931b1e7f5795.png)

# Modules:
## App module
 - Presentation and Framework

## Core module
 - Domain
 - Repository
 - Use Cases

## Testing module
 - Helper classes for testing

### Dependencies
- Navigation
- ViewModel
- Coroutines
- Dagger Hilt
- Room
- Paging3
- Glide
- Shimmer
- Retrofit
- Gson
- OkHttp
- Datastore

### Code analysis
- Detekt

### CI pipeline
- Bitrise

### Testing
- JUnit
- Mockito
- Espresso

## Steps required to run this app
You must sign up for a Marvel Developer API key by clicking <a href="https://developer.marvel.com">here</a> and creating a apikey.properties file at the root folder of your project and entering the following info:
```
PUBLIC_KEY="xxxxxxxx"
PRIVATE_KEY="xxxxxxxx"
```
