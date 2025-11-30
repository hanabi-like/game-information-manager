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
3. characters are catagorized and ordered by sex, position, region and quality
4. support basic CRUD operations
5. frequent addition of elements and adjustment of element order
6. =v=

## architecture

```
controller/
service/
domain/
    -entity/
repository/
dto/
```

## log

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

changed

- modify function description

[2025-11-28]

Added

- add dto/
- add introduction

changed

- adjust architecture

[2025-11-27]

Added

- initial project construction
- draft preliminary functions and architecture
