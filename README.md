# game-information-manager

## introduction

> 为什么我们会在虚构的游戏中体验「真实的情感」？这种情感是完全「虚拟」的吗？

```
Cogito, ergo sum.[1]
Transportation was proposed as a mechanism whereby narratives can affect beliefs. Defined as absorption into a story, transportation entails imagery, affect, and attentional focus.
We conceptualized transportation into a narrative world as a distinct mental process, an integrative melding of attention, imagery, and feelings.[2]
```

```
Reference
[1]Descartes, R. (1641). Meditationes de prima philosophia.
[2]Green, M. C., & Brock, T. C. (2000). The role of transportation in the persuasiveness of public narratives. Journal of Personality and Social Psychology, 79(5), 701–721. https://doi.org/10.1037/0022-3514.79.5.701
```

## function

1. record all owned game and character information
2. games are categorized by name
3. characters are catagorized and ordered by sex, region and quality
4. support basic CRUD operations
5. frequent addition of elements and adjustment of element order
6. =v=

## architecture

```
controller/
dto/
service/
    -bo/
domain/
    -model/
    -util/
repository/
    -entity/
    -impl/
```

## log

[2026-03-07]

Changed

- opt service/

[2026-03-06]

Changed

- opt repository/

[2026-03-05]

Changed

- change domain/ structure

[2026-03-05]

Fixed

- adapt @DataRedisTest

[2026-01-30]

Added

- add test/repository/ GameCharacterRepositoryTest

Changed

- optimize repository/entity/ GameCharacterEntity

[2026-01-21]

Changed

- optimize repository/ on branch repository

[2026-01-17]

Changed

- add order in domain/entity/ GameCharacter and dto/ CharacterInformationDTO
- modify test/domain/ CharacterBOTest and test/service/ CharacterServiceTest

[2026-01-12]

Changed

- split methods in service/

[2025-12-18]

Added

- add test/service/ CharacterServiceTest

Changed

- modify dto/ CharacterInformationDTO

[2025-12-03]

Added

- add service/ CharacterService

Changed

- modify dto/ CharacterInformationDTO and pom.xml

[2025-12-01]

Added

- add repository/ CharacterRepository interface

[2025-11-30]

Added

- add test/domain/ CharacterBOTest
- add domain/entity/ Game GameCharacter

Changed

- modify domain/ CharacterBO

Fixed

- fix dto/ details

[2025-11-29]

Added

- add domain/ Character
- add dto/ getter

Changed

- modify function description

[2025-11-28]

Added

- add dto/
- add introduction

Changed

- adjust architecture

[2025-11-27]

Added

- initial project construction
- draft preliminary functions and architecture
