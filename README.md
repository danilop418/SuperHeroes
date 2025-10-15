# SuperHeroes App

This Android project is an application to display superhero information. It follows a clean architecture approach with modular layers, using Retrofit for API calls, Coil for image loading, and Gson for data serialization. The project also implements design patterns like Singleton and Observer to ensure maintainability and scalability.

## Architecture

The project follows a layered architecture to separate responsibilities and maintain clean code principles:

```text
src
├── main
│ ├── java
│ │ └── com.example.superheroes
│ │ ├── core
│ │ │ └── api
│ │ │ └── ApiClient.kt
│ │ └── features
│ │ ├── data
│ │ │ ├── SuperHeroDataRepository.kt
│ │ │ └── remote
│ │ │ └── api
│ │ │ ├── SuperHeroApiModel.kt
│ │ │ ├── SuperHeroApiRemote.kt
│ │ │ ├── SuperHeroApiService.kt
│ │ │ └── SuperHeroMapper.kt
│ │ ├── domain
│ │ │ ├── ErrorApp.kt
│ │ │ ├── GetAllSuperHeroesUseCase.kt
│ │ │ ├── GetSuperHeroeByIdUseCase.kt
│ │ │ ├── SuperHeroe.kt
│ │ │ └── SuperHeroeRepository.kt
│ │ └── presentation
│ │ ├── SuperHeroAdapter.kt
│ │ ├── SuperHeroDetailActivity.kt
│ │ ├── SuperHeroDetailViewModel.kt
│ │ ├── SuperHeroesActivity.kt
```



### Project Layers

- **Core / API:** Contains `ApiClient.kt`, which manages the Retrofit instance. Implemented as a **Singleton** to ensure a single source of API configuration.
- **Data:** Responsible for data access, both remote (`SuperHeroApiRemote`) and data mapping (`SuperHeroMapper`), through the repository pattern (`SuperHeroDataRepository`).
- **Domain:** Contains business logic with entities (`SuperHeroe`), repository interfaces, and use cases (`GetAllSuperHeroesUseCase`, `GetSuperHeroeByIdUseCase`).
- **Presentation:** Manages the UI with adapters, activities, and `ViewModel`s. Implements the **Observer pattern** to reactively update the UI when data changes.

### Design Principles

- **Clean Code:** The project follows SOLID principles, modularization, and clear separation of concerns for maintainable and readable code.
- **Singleton:** Ensures that the `ApiClient` instance is globally accessible and only initialized once.
- **Observer:** UI components observe the ViewModel for data changes, allowing reactive updates without tight coupling.

### Technologies

- **Retrofit** – For API calls.
- **Coil** – For loading images efficiently.
- **Gson** – For JSON serialization and deserialization.
- **ViewModel & LiveData** – For lifecycle-aware UI updates.
- **Kotlin** – Modern language features for cleaner code.


