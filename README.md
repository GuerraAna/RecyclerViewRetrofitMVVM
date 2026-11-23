# RecyclerViewRetrofitMVVM

Estudos:
MVVM (model-view-viewModel):
. Rest -> API;
. API -> Repository;
. Repository -> ViewModel;
. ViewModel -> Activity.

API consumida por URL: Lista de lives;
RecyclerView;
  

## Tecnologias

- Kotlin
- Retrofit 2 + Gson (API de lives)
- RecyclerView + ViewBinding
- Glide (imagens)
- MVVM (`lifecycle-extensions`)
- Gradle 7.2 / compileSdk 32 (minSdk 23)

## Pré-requisitos

- **JDK 11**
- Android SDK Platform **32**

## Como rodar

```bash
echo "sdk.dir=$HOME/Library/Android/sdk" > local.properties   # macOS
./gradlew assembleDebug
```
